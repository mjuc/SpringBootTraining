package org.demo.controllers;

import org.demo.services.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/trainings")
public class TrainingController {
    @Autowired
    private TrainingService trainingService;


}
