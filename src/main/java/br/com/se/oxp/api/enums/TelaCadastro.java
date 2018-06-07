package br.com.se.oxp.api.enums;

/**
 * @author Juan Leiro
 */
public enum TelaCadastro {

    USUARIO("Usuario"),
    CONTATO("Contato"),
    INTEGRADOR("Integrador"),
	PARCEIRO("Parceiro"),
	OPERADORA("Operadora"),
	PARAMETRO_OPERADORA("Parametro_Operadora"),
	PARCEIRO_OPERADORA("Parceiro_Operadora"),
	PARCEIRO_OPERADORA_INTEGRADOR("Parceiro_Operadora_Integrador"),
	GRUPO("Grupo");

	private String descricao;
	
	TelaCadastro(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public String getName() {
		return name();
	}
}