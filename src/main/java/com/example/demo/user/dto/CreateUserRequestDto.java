package com.example.demo.user.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateUserRequestDto {
	
	@NotBlank (message ="Nome do Usuario é obrigatorio.")
	@Size(min = 2, max = 50, message = "O nome deve ter entre 2 e 50 caracteres.")
	private String name;
	
	@Email(message = "E-mail deve ter um formato válido")
	@NotBlank(message = "Email é obrigatorio")
	private String email;
	
	@Pattern(
			regexp = "^\\\\+?[1-9]\\\\d{1,14}$|^\\\\([1-9]{2}\\\\)\\\\s9?[0-9{4}-][0-9{4}]$",
			message = "Formato de telefone invalido"
			)
	@NotBlank(message = "Telefone é obrigatorio")
	private String phoneNumber;
	
}
