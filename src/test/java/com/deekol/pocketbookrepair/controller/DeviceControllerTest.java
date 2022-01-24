package com.deekol.pocketbookrepair.controller;

import java.math.BigDecimal;
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

import com.deekol.pocketbookrepair.model.DeviceEntity;
import com.deekol.pocketbookrepair.repository.DeviceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(DeviceController.class)
public class DeviceControllerTest {
	@Autowired
	MockMvc mockMvc;
	@Autowired
	ObjectMapper mapper;
	
	@MockBean
	DeviceRepository deviceRepository;
	
	BigDecimal buy_1 = new BigDecimal("20000");
	BigDecimal sale_1 = new BigDecimal("35000");
	BigDecimal buy_2 = new BigDecimal("9000");
	BigDecimal sale_2 = new BigDecimal("15000");
	BigDecimal buy_3 = new BigDecimal("12000");
	BigDecimal sale_3 = new BigDecimal("20000");
	
	DeviceEntity DEVICE_1 = new DeviceEntity(1l, "HP", "Intel i3, UHD600", "100% Den", buy_1, sale_1, "Intel i3", "UHD600", 8, 512, false, "клавиатура");
	DeviceEntity DEVICE_2 = new DeviceEntity(2l, "Dell", "AMD A8, 7970HD", "100% Den", buy_2, sale_2, "AMD A8", "7970HD", 4, 256, true, "матрица");
	DeviceEntity DEVICE_3 = new DeviceEntity(3l, "Asus", "Intel i5, mx330", "100% Den", buy_3, sale_3, "Intel i5", "mx330", 12, 512, false, null);
	DeviceEntity[] dArr = {DEVICE_1, DEVICE_2, DEVICE_3};
	
	@SuppressWarnings("unchecked")
	@Test
	public void getAllDevices_success() throws Exception {
		List<?> devices = new ArrayList<>(Arrays.asList(dArr));
		
		Mockito.when(deviceRepository.findAll()).thenReturn((List<DeviceEntity>) devices);
	
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/device")
				.contentType(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("$", hasSize(3)))
					.andExpect(jsonPath("$[2].name", is("Asus")));
	}
	
	@Test
	public void getDeviceById_success() throws Exception {
		Mockito.when(deviceRepository.findById(DEVICE_1.getId())).thenReturn(Optional.of(DEVICE_1));
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/device/1")
				.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$", notNullValue()))
					.andExpect(jsonPath("$.name", is("HP")));
	}
	
	@Test
	public void addDevice_success() throws Exception {
		BigDecimal deviceBuy = new BigDecimal("20000");
		BigDecimal deviceSale = new BigDecimal("30000");
		
		DeviceEntity device = DeviceEntity.builder()
				.name("Huawei")
				.specification("i7, UHD")
				.description("100% Den")
				.buy(deviceBuy)
				.sale(deviceSale)
				.build();
		
		Mockito.when(deviceRepository.save(device)).thenReturn(device);
		
		MockHttpServletRequestBuilder mockRequestBuilder = MockMvcRequestBuilders.post("/api/device")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(device));
		
		mockMvc.perform(mockRequestBuilder)
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", notNullValue()))
			.andExpect(jsonPath("$.name", is("Huawei")));
	}
	
	@Test
	public void updateDevice_success() throws Exception {
		DeviceEntity device = DeviceEntity.builder()
				.id(1l)
				.name("HP")
				.specification("i5, UHD600")
				.description("100% Den")
				.buy(buy_1)
				.sale(sale_1)
				.defect("клавиатура")
				.build();
		
		Mockito.when(deviceRepository.findById(DEVICE_1.getId())).thenReturn(Optional.of(DEVICE_1));
		Mockito.when(deviceRepository.save(device)).thenReturn(device);
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/device")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(device));
		
		mockMvc.perform(mockRequest)
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", notNullValue()))
			.andExpect(jsonPath("$.name", is("HP")))
			.andExpect(jsonPath("$.specification", is("i5, UHD600")));
	}
	
	@Test
	public void deleteDeviceById_success() throws Exception {
		Mockito.when(deviceRepository.findById(DEVICE_1.getId())).thenReturn(Optional.of(DEVICE_1));
		
		mockMvc.perform(MockMvcRequestBuilders
				.delete("/api/device/1")
				.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());
	}
}
