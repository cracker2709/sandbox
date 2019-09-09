package sample.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sample.domain.MyTableEntity;


@Transactional
@Repository("MyTableRepository")
public interface MyTableRepository extends CrudRepository<MyTableEntity, Long> {

    /**
     *
     * @param dateMin
     * @param dateMax
     * @return
     */
    List<MyTableEntity> findByCreationDateBetween(Date dateMin, Date dateMax);

}