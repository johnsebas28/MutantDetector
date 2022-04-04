package com.example.xmenmutants;

import java.io.IOException;

import com.example.xmenmutants.controllers.DnaController;
import com.example.xmenmutants.models.DNAModel;
import com.example.xmenmutants.services.IDnaService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DnaControllerTest {
    private MockMvc mockMvc;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
    //@Autowired
	//@InjectMocks
	private DnaController dnaController;
	
	//@Mock
	private IDnaService dnaService;

	public DnaControllerTest() {
		dnaService = Mockito.mock(IDnaService.class);
        dnaController = new DnaController(dnaService);
    }
	
	@BeforeEach
    void setUp() throws IOException {
		try {
			this.mockMvc = MockMvcBuilders.standaloneSetup(dnaController).build();
		} catch (Exception e) {
			System.out.println("Error MockMvcBuilders: " + e.getMessage());
		}
    }
    
	@Test
	void testIsMutant() throws Exception {
		String[] dnaValues = new String[]{"ATGCGA","CAGTAC","TTAAGT","AGAAGG","CACCTA","ACACTG"};
        DNAModel dna = new DNAModel();
        dna.setDna(dnaValues);

		Mockito.when(dnaService.isMutant(dnaValues)).thenReturn(true);
		mockMvc.perform(MockMvcRequestBuilders
				.post("/mutant")
				.content(objectMapper.writeValueAsString(dna))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		 		.andExpect(status().isOk());
	}

	@Test
	void testIsHuman() throws Exception {
		String[] dnaValues = new String[]{"ATGCAT","TGTACG","GATCAA","AGCTAT","GCCTGA","GCTGAC"};
        DNAModel dna = new DNAModel();
        dna.setDna(dnaValues);

		Mockito.when(dnaService.isMutant(dnaValues)).thenReturn(false);
		mockMvc.perform(MockMvcRequestBuilders
				.post("/mutant")
				.content(objectMapper.writeValueAsString(dna))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		 		.andExpect(status().isForbidden());
	}

}
