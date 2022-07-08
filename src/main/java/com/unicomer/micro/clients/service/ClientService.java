package com.unicomer.micro.clients.service;

import java.util.Map;

import com.unicomer.micro.clients.configuration.exception.CustomResponseException;
import com.unicomer.micro.clients.controller.request.ClientRequest;
import com.unicomer.micro.clients.controller.response.ClientResponse;

public interface ClientService {
	
	public Map<String, Object> findAll(Integer pageNo, Integer pageSize);
	
	public ClientResponse findById(Long id) throws CustomResponseException;

	public ClientResponse save(ClientRequest clientRequest);
	
	public ClientResponse update(ClientRequest clientRequest, Long id) throws CustomResponseException;

}
