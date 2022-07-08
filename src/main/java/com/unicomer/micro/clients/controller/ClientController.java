package com.unicomer.micro.clients.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unicomer.micro.clients.configuration.exception.CustomResponseException;
import com.unicomer.micro.clients.controller.request.ClientRequest;
import com.unicomer.micro.clients.service.ClientService;

import io.micrometer.core.ipc.http.HttpSender.Response;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "clients")
@RequiredArgsConstructor
public class ClientController {

	@NonNull
	private ClientService clientService;

	@GetMapping
	public ResponseEntity<?> getAll() {
		return new ResponseEntity<>(clientService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getClientById(@PathVariable Long id) throws CustomResponseException {
		return new ResponseEntity<>(clientService.findById(id), HttpStatus.CREATED);
	}

	@PostMapping()
	public ResponseEntity<?> saveClient(@Valid @RequestBody ClientRequest clientRequest) {
		return new ResponseEntity<>(clientService.save(clientRequest), HttpStatus.CREATED);

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateClient(@Valid @RequestBody ClientRequest clientRequest, @PathVariable Long id) {
		return new ResponseEntity<>(clientService.save(clientRequest), HttpStatus.OK);

	}

}
