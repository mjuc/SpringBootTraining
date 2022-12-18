package org.demo.repositories;

import org.demo.model.TrainingPOJO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TrainingRepository extends JpaRepository<TrainingPOJO,Long> {
}
