package com.unicomer.micro.clients.service.mapper;

import org.mapstruct.Mapper;

import com.unicomer.micro.clients.controller.request.ClientRequest;
import com.unicomer.micro.clients.controller.response.ClientResponse;
import com.unicomer.micro.clients.repository.entity.Client;

@Mapper(componentModel = "spring")
public interface ClientMapper {
	
	ClientResponse convertClientToClientResponse(Client client);
	
	Client convertClientRequestToClient(ClientRequest client);
}
