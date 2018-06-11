package br.com.se.oxp.api.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CNPJ;

import br.com.se.oxp.api.auditoria.Audit;
import br.com.se.oxp.api.auditoria.Auditable;
import br.com.se.oxp.api.enums.Status;
import br.com.se.oxp.api.enums.TelaCadastro;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper=false,exclude= {"parametros","contatos"})
@EqualsAndHashCode(callSuper=false,exclude= {"contatos"})
@Entity
public class Operadora extends Auditable implements Audit{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	@Column(name="nome")
	private String nome;
	
	@CNPJ(message="CNPJ Inválido")
	@Column(name="cnpj",unique=true)
	private String cnpj;
	
	@Builder.Default
	@Enumerated(EnumType.STRING)
	@Column(name="status")
	private Status status = Status.A;
	
//	@OneToMany(fetch = FetchType.LAZY, mappedBy="operadora")
//	public Set<ParametroOperadora> parametros; 
//	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="operadora")
	public Set<Contato> contatos;
	
	@Override
	public TelaCadastro getTela() {
		return TelaCadastro.OPERADORA;
	}

}
