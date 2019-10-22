package com.auchan.fennec.cart;

import com.auchan.fennec.cart.repositories.models.CustomerModel;
import com.auchan.fennec.cart.repositories.models.OrderModel;
import com.auchan.fennec.cart.repositories.models.OrderSaleContextModel;
import com.auchan.fennec.cart.services.CartService;
import com.auchan.fennec.cart.utils.Context;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Setter(onMethod = @__({@Autowired}))
@Slf4j
public class CartHolder {

    private UserProfile userProfile;
    private CartService cartService;

    /**
     * Get First Customer Cart using the customer ID extracted from the request using {@link UserProfile#getCustomer(Context)}
     *
     * @param context the request context
     * @return Mono of OrderModel
     */
    public Mono<OrderModel> getCurrentCart(final Context context) {

        final CustomerModel contextCustomer = this.userProfile.getCustomer(context);
        return this.cartService.getCustomerCarts(contextCustomer, context.isAuthenticated())
                .distinct(OrderModel::getOrderId)
                .collectList()
                .flatMap(orderModels -> mergeCarts(orderModels, contextCustomer))
                .switchIfEmpty(Mono.defer(() -> this.cartService.createCart(context, initEmptyCart(context))));

    }

    //FIXME rework this method, especially the save.
    private Mono<OrderModel> mergeCarts(final List<OrderModel> carts, final CustomerModel contextCustomer) {

        final Optional<OrderModel> firstCart = carts.stream().findFirst();
        if (firstCart.isPresent()) {
            if (carts.size() > 2) {
                log.warn("Customer {} - {} has more that two active cart ", contextCustomer.getId(), contextCustomer.getEmail());
                log.warn("Orders not merged {}", carts.stream().skip(2).map(OrderModel::getOrderId).collect(Collectors.joining(",")));
            } else if (carts.size() > 1) {
                final OrderModel secondCart = carts.get(1);
                return this.cartService.mergeCarts(firstCart.get(), secondCart)
                        .doOnSuccess(mergedCart -> {
                            this.cartService.removeCart(secondCart.getOrderId()).subscribe();
                        });
            } else {
                this.cartService.updateCartCustomer(firstCart.get(), contextCustomer);
                return this.cartService.saveOrder(firstCart.get());
            }

        }
        return Mono.justOrEmpty(firstCart);

    }

    //TODO Mettre en place un proxy, pour appeler le service externe si necessaire.
    public OrderModel initEmptyCart(final Context context) {

        final CustomerModel customer = context.isAuthenticated() ?
                context.getCustomer() :
                CustomerModel.builder().id(context.getGuid())
                        .anonymous(true)
                        .build();

        return OrderModel.builder(context)
                .customer(customer)
                .orderSaleContext(OrderSaleContextModel.builder(context)
                        .build())
                .build();
    }

}
