package com.deekol.pocketbookrepair.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import com.deekol.pocketbookrepair.model.PartEntity;
import com.deekol.pocketbookrepair.payload.response.PartResponse;
import com.deekol.pocketbookrepair.repository.DeviceRepository;
import com.deekol.pocketbookrepair.repository.PartRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(PartController.class)
public class PartControllerTest {
	@Autowired
	MockMvc mockMvc;
	@Autowired
	ObjectMapper mapper;
	
	@MockBean
	PartRepository partRepository;
	@MockBean
	DeviceRepository deivceRepository;
	
	BigDecimal buy_1 = new BigDecimal("750");
	BigDecimal buy_2 = new BigDecimal("1000");
	BigDecimal sale_2 = new BigDecimal("1500");
	BigDecimal buy_3 = new BigDecimal("6000");
	
	PartEntity PART_1 = new PartEntity(1l, null, "keyboard", null, null, "new", "100% Den", buy_1, null, null, "HP, Acer", null);
	PartEntity PART_2 = new PartEntity(2l, "Asus", "top case", null, null, "new", "100% Den", buy_1, sale_2, null, "Asus d20", null);
	PartEntity PART_3 = new PartEntity(3l, "Apple", "Display", null, null, "good", "100% Den", buy_1, null, null, "Air", null);
	PartEntity[] pArr = {PART_1, PART_2, PART_3};
	
	@SuppressWarnings("unchecked")
	@Test
	public void getAllParts_success() throws Exception {
		List<?> parts = new ArrayList<>(Arrays.asList(pArr));
		
		Mockito.when(partRepository.findAll()).thenReturn((List<PartEntity>) parts);
		
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/part")
				.contentType(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("$", hasSize(3)))
					.andExpect(jsonPath("$[2].name", is("Display")));
	}
	
	@Test
	public void getPartById_success() throws Exception {
		Mockito.when(partRepository.findById(PART_1.getId())).thenReturn(Optional.of(PART_1));
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/part/1")
				.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$", notNullValue()))
					.andExpect(jsonPath("$.name", is("keyboard")));
	}
	
	@Test
	public void addPart_success() throws Exception {
		BigDecimal partBuy = new BigDecimal("2000");
		
		PartEntity part = PartEntity.builder()
				.name("Поддон")
				.specification("Lenovo 100")
				.description("100% Den")
				.buy(partBuy)
				.sale(null)
				.creationDate(LocalDate.now())
				.build();
		
		PartResponse partResponse = PartResponse.builder()
				.name("Поддон")
				.specification("Lenovo 100")
				.description("100% Den")
				.buy(partBuy)
				.sale(null)
				.creationDate(LocalDate.now())
				.build();
		
		Mockito.when(partRepository.save(part)).thenReturn(part);
		
		MockHttpServletRequestBuilder mockRequestBuilder = MockMvcRequestBuilders.post("/api/part")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(partResponse));
		
		mockMvc.perform(mockRequestBuilder)
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", notNullValue()))
			.andExpect(jsonPath("$.name", is("Поддон")));
	}
	
//	@Test
//	public void updatePart_success() throws Exception {
//		PartEntity part = PartEntity.builder()
//				.id(1l)
//				.name("Клавиатура")
//				.specification("HP")
//				.description("100% Den")
//				.buy(buy_1)
//				.sale(null)
//				.build();
//		
//		PartResponse partResponse = PartResponse.builder()
//				.id(1l)
//				.name("Клавиатура")
//				.specification("HP")
//				.description("100% Den")
//				.buy(buy_1)
//				.sale(null)
//				.build();
//		
//		Mockito.when(partRepository.findById(PART_1.getId())).thenReturn(Optional.of(PART_1));
//		Mockito.when(partRepository.save(part)).thenReturn(part);
//		
//		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/api/part/1")
//				.contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON)
//				.content(this.mapper.writeValueAsString(partResponse));
//		
//		mockMvc.perform(mockRequest)
//			.andExpect(status().isOk())
//			.andExpect(jsonPath("$", notNullValue()))
//			.andExpect(jsonPath("$.id", is(1)))
//			.andExpect(jsonPath("$.name", is("Клавиатура")))
//			.andExpect(jsonPath("$.specification", is("HP")));
//	}
	
	@Test
	public void deletePartById_success() throws Exception {
		Mockito.when(partRepository.findById(PART_1.getId())).thenReturn(Optional.of(PART_1));
		
		mockMvc.perform(MockMvcRequestBuilders
				.delete("/api/part/1")
				.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());
	}
}
