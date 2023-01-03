package org.demo.repositories;

import org.demo.entity.TrainingPOJO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingRepository extends JpaRepository<TrainingPOJO,Integer> {
}
