package com.unicomer.micro.clients.service;

import com.unicomer.micro.clients.controller.request.ClientRequest;
import com.unicomer.micro.clients.controller.response.ClientResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ClientServiceImplementationTest  {

	@Autowired
    private ClientService clientService;
	
	@Test
	@DisplayName("Service get all clients in DB")
	public void getAllClient() throws Exception {
		Map<String, Object> response = new HashMap<>(); 
		Integer pageNo = 0;
		Integer pageSize = 10;
		
		response = clientService.findAll(pageNo, pageSize);
		
		assertFalse(response.isEmpty());
		assertNotNull(response);
		
	}
	
	@Test
	@DisplayName("Find client by id in DB")
	public void findById() throws Exception {
		 ClientResponse client = clientService.findById(1L);
		 assertNotNull(client);
		 
		 assertEquals(client.getIdClient(),1L);
		 assertEquals(client.getFirstName(),"Walter");
		 assertEquals(client.getLastName(),"Lemus");
		
	}
	
	@Test
	@DisplayName("save client in DB")
	public void save() throws Exception {
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
		 
		ClientResponse clientResponse = clientService.save(client);
		 
		 ClientResponse clientDB = clientService.findById(clientResponse.getIdClient());
		 assertNotNull(clientDB);

		 assertEquals(clientDB.getFirstName(),client.getFirstName());
		 assertEquals(clientDB.getLastName(),client.getLastName());
		
	}
	
	@Test
	@DisplayName("save client birthday is in future day in DB")
	public void saveBirhtdayNotCorrect() throws Exception {
		ClientRequest client = ClientRequest.builder()
				 .firstName("Wendy")
				 .lastName("Martinez")
				 .birthday(LocalDateTime.now().plusDays(1))
				 .gender('F')
				 .cellPhone("+50374747474")
				 .homePhone("+50378788787")
				 .addressHome("Zacatecoluca")
				 .profession("Licenciada en enfermería")
				 .incomes(new BigDecimal(800.00))
				 .build();
		
			assertThrows(Exception.class, () -> clientService.save(client));
		  
	}
	
	@Test
	@DisplayName("Update client birthday is in future day in DB")
	public void updateClientBirhtdayNotCorrect() throws Exception {
	
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
		 
		ClientResponse clientResponse=  clientService.save(client);
		
		client.setBirthday(LocalDateTime.now().plusMinutes(5));
		
		assertThrows(Exception.class, () -> clientService.update(client, clientResponse.getIdClient()));
		
	}
	
	@Test
	@DisplayName("Update client in DB")
	public void updateClient() throws Exception {
	
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
		 
		ClientResponse clientResponse=  clientService.save(client);
		
		client.setFirstName("Zoila");
		client.setLastName("Vasquez");
		
		clientResponse = clientService.update(client, 6L);
		
		 assertNotNull(clientResponse);

		 assertEquals(clientResponse.getFirstName(),client.getFirstName());
		 assertEquals(clientResponse.getLastName(),client.getLastName());
		
	}

}
