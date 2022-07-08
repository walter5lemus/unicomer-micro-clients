package com.unicomer.micro.clients.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.unicomer.micro.clients.configuration.CustomErrorEnum;
import com.unicomer.micro.clients.configuration.exception.CustomResponseException;
import com.unicomer.micro.clients.controller.request.ClientRequest;
import com.unicomer.micro.clients.controller.response.ClientResponse;
import com.unicomer.micro.clients.repository.ClientRepository;
import com.unicomer.micro.clients.repository.entity.Client;
import com.unicomer.micro.clients.service.ClientService;
import com.unicomer.micro.clients.service.mapper.ClientMapper;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

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
		 
		List<Client> listClient = clientRepository.findAll();
		listClient.forEach(x -> listClientResponse.add(
				clientMapper.convertClientToClientResponse(x)
				));
		
		return listClientResponse;
	}

	@Override
	public ClientResponse findById(Long id) throws CustomResponseException {
		Optional<Client> client = clientRepository.findById(id);
		if(client.isEmpty()) {
			throw CustomErrorEnum.ErrorClient(CustomErrorEnum.ERROR_NO_CLIENT, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return clientMapper.convertClientToClientResponse(client.get());
	}

	@Override
	public ClientResponse save(ClientRequest clientRequest) {
		Client client = clientMapper.convertClientRequestToClient(clientRequest);
		return clientMapper.convertClientToClientResponse(clientRepository.save(client));
	}

	@Override
	public ClientResponse update(ClientRequest clientRequest, Long id) throws CustomResponseException {
		Optional<Client> currentClient = clientRepository.findById(id);
		
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
			
			return clientMapper.convertClientToClientResponse(clientRepository.save(client));
		}else {
			throw CustomErrorEnum.ErrorClient(CustomErrorEnum.ERROR_UPDATE_CLIENT, HttpStatus.BAD_REQUEST);
		}
		
		
	}

}
