package com.algaworks.algafood.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algaworks.algafood.domain.model.Cidade;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("CidadesModel")
@Data
public class CidadesModelOpenApi {

	@ApiModel("CidadesEmbeddedModel")
	@Data
	public class CidadeEmbeddedModelOpenApi {
		
		private List<Cidade> cidades;
		
		private Links _links;
	}
}
