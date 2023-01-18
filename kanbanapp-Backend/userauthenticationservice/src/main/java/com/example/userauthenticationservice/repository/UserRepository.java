package com.example.userauthenticationservice.repository;

import com.example.userauthenticationservice.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, String> {
    public Users findByEmailidAndPassword(String email , String password);
}