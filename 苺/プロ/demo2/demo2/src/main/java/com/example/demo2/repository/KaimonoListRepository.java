package com.example.demo2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo2.entity.KaimonoList;


public interface KaimonoListRepository extends JpaRepository<KaimonoList, Integer>{
    List<KaimonoList> findByUser(String user);
}
