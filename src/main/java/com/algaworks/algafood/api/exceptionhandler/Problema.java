package com.algaworks.algafood.api.exceptionhandler;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Problema {

	private Integer status;
	private String tipo;
	private String titulo;
	private String detalhe;

	private String mensagem;
	private List<ProblemaCampo> campos;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {

		private Integer status;
		private String tipo;
		private String titulo;
		private String detalhe;
		private String mensagem;
		private List<ProblemaCampo> campos;

		public Builder status(Integer status) {
			this.status = status;
			return this;
		}

		public Builder tipo(String tipo) {
			this.tipo = tipo;
			return this;
		}

		public Builder titulo(String titulo) {
			this.titulo = titulo;
			return this;
		}

		public Builder detalhe(String detalhe) {
			this.detalhe = detalhe;
			return this;
		}

		public Builder mensagem(String mensagem) {
			this.mensagem = mensagem;
			return this;
		}
		
		public Builder campos(List<ProblemaCampo> campos) {
			this.campos = campos;
			return this;
		}

		public Problema build() {
			return new Problema(this);
		}
	}

	private Problema(Builder builder) {
		this.status = builder.status;
		this.tipo = builder.tipo;
		this.titulo = builder.titulo;
		this.detalhe = builder.detalhe;
		this.mensagem = builder.mensagem;
		this.campos = builder.campos;
	}

	public Integer getStatus() {
		return status;
	}

	public String getTipo() {
		return tipo;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDetalhe() {
		return detalhe;
	}

	public String getMensagem() {
		return mensagem;
	}

	public List<ProblemaCampo> getCampos(){
		return campos;
	}
	
}
