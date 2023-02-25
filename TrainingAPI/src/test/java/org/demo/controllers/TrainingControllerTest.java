package org.demo.controllers;

import org.demo.controllers.response.TrainingResponse;
import org.demo.entity.Training;
import org.demo.services.TrainingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        var date = LocalDateTime.now();
        when(trainingService.last()).thenReturn(new Training("type",date));
        var res = trainingController.last();
        verify(trainingService).last();
        assertEquals(res.getType(),"type");
        assertEquals(res.getDate(),date);
    }
    @Test
    void testGetByType(){
        when(trainingService.getSessionsByType("test")).thenReturn(
                List.of(new Training("test", LocalDateTime.now()),
                        new Training("test", LocalDateTime.now())));
        var res = trainingController.getByType("test");
        verify(trainingService).getSessionsByType("test");
        for (TrainingResponse t: res) {
            assertEquals(t.getType(),"test");
        }
    }
    
}