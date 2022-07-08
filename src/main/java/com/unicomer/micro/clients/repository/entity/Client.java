package com.unicomer.micro.clients.repository.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(name = "clients")
public class Client implements Serializable{

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long idClient;
	
	private String firstName;
	
	private String lastName;
	
	private Date birthday;
	
	private Character gender;
	
	private String cellPhone;
	
	private String homePhone;
	
	private String addressHome;
	
	private String profession;
	
	private BigDecimal incomes;
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
