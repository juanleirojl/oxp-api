package br.com.se.oxp.api.enums;

/**
 * @author Juan Leiro
 */
public enum Status {

	A("Ativo"),
	I("Inativo");

	private String descricao;
	
	Status(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public String getName() {
		return name();
	}

}