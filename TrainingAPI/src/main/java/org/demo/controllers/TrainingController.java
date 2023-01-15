package org.demo.controllers;

import org.demo.entity.Training;
import org.demo.services.TrainingService;
import org.demo.utils.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import java.net.URI;

@RestController
@RequestMapping(path = "/trainings")
public class TrainingController {
    private TrainingService trainingService;
    @Autowired
    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @GetMapping("/")
    public List<Training> getAllSessions(){
        return trainingService.allSessions();
    }
    @GetMapping("/last")
    public Training last(){
        return trainingService.last();
    }
    @GetMapping("/{type}")
    public List<Training> getByType(@PathVariable("type") String type){
        return trainingService.getSessionsByType(type);
    }
    @PostMapping(value = "/",consumes = "application/json")
    public ResponseEntity<Object> add(@RequestBody String type){
        //Integer id = trainingService.allSessions().size() + 1;
        Parser parser = new Parser();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        String date = format.format(new Date());
        Training training = new Training(parser.parsedType(type),date);
        trainingService.add(training);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(training.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
}
