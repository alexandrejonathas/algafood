package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.v1.model.EstadoModel;
import com.algaworks.algafood.api.v1.model.input.EstadoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Estados")
public interface EstadoControllerOpenApi {

	@ApiOperation("Lista os estados")
	public CollectionModel<EstadoModel> listar();
	
	@ApiOperation("Busca o estado por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do Estado inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class)
	})		
	public EstadoModel buscar(
			@ApiParam(example = "1", value = "ID do estado", required = true)
			Long estadoId);
	
	@ApiOperation("Cria um estado")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Estado criado"),
	})	
	public EstadoModel criar(
			@ApiParam(name="corpo", value = "Representação de um estado")
			EstadoInput estadoInput);

	@ApiOperation("Atualiza um estado por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Estado atualiazado"),
		@ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class)
	})		
	public ResponseEntity<EstadoModel> atualizar(
			@ApiParam(example = "1", value = "ID do estado", required = true)
			Long estadoId,
			
			@ApiParam(name="corpo", value = "Representação de um estado com os novos valores", required = true)
			EstadoInput estadoInput);		
	
	@ApiOperation("Exclui um estado por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Estado excluído"),
		@ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class)
	})		
	public void deletar(
			@ApiParam(example = "1", value = "ID do estado", required = true)
			Long estadoId);	
	
}
