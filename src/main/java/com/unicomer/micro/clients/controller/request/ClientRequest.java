package com.unicomer.micro.clients.controller.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

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
public class ClientRequest{

	@NotNull(message = "First name can not be null")
	@Size(min = 1, max = 50, message = "First name can not be more than 50 characters")
	private String firstName;
	
	@NotNull(message = "Last name can not be null")
	@Size(min = 1, max = 50, message = "Last name can not be more than 50 characters")
	private String lastName;
	
	@NotNull(message = "Birthday can not be null")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime birthday;
	
	@NotNull(message = "Gender can not be null")
	private Character gender;
	
	@NotNull(message = "Cell phone can not be null")
	@Size(min = 8, max = 20, message = "Cell Phone can not be more than 20 character")
	private String cellPhone;
	
	@NotNull(message = "Home phone can not be null")
	@Size(min = 8, max = 20, message = "Home phone can not be more than 20 character")
	private String homePhone;
	
	@NotNull(message = "Address can not be null")
	@Size(min = 1, max = 255, message = "Address can not be more than 255 character")
	private String addressHome;
	
	@NotNull(message = "Profession can not be null")
	@Size(min = 0, max = 50, message = "Profession can not be more than 50 character")
	private String profession;
	
	@NotNull(message = "Incomes can not be null")
	private BigDecimal incomes;
	
}
