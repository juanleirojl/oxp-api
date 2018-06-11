package br.com.se.oxp.api.auditoria.dtos;

import static javax.persistence.EnumType.STRING;

import java.util.List;

import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.se.oxp.api.enums.Status;
import br.com.se.oxp.api.enums.TipoContato;
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
@EqualsAndHashCode(callSuper=false)
@Builder
public class ContatoDTO {
	
private Long id;
	
	@NotBlank(message ="O Nome é de preenchimento obrigatório.")
	private String nome;
	
	@Email(message="Informe um email valido")
	@NotBlank(message ="O Email é de preenchimento obrigatório.")
	private String email;
	
	@NotBlank(message ="Informe telefone de contato.")
	private String telefones;
	
	@NotBlank(message ="A observação é de preenchimento obrigatório.")
	private String observacao;
	
	@Enumerated(STRING)
    private Status status;
	
	@Enumerated(STRING)
    private TipoContato tipoContato;
	
	private IntegradorDTO integrador;
	
	private OperadoraDTO operadora;
	
	@JsonIgnore
	private List<String> taggles;
	
	@JsonIgnore
	public boolean isNovo() {
		return id == null;
	}

	@JsonIgnore
	public boolean isEdicao() {
		return id != null;
	}


}
