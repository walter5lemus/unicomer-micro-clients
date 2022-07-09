package com.unicomer.micro.clients.service.implementation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	public Map<String, Object> findAll(Integer pageNo, Integer pageSize) {
		List<ClientResponse> listClientResponse = new ArrayList<>();
		Map<String, Object> response = new HashMap<>();
		Pageable paging = PageRequest.of(pageNo, pageSize);
		log.info("Entrando al metodo findAll en ClientServiceImplementation");

		Page<Client> pagedResult = clientRepository.findAll(paging);

		if (pagedResult.hasContent()) {
			pagedResult.forEach(x -> listClientResponse.add(clientMapper.convertClientToClientResponse(x)));
		}

		response.put("clients", listClientResponse);
		response.put("currentPage", pagedResult.getNumber());
		response.put("totalItems", pagedResult.getTotalElements());
		response.put("totalPages", pagedResult.getTotalPages());
		return response;

	}

	@Override
	public ClientResponse findById(Long id) throws CustomResponseException {
		Optional<Client> client = clientRepository.findById(id);
		log.info("Entrando al metodo findById en ClientServiceImplementation");

		if (client.isEmpty()) {

			log.error("Error al buscar el cliente id " + id + ", no se encontró en la BD ");
			throw CustomErrorEnum.ErrorClient(CustomErrorEnum.ERROR_NO_CLIENT, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		log.info("Regresando información correctamente del metodo findById en ClientServiceImplementation");
		return clientMapper.convertClientToClientResponse(client.get());
	}

	@Override
	public ClientResponse save(ClientRequest clientRequest) throws CustomResponseException {
		log.info("Entrando al metodo save en ClientServiceImplementation");
		
		if(clientRequest.getBirthday().isAfter(LocalDateTime.now())){
			throw CustomErrorEnum.ErrorClient(CustomErrorEnum.ERROR_BIRTHDAY, HttpStatus.BAD_REQUEST);
		}
		
		Client client = clientMapper.convertClientRequestToClient(clientRequest);
		

		log.info("Regresando información correctamente del metodo save en ClientServiceImplementation");
		return clientMapper.convertClientToClientResponse(clientRepository.save(client));
	}

	@Override
	public ClientResponse update(ClientRequest clientRequest, Long id) throws CustomResponseException {
		Optional<Client> currentClient = clientRepository.findById(id);
		log.info("Entrando al metodo update en ClientServiceImplementation");
		
		if(clientRequest.getBirthday().isAfter(LocalDateTime.now())){
			throw CustomErrorEnum.ErrorClient(CustomErrorEnum.ERROR_BIRTHDAY, HttpStatus.BAD_REQUEST);
		}

		if (currentClient.isPresent()) {

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
		} else {

			log.error("Error actualizando el cliente id " + id + ", no se encontró en la BD ");
			throw CustomErrorEnum.ErrorClient(CustomErrorEnum.ERROR_UPDATE_CLIENT, HttpStatus.BAD_REQUEST);
		}

	}

}
