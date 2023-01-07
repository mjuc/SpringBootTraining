package org.demo.services;

import org.demo.entity.TrainingPOJO;
import org.demo.repositories.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingService {
    @Autowired
    private TrainingRepository trainingRepository;


    public List<TrainingPOJO> allSessions(){return trainingRepository.findAll();}
    public List<TrainingPOJO> getSessionsByType(String type){return trainingRepository.findByType(type);}
    public TrainingPOJO last(){return trainingRepository.findTopByOrderByIdDesc();}

    public void add(TrainingPOJO trainingPOJO){
        trainingRepository.save(trainingPOJO);
    }
}
