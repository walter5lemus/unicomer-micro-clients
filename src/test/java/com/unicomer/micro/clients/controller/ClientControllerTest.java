package com.unicomer.micro.clients.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.google.gson.Gson;
import com.unicomer.micro.clients.configuration.exception.CustomResponseException;
import com.unicomer.micro.clients.controller.request.ClientRequest;
import com.unicomer.micro.clients.controller.response.ClientResponse;

@SpringBootTest
public class ClientControllerTest {
	
	@Autowired
	private ClientController clientController;
	
	@Test
	@DisplayName("Controller get all clients in DB")
	public void getAllClient() {
		   
		Integer pageNo = 0;
		Integer pageSize = 10;
		
		ResponseEntity<?> response = clientController.getAllClient(pageNo, pageSize);
		
		assertNotNull(response);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		
	}
	
	@Test
	@DisplayName("Controller find client by id in DB")
	public void findById() throws CustomResponseException {
		 ResponseEntity<?> response = clientController.getClientById(1L);
		 
		 assertNotNull(response);
		 assertEquals(response.getStatusCode(), HttpStatus.OK);
		 
		 System.out.println(response.getBody());
			
		 ClientResponse clientResponse  = (ClientResponse) response.getBody();
		 
		 assertEquals(clientResponse.getFirstName(),"Walter");
		 assertEquals(clientResponse.getLastName(),"Lemus");
		
	}
	
	@Test
	@DisplayName("Controller save client in DB")
	public void saveClientController() throws CustomResponseException{
		
		ClientRequest client = ClientRequest.builder()
			 .firstName("Wendy")
			 .lastName("Martinez")
			 .birthday(LocalDateTime.now())
			 .gender('F')
			 .cellPhone("+50374747474")
			 .homePhone("+50378788787")
			 .addressHome("Zacatecoluca")
			 .profession("Licenciada en enfermería")
			 .incomes(new BigDecimal(800.00))
			 .build();
	 
		ResponseEntity<?> clientResponse = clientController.saveClient(client);
		
		assertNotNull(clientResponse);
		assertEquals(clientResponse.getStatusCode(), HttpStatus.CREATED);
	 
	}
	
	@Test
	@DisplayName("Controller save client in DB with error in birthday")
	public void saveClientControllerwithErrorBirthDay() throws CustomResponseException{
		
		ClientRequest client = ClientRequest.builder()
			 .firstName("Wendy")
			 .lastName("Martinez")
			 .birthday(LocalDateTime.now().plusDays(2))
			 .gender('F')
			 .cellPhone("+50374747474")
			 .homePhone("+50378788787")
			 .addressHome("Zacatecoluca")
			 .profession("Licenciada en enfermería")
			 .incomes(new BigDecimal(800.00))
			 .build();
	 
		
		assertThrows(CustomResponseException.class, () -> clientController.saveClient(client));
	}

	@Test
	@DisplayName("Controller update client birthday is in future day in DB")
	public void updateClientBirhtdayNotCorrect() throws CustomResponseException {
	
		ClientRequest client = ClientRequest.builder()
				 .firstName("Wendy")
				 .lastName("Martinez")
				 .birthday(LocalDateTime.now())
				 .gender('F')
				 .cellPhone("+50374747474")
				 .homePhone("+50378788787")
				 .addressHome("Zacatecoluca")
				 .profession("Licenciada en enfermería")
				 .incomes(new BigDecimal(800.00))
				 .build();
		
		ResponseEntity<?> response=  clientController.saveClient(client);
		
		assertNotNull(response);
		assertEquals(response.getStatusCode(), HttpStatus.CREATED);
		
		ClientResponse clientResponse  = (ClientResponse) response.getBody();
		
		client.setBirthday(LocalDateTime.now().plusMinutes(5));
		
		assertThrows(CustomResponseException.class, () -> clientController.updateClient(client, clientResponse.getIdClient()));
	}
	
	@Test
	@DisplayName("Controller Update client in DB")
	public void updateClientController() throws CustomResponseException {
	
		ClientRequest client = ClientRequest.builder()
				 .firstName("Wendy")
				 .lastName("Martinez")
				 .birthday(LocalDateTime.now())
				 .gender('F')
				 .cellPhone("+50374747474")
				 .homePhone("+50378788787")
				 .addressHome("Zacatecoluca")
				 .profession("Licenciada en enfermería")
				 .incomes(new BigDecimal(800.00))
				 .build();
		 
		ResponseEntity<?> response = clientController.saveClient(client);
		
		assertNotNull(response);
		assertEquals(response.getStatusCode(), HttpStatus.CREATED);
		
		ClientResponse clientResponse  = (ClientResponse) response.getBody();
		
		client.setFirstName("Zoila");
		client.setLastName("Vasquez");
		
		response = clientController.updateClient(client, clientResponse.getIdClient());
		ClientResponse clientResponse2  = (ClientResponse) response.getBody();
		 assertNotNull(response);

		 assertEquals(clientResponse2.getFirstName(),client.getFirstName());
		 assertEquals(clientResponse2.getLastName(),client.getLastName());
		
	}
	
}
