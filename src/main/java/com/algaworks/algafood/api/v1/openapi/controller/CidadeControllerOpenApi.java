package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.v1.model.CidadeModel;
import com.algaworks.algafood.api.v1.model.input.CidadeInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Cidades")
public interface CidadeControllerOpenApi {

	@ApiOperation("Lista as cidades")
	CollectionModel<CidadeModel> listar();

	@ApiOperation("Busca a cidade por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID da Cidade inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
	})	
	CidadeModel buscar(@ApiParam(value = "ID de uma cidade", example = "1") Long cidadeId);

	@ApiOperation("Cria uma cidade")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Cidade criada"),
	})		
	ResponseEntity<?> criar(@ApiParam(name="corpo", value = "Representação de uma nova cidade") CidadeInput cidadeInput);

	@ApiOperation("Atualiza uma cidade por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Cidade atualiazada"),
		@ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
	})	
	ResponseEntity<?> atualizar(
			@ApiParam(value = "ID de uma cidade", example = "1") 
			Long cidadeId, 
			@ApiParam(name="corpo", value = "Representação de uma cidade com os novos valores") 
			CidadeInput cidadeInput);

	@ApiOperation("Exclui uma cidade por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Cidade excluída"),
		@ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
	})		
	ResponseEntity<?> deletar(@ApiParam(value = "ID de uma cidade", example = "1") Long cidadeId);

}