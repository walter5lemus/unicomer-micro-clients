package com.unicomer.micro.clients.repository.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

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
	
	@NotNull
	@Length(min = 1, max = 50, message = "First name can not be more than 50 characters")
	private String firstName;
	
	@NotNull
	@Length(min = 1, max = 50, message = "Last name can not be more than 50 characters")
	private String lastName;

	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date birthday;

	@NotNull
	private Character gender;

	@NotNull
	@Length(min = 8, max = 20, message = "Cell Phone can not be more than 20 character")
	private String cellPhone;

	@NotNull
	@Length(min = 8, max = 20, message = "Home phone can not be more than 20 character")
	private String homePhone;

	@NotNull
	@Length(min = 1, max = 255, message = "Address can not be more than 255 character")
	private String addressHome;

	@NotNull
	@Length(min = 0, max = 50, message = "Profession can not be more than 50 character")
	private String profession;

	@NotNull
	private BigDecimal incomes;
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
