package com.example.xmenmutants;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.example.xmenmutants.models.ResponseDataModel;
import com.example.xmenmutants.services.IDnaService;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//import reactor.test.StepVerifier;

@SpringBootTest
 class DnaServiceTest {
    @Autowired
	IDnaService dnaService;

    @Test
	void testGetIsMutant() {
		String[] dnaValues = new String[]{"ATGCGA","CAGTAC","TTAAGT","AGAAGG","CACCTA","ACACTG"};
			// StepVerifier
			// .create(dnaService.isMutant(dnaValues))
			// .expectNext(true)
			// .expectComplete()
			// .verify();
		 assertTrue(dnaService.isMutant(dnaValues));
	} 
	@Test
	void testGetIsHuman() {
		String[] dnaValues = new String[]{"ATGCAT","TGTACG","GATCAA","AGCTAT","GCCTGA","GCTGAC"};
		 assertFalse(dnaService.isMutant(dnaValues));
	}
	@Test
	void testGetStats(){
	 	ResponseDataModel responseDataModel = dnaService.getStats();
		assertNotNull(responseDataModel);
	}
}
