package sample.web;


import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sample.repository.models.MyTableModel;
import sample.services.DataService;

import javax.naming.Context;


@Slf4j
@RestController
@RequestMapping("/table")
public class TableController {

    private DataService dataService;
    private TableHolder tableHolder;

    @GetMapping(value = "/all")
    @ResponseStatus(HttpStatus.OK)
    public Flux<MyTableModel> getAllModels(final Context context) {

        log.info("Retrieve all models " + ReflectionToStringBuilder.toString(context));

        return this.tableHolder.getAllTable(context)
                .flatMap(table -> this.dataService.findAll());
    }





}