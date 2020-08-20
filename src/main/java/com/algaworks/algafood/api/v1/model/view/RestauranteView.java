package com.algaworks.algafood.api.v1.model.view;

/**
 * 
 * @author alexa
 *
 *Usar com a anotação @JsonView(RestauranteView.Resumo.class)
 */
public interface RestauranteView {
	public interface Resumo{}
	public interface ApenasNomes{}
}
