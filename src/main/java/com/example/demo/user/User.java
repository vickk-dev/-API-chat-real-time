package com.example.demo.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;



@Data
@Document(collation = "User")
public class User {

	@Id
	private String id;
	
	private String name;
	
	@Email
	private String email;
	
	@Pattern(regexp = "^\\+?[1-9]\\d{1,14}$|^\\([1-9]{2}\\)\\s9?[0-9{4}-][0-9{4}]$")
	private String phoneNumber;

	
	
	
	
	
}
