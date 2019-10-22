package sample.repository;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import sample.repository.models.MyTableModel;

import java.util.Date;


@Repository("MyTableRepository")
public interface MyTableRepository extends ReactiveSortingRepository<MyTableModel, Long> {

    /**
     *
     * @param dateMin
     * @param dateMax
     * @return
     */
    Flux<MyTableModel> findAllCreationDateBetween(Date dateMin, Date dateMax);



}