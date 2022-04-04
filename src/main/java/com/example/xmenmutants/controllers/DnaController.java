package com.example.xmenmutants.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//import reactor.core.publisher.Mono;

import javax.validation.Valid;
import com.example.xmenmutants.models.*;
import com.example.xmenmutants.services.IDnaService;

import org.springframework.http.MediaType;

@RestController
public class DnaController {

    private final IDnaService dnaService;

    @Autowired
    public DnaController(final IDnaService _dnaService) {
        this.dnaService = _dnaService;
    }

    @PostMapping(value = "/mutant", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> isMutant(@Valid @RequestBody DNAModel dna) {

        // @Valid y DNAModel implementa custom method para Validar las letras de cada
        // fila.
        // Validar si es un mutante
        // Guardar en Base de datos
        boolean x = this.dnaService.isMutant(dna.getDna());
        if (x) {
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
        }
    }
}