package org.demo.repositories;

import org.demo.entity.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TrainingRepository extends JpaRepository<Training,Integer> {
    Training findTopByOrderByIdDesc();
    List<Training> findByType(@Param(value = "type") String type);
}
