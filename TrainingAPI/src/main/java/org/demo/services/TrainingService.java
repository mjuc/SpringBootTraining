package org.demo.services;

import org.demo.model.TrainingPOJO;
import org.demo.repositories.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingService {
    @Autowired
    private TrainingRepository trainingRepository;

    public List<TrainingPOJO> list(){
        return trainingRepository.findAll();
    }
}
