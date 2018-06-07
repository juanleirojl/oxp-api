package br.com.se.oxp.api.auditoria.dtos;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Builder
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class PermissaoDTO {
	
	private Long id;
	
	@NotBlank(message="Nome não pode ser vazio.")
	@Length(min=3, max = 200, message="Nome deve conter entre {min} e {max} caracteres.")
	private String nome;
	
	@NotBlank(message="Descricao não pode ser vazio.")
	private String descricao;
	
}
