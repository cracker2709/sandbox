package sample.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import sample.domain.MyTableEntity;


@Transactional
@Repository("MyTableRepository")
public interface MyTableRepository extends ReactiveSortingRepository<MyTableEntity, Long> {

    /**
     *
     * @param dateMin
     * @param dateMax
     * @return
     */
    Flux<MyTableEntity> findByCreationDateBetween(Date dateMin, Date dateMax);

}