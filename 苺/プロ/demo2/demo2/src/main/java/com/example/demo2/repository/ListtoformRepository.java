package com.example.demo2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo2.entity.KaimonoList;

public interface ListtoformRepository extends JpaRepository<KaimonoList, Integer> {
    
}
