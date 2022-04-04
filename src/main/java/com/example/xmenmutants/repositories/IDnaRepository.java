package com.example.xmenmutants.repositories;

import com.example.xmenmutants.models.StatsModel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDnaRepository extends CrudRepository<StatsModel, Long> {
    
}
