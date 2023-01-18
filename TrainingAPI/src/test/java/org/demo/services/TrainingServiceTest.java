package org.demo.services;

import org.demo.entity.Training;
import org.demo.repositories.TrainingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrainingServiceTest {
    @Mock private TrainingRepository trainingRepository;
    private TrainingService trainingService;
    @BeforeEach
    void setup(){
        this.trainingService = new TrainingService(this.trainingRepository);
    }
    @Test
    void testGetAll(){
        trainingService.allSessions();
        verify(trainingRepository).findAll();
    }
    @Test
    void testGetByType(){
        trainingService.getSessionsByType("test");
        verify(trainingRepository).findByType("test");
    }
    @Test
    void  testLast(){
        trainingService.last();
        verify(trainingRepository).findTopByOrderByIdDesc();
    }
    @Test
    void testAdd(){
        Training emptyTraining = mock(Training.class);
        trainingService.add(emptyTraining);
        verify(trainingRepository).save(emptyTraining);
    }

}