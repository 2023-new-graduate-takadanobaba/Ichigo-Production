package com.example.demo2.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo2.entity.Username;

public interface UsernameRepository extends JpaRepository <Username, Integer>{
    
}
