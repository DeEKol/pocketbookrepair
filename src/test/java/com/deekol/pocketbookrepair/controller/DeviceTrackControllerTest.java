package com.deekol.pocketbookrepair.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.deekol.pocketbookrepair.model.DeviceTrackEntity;
import com.deekol.pocketbookrepair.repository.DeviceTrackRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(DeviceTrackController.class)
public class DeviceTrackControllerTest {
	@Autowired
	MockMvc mockMvc;
	@Autowired
	ObjectMapper mapper;
	
	@MockBean
	DeviceTrackRepository deviceTrackRepository;
	
	BigDecimal buy_1 = new BigDecimal("20000");
	BigDecimal sale_1 = new BigDecimal("35000");
	BigDecimal buy_2 = new BigDecimal("9000");
	BigDecimal sale_2 = new BigDecimal("15000");
	BigDecimal buy_3 = new BigDecimal("12000");
	BigDecimal sale_3 = new BigDecimal("20000");
	
	DeviceTrackEntity DEVICE_1 = new DeviceTrackEntity(1l, "Apple", "Air", null, null, "good", "100% Den", buy_1, null, null, "Intel i3", "UHD600", 12000, 512000, true, null, null);
	DeviceTrackEntity DEVICE_2 = new DeviceTrackEntity(2l, "Acer", "Apire70", null, null, "bad", "100% Den", buy_2, null, null, "Intel i5", "MX250", 8000, 256000, false, null, null);
	DeviceTrackEntity DEVICE_3 = new DeviceTrackEntity(3l, "Dell", "UD20", null, null, "new", "100% Den", buy_3, sale_3, null, "Amd A8", "HD7870", 16000, 512000, true, null, null);
	DeviceTrackEntity[] dArr = {DEVICE_1, DEVICE_2, DEVICE_3};
	
	@SuppressWarnings("unchecked")
	@Test
	public void getAllDeviceTrack_success() throws Exception {
		List<?> devices = new ArrayList<>(Arrays.asList(dArr));
		
		Mockito.when(deviceTrackRepository.findAll()).thenReturn((List<DeviceTrackEntity>) devices);
	
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/deviceTrack")
				.contentType(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("$", hasSize(3)))
					.andExpect(jsonPath("$[2].maker", is("Dell")))
					.andExpect(jsonPath("$[2].name", is("UD20")));
	}
	
	@Test
	public void getDeviceTrackById_success() throws Exception {
		Mockito.when(deviceTrackRepository.findById(DEVICE_1.getId())).thenReturn(Optional.of(DEVICE_1));
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/deviceTrack/1")
				.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$", notNullValue()))
					.andExpect(jsonPath("$.maker", is("Apple")))
					.andExpect(jsonPath("$.name", is("Air")));
	}
	
	@Test
	public void addDeviceTrack_success() throws Exception {
		BigDecimal deviceBuy = new BigDecimal("20000");
		BigDecimal deviceSale = new BigDecimal("30000");
		
		DeviceTrackEntity device = DeviceTrackEntity.builder()
				.maker("Huawei")
				.name("d14")
				.specification("d1412")
				.description("d1412dk")
				.buy(deviceBuy)
				.sale(deviceSale)
				.creationDate(LocalDate.now())
				.cpu("Celeron")
				.gpu("UHD400")
				.ram(4000)
				.hdd(120000)
				.build();
		
		Mockito.when(deviceTrackRepository.save(device)).thenReturn(device);
		
		MockHttpServletRequestBuilder mockRequestBuilder = MockMvcRequestBuilders.post("/api/deviceTrack")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(device));
		
		mockMvc.perform(mockRequestBuilder)
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", notNullValue()))
			.andExpect(jsonPath("$.maker", is("Huawei")))
			.andExpect(jsonPath("$.name", is("d14")));
	}
	
//	@Test
//	public void updateDevice_success() throws Exception {
//		DeviceEntity device = DeviceEntity.builder()
//				.id(1l)
//				.maker("HP")
//				.name("G6")
//				.specification("i5, UHD600")
//				.description("100% Den")
//				.buy(buy_1)
//				.sale(sale_1)
//				.defect("клавиатура")
//				.build();
//		
//		Mockito.when(deviceRepository.findById(DEVICE_1.getId())).thenReturn(Optional.of(DEVICE_1));
//		Mockito.when(deviceRepository.save(device)).thenReturn(device);
//		
//		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/api/device/1")
//				.contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON)
//				.content(this.mapper.writeValueAsString(device));
//		
//		mockMvc.perform(mockRequest)
//			.andExpect(status().isOk())
//			.andExpect(jsonPath("$", notNullValue()))
//			.andExpect(jsonPath("$.maker", is("HP")))
//			.andExpect(jsonPath("$.specification", is("i5, UHD600")));
//	}
	
	@Test
	public void deleteDeviceTrackById_success() throws Exception {
		Mockito.when(deviceTrackRepository.findById(DEVICE_1.getId())).thenReturn(Optional.of(DEVICE_1));
		
		mockMvc.perform(MockMvcRequestBuilders
				.delete("/api/deviceTrack/1")
				.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());
	}
}
