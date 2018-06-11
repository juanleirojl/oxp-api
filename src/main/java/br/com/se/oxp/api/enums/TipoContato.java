package br.com.se.oxp.api.enums;

/**
 * @author Juan Leiro
 */
public enum TipoContato {

	O("OPERADORA"),
	I("INTEGRADOR");

	private String descricao;
	
	TipoContato(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public String getName() {
		return name();
	}

}