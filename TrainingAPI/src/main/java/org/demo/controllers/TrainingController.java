package org.demo.controllers;

import org.demo.entity.Training;
import org.demo.request.TrainingType;
import org.demo.services.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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
    public ResponseEntity<Object> add(@RequestBody TrainingType type){
        Training training = new Training(type.getType(), LocalDateTime.now());
        trainingService.add(training);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(training.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
}
