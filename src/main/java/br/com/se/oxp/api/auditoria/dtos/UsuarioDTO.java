package br.com.se.oxp.api.auditoria.dtos;

import java.time.LocalDateTime;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.se.oxp.api.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false,exclude={"grupos"})
@Builder
public class UsuarioDTO {
	
	private Long id;
	
	@NotBlank(message="Nome não pode ser vazio.")
	@Size(min=3, max = 200, message="Nome deve conter entre {min} e {max} caracteres.")
	private String nome;
	
	@NotBlank(message="Login não pode ser vazio.")
	private String login;
	
	@NotNull(message="Status não pode ser vazio.")
	private Status status;
	
	@NotEmpty(message="Nenhum grupo foi selecionado.")
	private Set<GrupoDTO> grupos;
	
	private LocalDateTime dataAlteracao;
	
	@JsonBackReference
	private UsuarioDTO ultimoUsuario;
	
	private String nomeUltimoUsuario;

}
