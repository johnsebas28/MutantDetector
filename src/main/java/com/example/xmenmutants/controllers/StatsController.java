package com.example.xmenmutants.controllers;

import com.example.xmenmutants.models.ResponseDataModel;
import com.example.xmenmutants.services.IDnaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


@RestController
public class StatsController {
    private final IDnaService dnaService;

    @Autowired
    public StatsController(final IDnaService _dnaService) {
        this.dnaService = _dnaService;
    }

    @GetMapping(value = "/stats",consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ResponseDataModel> getStats() {
        ResponseDataModel responseDataModel = this.dnaService.getStats();
        return new ResponseEntity<ResponseDataModel>(responseDataModel,HttpStatus.OK);
    }
}
