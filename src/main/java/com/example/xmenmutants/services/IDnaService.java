package com.example.xmenmutants.services;

import com.example.xmenmutants.models.ResponseDataModel;


public interface IDnaService {
    boolean isMutant(String[] dnaValues);
    ResponseDataModel getStats();
}
