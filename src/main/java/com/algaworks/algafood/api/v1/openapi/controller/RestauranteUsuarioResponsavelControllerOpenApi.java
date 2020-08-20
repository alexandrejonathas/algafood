package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.v1.model.UsuarioModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Usuários")
public interface RestauranteUsuarioResponsavelControllerOpenApi {

	@ApiOperation("Busca usuários por ID do restaurante")
	CollectionModel<UsuarioModel> listar(
			@ApiParam(example = "1", value = "ID do restaurante", required = true)
			Long restauranteId);

	@ApiOperation("Desvincula um usuário")	
	ResponseEntity<Void> desvincular(
			@ApiParam(example = "1", value = "ID do restaurante", required = true)
			Long restauranteId, 
			
			@ApiParam(example = "1", value = "ID do usuário", required = true)
			Long usuarioId);

	@ApiOperation("Vincula um usuário")
	ResponseEntity<Void> vincular(
			@ApiParam(example = "1", value = "ID do restaurante", required = true)
			Long restauranteId,
			
			@ApiParam(example = "1", value = "ID do usuário", required = true)
			Long usuarioId);

}