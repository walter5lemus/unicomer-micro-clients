package com.unicomer.micro.clients.controller.response;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ClientResponse {
	
	private Long idClient;
	
	private String firstName;
	
	private String lastName;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date birthday;
	
	private Character gender;
	
	private String cellPhone;
	
	private String homePhone;
	
	private String addressHome;
	
	private String profession;
	
	private BigDecimal incomes;

}