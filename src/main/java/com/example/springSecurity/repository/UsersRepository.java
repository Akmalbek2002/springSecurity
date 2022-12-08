package com.example.springSecurity.repository;

import com.example.springSecurity.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users,Integer> {
    Optional<Users> findByLogin(String login);
}
