package com.algaworks.algafood.api.exceptionhandler;

public enum ProblemaTipo {

	ACESSO_NEGADO("/acesso-negado", "Acesso negado"),
	RECURSO_NAO_ENCONTRADO("Recurso não encontrado", "/recurso-nao-encontrado"),
	ENTIDADE_EM_USO("Entidade em uso", "/entidade-em-uso"),
	ERRO_NEGOCIO("Violação de regra de negócio", "/erro-negocio"),
	MENSAGEM_INCOMPREENSIVEL("Mensagem incompreensível", "/mensagem-incompreensivel"),
	PARAMETRO_INVALIDO("Parâmetro inválido", "/parametro-invalido"),
	ERRO_SISTEMA("Erro de sistema", "/erro-sistema"),
	DADOS_INVALIDOS("Dados inválidos", "/dados-invalidos");
	
	private String titulo;
	
	private String uri;

	private ProblemaTipo(String titulo, String path) {
		this.titulo = titulo;
		this.uri = "http://algafood.com.br"+path;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public String getUri() {
		return uri;
	}
	
	
}
