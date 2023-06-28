package com.example.demo2.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo2.entity.Listtoform;

public interface ListtoformRepository extends JpaRepository<Listtoform, Integer> {
    List<Listtoform>findBycreateTime(Date date);   
}
