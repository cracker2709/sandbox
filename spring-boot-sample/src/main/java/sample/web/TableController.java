package sample.web;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import sample.repository.models.MyTableModel;
import sample.services.DataService;


@Slf4j
@RestController
@RequestMapping("/table")
public class TableController {

    @Autowired
    private DataService dataService;

    @GetMapping(value = "/all")
    @ResponseStatus(HttpStatus.OK)
    public Flux<MyTableModel> getAllModels() {

        log.info("Retrieve all models");

        return this.dataService.findAll().flatMap(Flux::just);
    }





}