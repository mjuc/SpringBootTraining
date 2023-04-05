package org.demo.controllers;

import org.demo.controllers.request.TrainingType;
import org.demo.controllers.response.TrainingResponse;
import org.demo.entity.Training;
import org.demo.services.TrainingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


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
        var date = LocalDateTime.now();
        when(trainingService.allSessions()).thenReturn(
                List.of(new Training("test", date),
                        new Training("type", date)));
        var res = trainingController.getAllSessions();
        verify(trainingService).allSessions();
        assertEquals(res.get(0).getType(),"test");
        assertEquals(res.get(0).getDate(),date);
        assertEquals(res.get(1).getType(),"type");
        assertEquals(res.get(1).getDate(),date);
    }
    @Test
    void testLast(){
        var date = LocalDateTime.now();
        when(trainingService.last()).thenReturn(new Training("type",date));
        var res = trainingController.last();
        verify(trainingService).last();
        assertEquals("type",res.getType());
        assertEquals(date,res.getDate());
    }
    @Test
    void testGetByType(){
        when(trainingService.getSessionsByType("test")).thenReturn(
                List.of(new Training("test", LocalDateTime.now()),
                        new Training("test", LocalDateTime.now())));
        var res = trainingController.getByType("test");
        verify(trainingService).getSessionsByType("test");
        for (TrainingResponse t: res) {
            assertEquals("test",t.getType());
        }
    }
    @Test
    void testAdd(){
        var training = new TrainingType();
        var request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        training.setType("type");
        var response = trainingController.add(training);
        assertEquals("201 CREATED",response.getStatusCode().toString());
        assertEquals("",response.getHeaders().getLocation().getPath());
    }
}
