package br.com.se.oxp.api.model;

import static javax.persistence.EnumType.STRING;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import br.com.se.oxp.api.auditoria.Audit;
import br.com.se.oxp.api.auditoria.Auditable;
import br.com.se.oxp.api.enums.Status;
import br.com.se.oxp.api.enums.TelaCadastro;
import br.com.se.oxp.api.enums.TipoContato;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Builder
@Entity
public class Contato extends Auditable implements Audit {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	@Column(name="nome")
	private String nome;
	
	@NotBlank
	@Email
	@Column(name="email")
	private String email;
	
	@NotBlank
	@Column(name="telefones")
	private String telefones;
	
	@NotBlank
	@Column(name="observacao")
	private String observacao;
	
	@Enumerated(STRING)
	@Column(name="status")
    private Status status;
	
	@Enumerated(STRING)
	@Column(name="Tipo")
    private TipoContato tipoContato;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="integrador_id")
	private Integrador integrador;
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name="operadora_id")
	private Operadora operadora;
	
	@Override
	public TelaCadastro getTela() {
		return TelaCadastro.CONTATO;
	}

}
