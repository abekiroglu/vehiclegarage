package com.abekiroglu.vehicle;

import com.abekiroglu.vehicle.api.controller.VehicleController;
import com.abekiroglu.vehicle.api.dto.request.LeaveRequest;
import com.abekiroglu.vehicle.api.dto.request.ParkRequest;
import com.abekiroglu.vehicle.api.dto.request.VehicleRequest;
import com.abekiroglu.vehicle.api.dto.response.Response;
import com.abekiroglu.vehicle.api.dto.response.StatusResponse;
import com.abekiroglu.vehicle.api.service.VehicleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RequestBody;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(VehicleController.class)
class VehicleApplicationTests {
	@Autowired
	private MockMvc mvc;
	@MockBean
	private VehicleService service;
	@Test
	void contextLoads() {
	}
	//post
	@Test
	public void givenTypeAndColorAndPlateNo_whenPostVehicle_thenReturnVehicleObject() throws Exception {
		VehicleRequest request = new VehicleRequest("jeep", "34-SA-1111", "red");
		ResponseEntity response = service.postVehicle(request);
		given(service.postVehicle(request)).willReturn(response);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson=ow.writeValueAsString(request);

		mvc.perform(post("/v1/vehicles/")
						.contentType(MediaType.APPLICATION_JSON)
						.content(requestJson))
				.andExpect(status().isOk());
	}
	@Test
	public void givenTypeAndColor_whenPostVehicle_thenReturnVehicleObject() throws Exception {
		VehicleRequest request = new VehicleRequest("jeep", null, "red");
		ResponseEntity response = service.postVehicle(request);
		given(service.postVehicle(request)).willReturn(response);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson=ow.writeValueAsString(request);

		mvc.perform(post("/v1/vehicles/")
						.contentType(MediaType.APPLICATION_JSON)
						.content(requestJson))
				.andExpect(status().isOk());
	}
	@Test
	public void givenTypeAndPlateNo_whenPostVehicle_thenReturnVehicleObject() throws Exception {
		VehicleRequest request = new VehicleRequest("jeep", null, "34-SA-1111");
		ResponseEntity response = service.postVehicle(request);
		given(service.postVehicle(request)).willReturn(response);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson=ow.writeValueAsString(request);

		mvc.perform(post("/v1/vehicles/")
						.contentType(MediaType.APPLICATION_JSON)
						.content(requestJson))
				.andExpect(status().isOk());
	}
	@Test
	public void givenType_whenPostVehicle_thenReturnVehicleObject() throws Exception {
		VehicleRequest request = new VehicleRequest("jeep", null, null);
		ResponseEntity response = service.postVehicle(request);
		given(service.postVehicle(request)).willReturn(response);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson=ow.writeValueAsString(request);

		mvc.perform(post("/v1/vehicles/")
						.contentType(MediaType.APPLICATION_JSON)
						.content(requestJson))
				.andExpect(status().isOk());
	}
	@Test
	public void givenColor_whenPostVehicle_thenReturnError() throws Exception {
		VehicleRequest request = new VehicleRequest(null, null, "red");
		ResponseEntity response = service.postVehicle(request);
		given(service.postVehicle(request)).willReturn(response);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson=ow.writeValueAsString(request);

		mvc.perform(post("/v1/vehicles/")
						.contentType(MediaType.APPLICATION_JSON)
						.content(requestJson))
				.andExpect(status().isOk());
	}
	@Test
	public void givenPlateNo_whenPostVehicle_thenReturnError() throws Exception {
		VehicleRequest request = new VehicleRequest(null, "34-SA-1154", null);
		ResponseEntity response = service.postVehicle(request);
		given(service.postVehicle(request)).willReturn(response);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson=ow.writeValueAsString(request);

		mvc.perform(post("/v1/vehicles/")
						.contentType(MediaType.APPLICATION_JSON)
						.content(requestJson))
				.andExpect(status().isOk());
	}
	//park
	@Test
	public void givenValidParkString_whenPark_thenReturnNotFound() throws Exception{
		ParkRequest request = new ParkRequest("34-HBO-2020 Black Truck");
		ResponseEntity response = service.parkVehicle(request);
		given(service.parkVehicle(request)).willReturn(response);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson=ow.writeValueAsString(request);

		mvc.perform(post("/v1/vehicles/park/")
						.contentType(MediaType.APPLICATION_JSON)
						.content(requestJson))
				.andExpect(status().isNotFound());
	}
	//leave
	@Test
	public void givenValidLeaveIntegerButNoCars_whenLeave_thenReturnOk() throws Exception{
		LeaveRequest leaveRequest = new LeaveRequest(1);
		ResponseEntity leaveResponse = service.leaveVehicle(leaveRequest);
		given(service.leaveVehicle(leaveRequest)).willReturn(leaveResponse);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson=ow.writeValueAsString(leaveRequest);

		mvc.perform(post("/v1/vehicles/leave")
						.contentType(MediaType.APPLICATION_JSON)
						.content(requestJson))
				.andExpect(status().isOk());
	}
	//get
	@Test
	public void givenNoCars_whenStatus_thenReturnOk() throws Exception{
		ResponseEntity leaveResponse = service.getGarageStatus();
		given(service.getGarageStatus()).willReturn(leaveResponse);

		mvc.perform(get("/v1/vehicles/status")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
}
