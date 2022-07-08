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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unicomer.micro.clients.configuration.exception.CustomResponseException;
import com.unicomer.micro.clients.controller.request.ClientRequest;
import com.unicomer.micro.clients.service.ClientService;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "clients")
@RequiredArgsConstructor
@Api(value = "/clients", tags = "Clientes", description = "Permite el manejo de clientes")
public class ClientController {

	@NonNull
	private ClientService clientService;

	@GetMapping
	@Operation(summary = "Obtiene todos los clientes", description = "Este método  permite obtener los clientes mediante paginas")
	public ResponseEntity<?> getAllClient(@RequestParam(name = "Numero de pagina", defaultValue = "0") Integer pageNo,
			@RequestParam(name = "Cantidad de elementos", defaultValue = "2") Integer pageSized) {
		
		log.info("Entrando al metodo getAll en ClientController");
		return new ResponseEntity<>(clientService.findAll(pageNo, pageSized), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Permite obtener un cliente", description = "Este método permite obtener un "
			+ "cliente en específico mediante un identificador")
	public ResponseEntity<?> getClientById(@PathVariable(name = "Id del cliente") Long id)
			throws CustomResponseException {
		
		
		log.info("Entrando al metodo getClientById en ClientController");
		return new ResponseEntity<>(clientService.findById(id), HttpStatus.CREATED);
	}

	@PostMapping()
	@Operation(summary = "Permite guardar un cliente", description = "Este método permite guardar un "
			+ "cliente en específico")
	public ResponseEntity<?> saveClient(@Valid @RequestBody ClientRequest clientRequest) {
		
		
		log.info("Entrando al metodo saveClient en ClientController");
		return new ResponseEntity<>(clientService.save(clientRequest), HttpStatus.CREATED);

	}

	@PutMapping("/{id}")
	@Operation(summary = "Permite actualizar un cliente", description = "Este método permite actualizar un "
			+ "cliente en específico mediante un identificador y los campos a actualizar")
	public ResponseEntity<?> updateClient(@Valid @RequestBody ClientRequest clientRequest,
			@PathVariable(name = "Id del cliente") Long id) throws CustomResponseException {
		
		
		log.info("Entrando al metodo updateClient en ClientController");
		return new ResponseEntity<>(clientService.update(clientRequest, id), HttpStatus.OK);

	}

}
