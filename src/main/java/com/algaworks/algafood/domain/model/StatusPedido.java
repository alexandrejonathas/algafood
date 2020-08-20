package com.algaworks.algafood.domain.model;

import java.util.Arrays;
import java.util.List;

public enum StatusPedido {

	CRIADO("Criado"),
	CONFIRMADO("Confirmado", CRIADO),
	ENTREGUE("Entregue", CONFIRMADO),
	CANCELADO("Cancelado", CRIADO, CONFIRMADO);
	
	private String descricao;
	
	private StatusPedido(String descricao, StatusPedido... statusAnteriores) {
		this.descricao = descricao;
		this.statusAnteriores = Arrays.asList(statusAnteriores);
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	private List<StatusPedido> statusAnteriores;
	
	public boolean naoPodeAlterarPara(StatusPedido novoStatus) {
		return !novoStatus.statusAnteriores.contains(this);
	}
	
	public boolean podeAlterarPara(StatusPedido novoStatus) {
		return !naoPodeAlterarPara(novoStatus);
	}	
	
}
