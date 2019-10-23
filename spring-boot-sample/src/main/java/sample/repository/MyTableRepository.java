package sample.repository;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sample.repository.models.MyTableModel;



@Repository
public interface MyTableRepository extends ReactiveSortingRepository<MyTableModel, Long> {

    Mono<MyTableModel> findByName(final Long name);

    /**
     *
     * @param dateMin
     * @param dateMax
     * @return
     */
    //Flux<MyTableModel> findAllCreationDateBetween(Date dateMin, Date dateMax);



}