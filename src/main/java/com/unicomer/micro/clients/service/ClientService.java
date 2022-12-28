package com.unicomer.micro.clients.service;

import com.unicomer.micro.clients.controller.request.ClientRequest;
import com.unicomer.micro.clients.controller.response.ClientResponse;

import java.util.Map;

public interface ClientService {
	
	Map<String, Object> findAll(Integer pageNo, Integer pageSize) throws Exception;
	
	ClientResponse findById(Long id) throws Exception;

	ClientResponse save(ClientRequest clientRequest) throws Exception;
	
	ClientResponse update(ClientRequest clientRequest, Long id) throws Exception;

}
