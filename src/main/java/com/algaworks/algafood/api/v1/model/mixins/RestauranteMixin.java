package com.algaworks.algafood.api.v1.model.mixins;

import java.util.ArrayList;
import java.util.List;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Endereco;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.model.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class RestauranteMixin {

	//Ignora a propriedade nome ao converter json para objeto, mas não ignora ao converter objeto em json.
	@JsonIgnoreProperties(value = "nome", allowGetters = true)
	private Cozinha cozinha;
	
	@JsonIgnore
	private Endereco endereco;

	@JsonIgnore
	private List<Produto> produtos = new ArrayList<>();

	@JsonIgnore
	private List<FormaPagamento> formasPagamento = new ArrayList<>();	
	
}
