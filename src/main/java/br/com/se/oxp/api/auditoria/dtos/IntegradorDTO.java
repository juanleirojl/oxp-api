package br.com.se.oxp.api.auditoria.dtos;

import java.util.List;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CNPJ;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@EqualsAndHashCode(callSuper=false,exclude={"contatos"})
@Builder
public class IntegradorDTO {
	
private Long id;
	
	@NotBlank(message ="O Nome é de preenchimento obrigatório.")
	private String nome;
	
	@NotBlank(message ="O Apelido é de preenchimento obrigatório.")
	private String apelido;
	
	public List<ContatoDTO> contatos;
	
	@CNPJ(message="CNPJ Inválido")
	@Column(name="cnpj")
	private String cnpj;
	
	@JsonIgnore
	public boolean isNovo() {
		return id == null;
	}

	@JsonIgnore
	public boolean isEdicao() {
		return id != null;
	}

}
