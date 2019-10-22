package sample.web;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import sample.repository.models.MyTableModel;
import sample.services.DataService;

import javax.naming.Context;

@Component
@Setter(onMethod = @__({@Autowired}))
@Slf4j
public class TableHolder {


    private DataService dataService;

    /**
     * get All Docs
     *
     * @param context the request context
     * @return Flux of {@link sample.repository.models.MyTableModel}
     */
    public Flux<MyTableModel> getAllTable(final Context context) {

        return this.dataService.findAll()
                .distinct(MyTableModel::getId);
    }


}
