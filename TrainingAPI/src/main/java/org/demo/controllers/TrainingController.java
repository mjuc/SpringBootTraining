package org.demo.controllers;

import org.demo.controllers.response.TrainingResponse;
import org.demo.entity.Training;
import org.demo.controllers.request.TrainingType;
import org.demo.services.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.time.LocalDateTime;
import java.util.List;
import java.net.URI;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/trainings")
public class TrainingController {
    private final TrainingService trainingService;
    @Autowired
    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @GetMapping("/")
    public List<TrainingResponse> getAllSessions(){
        return trainingService.allSessions().stream().map(
                t -> {
                    TrainingResponse r = new TrainingResponse();
                    r.setId(t.getId());
                    r.setType(t.getType());
                    r.setDate(t.getDate());
                    return r;
                }
        ).collect(Collectors.toList());
    }
    @GetMapping("/last")
    public TrainingResponse last(){
        var last = trainingService.last();
        var ret = new TrainingResponse();
        ret.setId(last.getId());
        ret.setType(last.getType());
        ret.setDate(last.getDate());
        return ret;
    }
    @GetMapping("/{type}")
    public List<TrainingResponse> getByType(@PathVariable("type") String type){
        return trainingService.getSessionsByType(type).stream().map(
                t -> {
                    TrainingResponse r = new TrainingResponse();
                    r.setId(t.getId());
                    r.setType(t.getType());
                    r.setDate(t.getDate());
                    return r;
                }
        ).collect(Collectors.toList());
    }
    @PostMapping(value = "/",consumes = "application/json")
    public ResponseEntity<Object> add(@RequestBody TrainingType type){
        Training training = new Training(type.getType(), LocalDateTime.now());
        trainingService.add(training);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(training.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
}
