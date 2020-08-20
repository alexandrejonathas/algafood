package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.v1.model.FormaPagamentoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Restaurantes")
public interface RestauranteFormaPagamentoControllerOpenApi {

	@ApiOperation("Busca formas de pagamento por ID do restaurante")
	CollectionModel<FormaPagamentoModel> listar(
			@ApiParam(example = "1", value = "ID do restaurante", required = true)
			Long restauranteId);

	@ApiOperation("Vincula uma forma de pagamento")
	ResponseEntity<Void> vincular(
			@ApiParam(example = "1", value = "ID do restaurante", required = true)
			Long restauranteId, 
			@ApiParam(example = "1", value = "ID da forma de pagamento", required = true)
			Long formaPagamentoId);

	@ApiOperation("Desvincula uma forma de pagamento")
	ResponseEntity<Void> desvincular(
			@ApiParam(example = "1", value = "ID do restaurante", required = true)
			Long restauranteId,
			
			@ApiParam(example = "1", value = "ID da forma de pagamento", required = true)
			Long formaPagamentoId);

}