package org.demo.controllers;

import org.demo.entity.TrainingPOJO;
import org.demo.services.TrainingService;
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
    @Autowired
    private TrainingService trainingService;
    @GetMapping("/all")
    public List<TrainingPOJO> getAllSessions(){
        return trainingService.allSessions();
    }
    @GetMapping("/")
    public TrainingPOJO last(){
        return trainingService.last();
    }
    @PostMapping(value = "/add",consumes = "application/json")
    public ResponseEntity<Object> add(@RequestBody String type){
        //Integer id = trainingService.allSessions().size() + 1;
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        String date = format.format(new Date());
        TrainingPOJO training = new TrainingPOJO(type,date);
        trainingService.add(training);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(training.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
}
