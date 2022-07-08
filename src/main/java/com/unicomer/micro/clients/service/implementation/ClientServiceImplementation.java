package com.unicomer.micro.clients.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.unicomer.micro.clients.configuration.CustomErrorEnum;
import com.unicomer.micro.clients.configuration.exception.CustomResponseException;
import com.unicomer.micro.clients.controller.ClientController;
import com.unicomer.micro.clients.controller.request.ClientRequest;
import com.unicomer.micro.clients.controller.response.ClientResponse;
import com.unicomer.micro.clients.repository.ClientRepository;
import com.unicomer.micro.clients.repository.entity.Client;
import com.unicomer.micro.clients.service.ClientService;
import com.unicomer.micro.clients.service.mapper.ClientMapper;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientServiceImplementation implements ClientService {
	
	@NonNull
	private ClientRepository clientRepository;
	
	@NonNull
	private ClientMapper clientMapper;

	@Override
	public List<ClientResponse> findAll() {
		List<ClientResponse> listClientResponse = new ArrayList<>();
		log.info("Entrando al metodo findAll en ClientServiceImplementation");
		
		List<Client> listClient = clientRepository.findAll();
		listClient.forEach(x -> listClientResponse.add(
				clientMapper.convertClientToClientResponse(x)
				));
		
		log.info("Regresando información correctamente del metodo findAll en ClientServiceImplementation");
		return listClientResponse;
	}

	@Override
	public ClientResponse findById(Long id) throws CustomResponseException {
		Optional<Client> client = clientRepository.findById(id);
		log.info("Entrando al metodo findById en ClientServiceImplementation");
		
		if(client.isEmpty()) {
			
			log.error("Error al buscar el cliente id " + id + ", no se encontró en la BD ");
			throw CustomErrorEnum.ErrorClient(CustomErrorEnum.ERROR_NO_CLIENT, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		log.info("Regresando información correctamente del metodo findById en ClientServiceImplementation");
		return clientMapper.convertClientToClientResponse(client.get());
	}

	@Override
	public ClientResponse save(ClientRequest clientRequest) {
		log.info("Entrando al metodo save en ClientServiceImplementation");
		Client client = clientMapper.convertClientRequestToClient(clientRequest);
		
		log.info("Regresando información correctamente del metodo save en ClientServiceImplementation");
		return clientMapper.convertClientToClientResponse(clientRepository.save(client));
	}

	@Override
	public ClientResponse update(ClientRequest clientRequest, Long id) throws CustomResponseException {
		Optional<Client> currentClient = clientRepository.findById(id);
		log.info("Entrando al metodo update en ClientServiceImplementation");
		
		if(currentClient.isPresent()) {
			
			Client ClientMapper = clientMapper.convertClientRequestToClient(clientRequest);
			Client client = currentClient.get();
			
			client.setFirstName(ClientMapper.getFirstName());
			client.setLastName(ClientMapper.getLastName());
			client.setBirthday(ClientMapper.getBirthday());
			client.setGender(ClientMapper.getGender());
			client.setCellPhone(ClientMapper.getCellPhone());
			client.setHomePhone(ClientMapper.getHomePhone());
			client.setAddressHome(ClientMapper.getAddressHome());
			client.setProfession(ClientMapper.getProfession());
			client.setIncomes(ClientMapper.getIncomes());
			
			log.info("Regresando información correctamente del metodo update en ClientServiceImplementation");
			return clientMapper.convertClientToClientResponse(clientRepository.save(client));
		}else {
			
			log.error("Error actualizando el cliente id " + id + ", no se encontró en la BD ");
			throw CustomErrorEnum.ErrorClient(CustomErrorEnum.ERROR_UPDATE_CLIENT, HttpStatus.BAD_REQUEST);
		}
		
		
	}

}
