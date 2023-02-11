package org.demo.controllers;

import org.demo.services.TrainingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TrainingControllerTest {
    @Mock private TrainingService trainingService;
    private TrainingController trainingController;
    @BeforeEach
    void setTrainingController(){
        this.trainingController = new TrainingController(this.trainingService);
    }
    @Test
    void testGetAllSessions(){
        trainingController.getAllSessions();
        verify(trainingService).allSessions();
    }
    @Test
    void testLast(){
        trainingController.last();
        verify(trainingService).last();
    }
    @Test
    void testGetByType(){
        trainingController.getByType("test");
        verify(trainingService).getSessionsByType("test");
    }
}