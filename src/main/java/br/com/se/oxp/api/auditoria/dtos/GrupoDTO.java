package br.com.se.oxp.api.auditoria.dtos;

import java.util.Set;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false,exclude= {"permissoes"})
@Builder
public class GrupoDTO {
	
	private Long id;
	
	@NotBlank(message="Nome não pode ser vazio.")
	@Length(min=3, max = 200, message="Nome deve conter entre {min} e {max} caracteres.")
	private String nome;
	
	@NotBlank(message="Descricao não pode ser vazio.")
	private String descricao;
	
	
	@NotBlank(message="Nenhuma permissão foi selecionada.")
	private Set<PermissaoDTO> permissoes;
	
	@JsonIgnore
	private Boolean checked;

}
