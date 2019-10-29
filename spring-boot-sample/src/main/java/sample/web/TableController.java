package sample.web;



import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sample.repository.models.MyTableModel;
import sample.services.MyTableService;

@Log4j2
@RestController
@RequestMapping("/table")
public class TableController {

    @Autowired
    private MyTableService myTableService;

    @GetMapping(value = "/all")
    @ResponseStatus(HttpStatus.OK)
    public Flux<MyTableModel> getAllModels() {
        log.info("Retrieve all models");
        Flux<MyTableModel> results = this.myTableService.findAll();
        results.subscribe(p -> inspectObj(p));
        return results;
    }


    @GetMapping(value = "/deleteAll")
    @ResponseStatus(HttpStatus.OK)
    public Mono<MyTableService> deleteAllModels() {
        log.info("Deleting all models");
        Mono<Void> results = this.myTableService.deleteAll();
        return null;
    }

    private void inspectObj(MyTableModel myTableModel) {
        if(myTableModel == null) {
            log.error("model is null");
        }else {
            log.info("Retrieve model ::  {}", myTableModel.getName());
        }
    }





}