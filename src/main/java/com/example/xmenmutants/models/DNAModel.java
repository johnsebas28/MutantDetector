package com.example.xmenmutants.models;

import javax.validation.constraints.NotNull;

import com.example.xmenmutants.validator.DnaListValidation;

public class DNAModel {

   @NotNull 
   @DnaListValidation
   private String[] dna;

   public String[] getDna(){
       return dna;
   }
   public void setDna (String[] value){
        dna = value;
   }  

}