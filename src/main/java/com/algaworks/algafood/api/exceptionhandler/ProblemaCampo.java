package com.algaworks.algafood.api.exceptionhandler;

public class ProblemaCampo {

	private String nome;

	private String erro;

	public ProblemaCampo(String nome, String erro) {
		this.nome = nome;
		this.erro = erro;
	}

	public String getNome() {
		return nome;
	}

	public String getErro() {
		return erro;
	}

}
