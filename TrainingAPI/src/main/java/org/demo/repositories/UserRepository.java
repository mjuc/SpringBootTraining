package org.demo.repositories;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findById(Integer id);

    default User getById(Integer id){
        var user = findById(id);
        //TODO validation of found user
        return user.get();
    }

    Optional<User> findByUsername(String username);
    default User getByUsername(String username){
        var user = findByUsername(username);
        //TODO validation of found user
        return user.get();
    }
}
