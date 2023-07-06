package com.example.demo2.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo2.entity.Bought;

public interface BoughtRepository extends JpaRepository<Bought, Integer> {
    List<Bought>findByCreateTime(String date);   
    List<Bought>findByCreateTimeContaining(String date);   

    
}
