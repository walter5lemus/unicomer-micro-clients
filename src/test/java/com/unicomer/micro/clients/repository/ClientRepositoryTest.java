package com.unicomer.micro.clients.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.unicomer.micro.clients.repository.entity.Client;

@DataJpaTest
public class ClientRepositoryTest {

	@Autowired
	private ClientRepository clientRepository;
	
	@Test
	@DisplayName("Find all clients in DB")
	public void findAll() {
		List<Client> client = (List<Client>) clientRepository.findAll();
		assertFalse(client.isEmpty());
		assertEquals(5, client.size());
	}
	
	
	@Test
	@DisplayName("Find first client in DB")
	public void findById() {
		Optional<Client> client = clientRepository.findById(1L);
        assertTrue(client.isPresent());
        assertEquals("Walter", client.orElseThrow().getFirstName());
	}
	
	@Test
	@DisplayName("Save client in DB")
	public void saveClient() {
		 Client client = Client.builder()
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
		 
		 clientRepository.save(client);
		 
		 Optional<Client> clientDB = clientRepository.findById(6L);
		 assertTrue(clientDB.isPresent());
		 assertEquals(clientDB.get(),client);
	}
	
	@Test
	@DisplayName("Update client in DB")
	public void updateClient() {
	
		Optional<Client> clientDB = clientRepository.findById(1L);
		
		assertTrue(clientDB.isPresent());
		
		clientDB.orElseThrow().setFirstName("Oscar");
		clientDB.orElseThrow().setLastName("Martinez");
		 
		clientRepository.save(clientDB.get());
		 
		Optional<Client> clientDBNew = clientRepository.findById(1L);
		assertTrue(clientDBNew.isPresent());
		assertEquals(clientDBNew.orElseThrow().getFirstName(),"Oscar");
		assertEquals(clientDBNew.orElseThrow().getLastName(),"Martinez");
		
	}
	
	
}
