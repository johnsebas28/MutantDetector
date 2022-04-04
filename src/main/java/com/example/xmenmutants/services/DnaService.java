package com.example.xmenmutants.services;

import java.util.ArrayList;

import com.example.xmenmutants.models.ResponseDataModel;
import com.example.xmenmutants.models.StatsModel;
import com.example.xmenmutants.repositories.IDnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import reactor.core.publisher.Mono;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DnaService implements IDnaService {

    private final IDnaRepository _dnaRepository;
    private static final int MIN_ROW_ITEMS = 4;
    private static final int MUTANT_DNA_STRINGS = 2;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public DnaService(final IDnaRepository dnaRepository) {
        this._dnaRepository = dnaRepository;
    }

    @Override
    public boolean isMutant(String[] dnaValues) {
        int mutantStrings = getMutantRows(dnaValues) + getMutantColumns(dnaValues) + getMutantLeftDiagonal(dnaValues)
                + getMutantRightDiagonal(dnaValues);
        try {
            CreatePostDna(mutantStrings, dnaValues);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return mutantStrings >= MUTANT_DNA_STRINGS ? true : false;
    }

    private void CreatePostDna(int mutantStrings, String[] dnaValues) throws JsonProcessingException {

        StatsModel dnaModel = new StatsModel();
        dnaModel.setDna(objectMapper.writeValueAsString(dnaValues));
        dnaModel.setIsMutant(mutantStrings >= MUTANT_DNA_STRINGS);
        this._dnaRepository.save(dnaModel);

    }

    private int getMutantColumns(String[] dnaValues) {
        char previousChar = '*';
        int charCounter = 0;
        int mutantStrings = 0;
        int columnsCount = dnaValues[0].length();
        for (int i = 0; i < columnsCount; i++) {
            for (String currentRow : dnaValues) {
                var currentChar = currentRow.charAt(i);
                if (currentChar == previousChar)
                    charCounter++;
                else
                    charCounter = 0;

                if (charCounter >= MIN_ROW_ITEMS - 1) {
                    charCounter = 0;
                    mutantStrings++;
                }
                previousChar = currentChar;
            }
            charCounter = 0;
            previousChar = '*';
        }

        return mutantStrings;
    }

    private int getMutantRows(String[] dnaValues) {
        char previousChar = '*';
        int charCounter = 0;
        int mutantStrings = 0;
        for (String currentRow : dnaValues) {
            // currentrow = "AGGT"
            for (int i = 0; i < currentRow.length(); i++) {
                var currentChar = currentRow.charAt(i);
                if (currentChar == previousChar)
                    charCounter++;
                else
                    charCounter = 0;

                if (charCounter >= MIN_ROW_ITEMS - 1) {
                    charCounter = 0;
                    mutantStrings++;
                }
                previousChar = currentChar;
            }
            charCounter = 0;
            previousChar = '*';
        }
        return mutantStrings;
    }

    // https://github.com/eugenp/tutorials/blob/master/core-java-modules/core-java-arrays-multidimensional/src/main/java/com/baeldung/array/looping/LoopDiagonally.java
    private int getMutantLeftDiagonal(String[] dnaValues) {

        int length = dnaValues.length;
        int diagonalLines = (length + length) - 1;
        int midPoint = (diagonalLines / 2) + 1;
        int itemsInDiagonal = 0;
        char[][] matrizDna = new char[length][length];
        for (int i = 0; i < length; i++) {
            char[] row = dnaValues[i].toCharArray();
            for (int j = 0; j < row.length; j++) {
                matrizDna[i][j] = row[j];
            }
        }

        int charCounter = 0;
        char previousChar = '*';
        int mutantStrings = 0;
        for (int i = 1; i <= diagonalLines; i++) {
            int rowIndex;
            int columnIndex;
            if (i <= midPoint) {
                itemsInDiagonal++;
                for (int j = 0; j < itemsInDiagonal; j++) {
                    rowIndex = (i - j) - 1;
                    columnIndex = j;
                    var currentChar = matrizDna[rowIndex][columnIndex];
                    if (currentChar == previousChar)
                        charCounter++;
                    else
                        charCounter = 0;

                    if (charCounter >= MIN_ROW_ITEMS - 1) {
                        charCounter = 0;
                        mutantStrings++;
                    }
                    previousChar = currentChar;
                }
                charCounter = 0;
                previousChar = '*';
            } else {
                itemsInDiagonal--;
                for (int j = 0; j < itemsInDiagonal; j++) {
                    rowIndex = (length - 1) - j;
                    columnIndex = (i - length) + j;
                    var currentChar = matrizDna[rowIndex][columnIndex];
                    if (currentChar == previousChar)
                        charCounter++;
                    else
                        charCounter = 0;

                    if (charCounter >= MIN_ROW_ITEMS - 1) {
                        charCounter = 0;
                        mutantStrings++;
                    }
                    previousChar = currentChar;
                }
                charCounter = 0;
                previousChar = '*';
            }
        }
        return mutantStrings;
    }

    private int getMutantRightDiagonal(String[] dnaStrings) {
        char previousChar = '*';
        int charCounter = 0;
        int mutantStrings = 0;
        // String[] dnaStrings = new String[] {"12345","67890","12345","67890","12345"};
        for (int i = 0; i < dnaStrings[0].length(); i++) {
            if (dnaStrings.length - i < MIN_ROW_ITEMS)
                break;
            for (int j = 0; j < dnaStrings.length - i; j++) {
                var currentChar = dnaStrings[j].charAt(i + j);
                if (currentChar == previousChar)
                    charCounter++;
                else
                    charCounter = 0;

                if (charCounter >= MIN_ROW_ITEMS - 1) {
                    charCounter = 0;
                    mutantStrings++;
                }
                previousChar = currentChar;
            }
            charCounter = 0;
            previousChar = '*';
        }
        for (int i = 0; i < dnaStrings[0].length(); i++) {
            if (dnaStrings.length - i < MIN_ROW_ITEMS)
                break;
            for (int j = i + 1; j < dnaStrings.length - i; j++) {
                var currentChar = dnaStrings[j].charAt(j - (i + 1));
                if (currentChar == previousChar)
                    charCounter++;
                else
                    charCounter = 0;

                if (charCounter >= MIN_ROW_ITEMS - 1) {
                    charCounter = 0;
                    mutantStrings++;
                }
                previousChar = currentChar;
            }
            charCounter = 0;
            previousChar = '*';
        }
        return mutantStrings;
    }

    @Override
    public ResponseDataModel getStats() {
        ArrayList<StatsModel> StatsList = (ArrayList<StatsModel>) this._dnaRepository.findAll();
        ResponseDataModel responseDataModel = new ResponseDataModel();
        for (int i = 0; i < StatsList.size(); i++) {

            if (StatsList.get(i).getIsMutant()) {
                responseDataModel.setCount_mutant_dna(responseDataModel.getCount_mutant_dna() + 1);
            } else {
                responseDataModel.setCount_human_dna(responseDataModel.getCount_human_dna() + 1);
            }
        }
        int HumansCout = responseDataModel.getCount_human_dna();
        int MutantsCount = responseDataModel.getCount_mutant_dna();
        if(HumansCout == 0){
            HumansCout = 1;
        }
        if(MutantsCount == 0){
            MutantsCount = 1;
        }
        responseDataModel.setRatio(responseDataModel.getCount_mutant_dna() /HumansCout);
        return responseDataModel;
    }
}