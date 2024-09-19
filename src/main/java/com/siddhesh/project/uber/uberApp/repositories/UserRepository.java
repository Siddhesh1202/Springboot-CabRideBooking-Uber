package com.siddhesh.project.uber.uberApp.repositories;

import com.siddhesh.project.uber.uberApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findbyEmail(String email);
}
