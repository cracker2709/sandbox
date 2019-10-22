package sample.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import sample.repository.MyTableRepository;
import sample.repository.models.MyTableModel;

@Service
public class DataService {

    @Autowired
    private MyTableRepository myTableRepository;


    public Flux<MyTableModel> findAll(){
        return myTableRepository.findAll();
    }
}
