package br.com.se.oxp.api.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import br.com.se.oxp.api.auditoria.Audit;
import br.com.se.oxp.api.auditoria.Auditable;
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
@EqualsAndHashCode(callSuper=false,exclude= {"permissoes"})
@Builder
@Entity
@Table(name="grupo")
public class Grupo extends Auditable implements Audit{
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="descricao")
	private String descricao;
	
	@ManyToMany
	@JoinTable(
			name="grupo_permissao",
			joinColumns=@JoinColumn(name="grupo_id"),
			inverseJoinColumns=@JoinColumn(name="permissao_id")
			)
	private Set<Permissao> permissoes;
	
	
	@Override
	public TelaCadastro getTela() {
		return TelaCadastro.GRUPO;
	}

}
