package br.com.se.oxp.api.model;

import static javax.persistence.EnumType.STRING;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import br.com.se.oxp.api.auditoria.Audit;
import br.com.se.oxp.api.auditoria.Auditable;
import br.com.se.oxp.api.enums.Status;
import br.com.se.oxp.api.enums.TelaCadastro;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false,exclude={"grupos"})
@Builder
@Entity
@Table(name="usuario")
public class Usuario extends Auditable implements Audit{
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="login")
	private String login;
	
	@Builder.Default
	@Enumerated(STRING)
	private Status status = Status.A;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "usuario_grupo", joinColumns = @JoinColumn(name = "usuario_id")
		, inverseJoinColumns = @JoinColumn(name = "grupo_id"))
	private Set<Grupo> grupos;
	
	@Override
	public TelaCadastro getTela() {
		return TelaCadastro.USUARIO;
	}

}
