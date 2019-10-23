package sample.repository;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import sample.repository.models.MyTableModel;



@Repository
public interface MyTableRepository extends ReactiveSortingRepository<MyTableModel, String> {

    Mono<MyTableModel> findByCode(final String code);

    Mono<MyTableModel> findByName(final String name);

    /**
     *
     * @param dateMin
     * @param dateMax
     * @return
     */
    //Flux<MyTableModel> findAllCreationDateBetween(Date dateMin, Date dateMax);



}