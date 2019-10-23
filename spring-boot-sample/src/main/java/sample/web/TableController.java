package sample.web;



import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import sample.repository.models.MyTableModel;
import sample.services.MyTableService;

@Log4j2
@RestController
@RequestMapping("/table")
public class TableController {

    @Autowired
    private MyTableService myTableService;

    @GetMapping(value = "/all")
    //@ResponseStatus(HttpStatus.OK)
    public Flux<MyTableModel> getAllModels() {
        Flux<MyTableModel> results = this.myTableService.findAll();

        results.subscribe(p -> System.out.println(p.getName()));

        log.info("Retrieve all models");

        return this.myTableService.findAll();
    }





}