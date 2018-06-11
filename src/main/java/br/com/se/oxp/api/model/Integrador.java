package br.com.se.oxp.api.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CNPJ;

import br.com.se.oxp.api.auditoria.Audit;
import br.com.se.oxp.api.auditoria.Auditable;
import br.com.se.oxp.api.enums.TelaCadastro;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false,exclude={"contatos"})
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Integrador extends Auditable implements Audit{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	@Column(name="nome")
	private String nome;
	
	@Column(name="apelido")
	private String apelido;
	
	@CNPJ(message="CNPJ Inválido")
	@Column(name="cnpj")
	private String cnpj;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="integrador")
	public Set<Contato> contatos;
	
	@Override
	public TelaCadastro getTela() {
		return TelaCadastro.INTEGRADOR;
	}

}
