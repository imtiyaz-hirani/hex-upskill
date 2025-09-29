package com.upskill.supportflowlite.repository;

import com.upskill.supportflowlite.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("select u from User u where u.id=?1")
    Optional<User> getUser(int userId);

    User findByUsername(String username);
}
