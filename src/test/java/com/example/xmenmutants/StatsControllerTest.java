package com.example.xmenmutants;
import java.io.IOException;

import com.example.xmenmutants.controllers.StatsController;
import com.example.xmenmutants.models.DNAModel;
import com.example.xmenmutants.models.ResponseDataModel;
import com.example.xmenmutants.services.IDnaService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
public class StatsControllerTest {
    private MockMvc mockMvc;
    //@Mock
	private IDnaService dnaService;
    private ObjectMapper objectMapper = new ObjectMapper();
	
	private StatsController statsController;

	public StatsControllerTest() {
		dnaService = Mockito.mock(IDnaService.class);
        statsController = new StatsController(dnaService);
    }

    @BeforeEach
    void setUp() throws IOException {
		try {
			this.mockMvc = MockMvcBuilders.standaloneSetup(statsController).build();
		} catch (Exception e) {
			System.out.println("Error MockMvcBuilders: " + e.getMessage());
		}
    }

    @Test
	void testGetStats() throws Exception {
		ResponseDataModel response = new ResponseDataModel();
		response.setCount_human_dna(40);
		response.setCount_mutant_dna(100);
		response.setRatio(2.5);
		Mockito.when(dnaService.getStats()).thenReturn(response);
		mockMvc.perform(MockMvcRequestBuilders
				.get("/stats")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("count_mutant_dna").value(100))
				.andExpect(MockMvcResultMatchers.jsonPath("count_human_dna").value(40))
				.andExpect(MockMvcResultMatchers.jsonPath("ratio").value(2.5));
	}

}
