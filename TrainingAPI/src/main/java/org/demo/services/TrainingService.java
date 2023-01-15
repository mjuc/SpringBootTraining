package org.demo.services;

import org.demo.entity.Training;
import org.demo.repositories.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingService {
    private TrainingRepository trainingRepository;
    @Autowired
    public TrainingService(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    public List<Training> allSessions(){return trainingRepository.findAll();}
    public List<Training> getSessionsByType(String type){return trainingRepository.findByType(type);}
    public Training last(){return trainingRepository.findTopByOrderByIdDesc();}

    public void add(Training training){
        trainingRepository.save(training);
    }
}
