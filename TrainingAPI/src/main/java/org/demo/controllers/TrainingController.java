package org.demo.controllers;

import org.demo.entity.TrainingPOJO;
import org.demo.services.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        return new TrainingPOJO(1,"","");
    }
}
