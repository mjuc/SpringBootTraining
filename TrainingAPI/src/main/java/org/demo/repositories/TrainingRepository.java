package org.demo.repositories;

import org.demo.entity.TrainingPOJO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TrainingRepository extends JpaRepository<TrainingPOJO,Integer> {
    TrainingPOJO findTopByOrderByIdDesc();
    List<TrainingPOJO> findByType(@Param(value = "type") String type);
}
