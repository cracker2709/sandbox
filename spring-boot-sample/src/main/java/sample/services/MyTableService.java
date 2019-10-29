package sample.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sample.repository.MyTableRepository;
import sample.repository.models.MyTableModel;

@Service
public class MyTableService {

    @Autowired
    private MyTableRepository myTableRepository;


    public Flux<MyTableModel> findAll(){
        return myTableRepository.findAll();
    }

    public Mono<Void> deleteAll() {
        return myTableRepository.deleteAll();
    }
}
