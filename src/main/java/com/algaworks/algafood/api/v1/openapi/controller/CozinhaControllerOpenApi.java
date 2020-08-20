package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.v1.model.CozinhaModel;
import com.algaworks.algafood.domain.model.Cozinha;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Cozinhas")
public interface CozinhaControllerOpenApi {

	@ApiOperation("Listar as cozinhas")	
	public PagedModel<CozinhaModel> listar(Pageable pageable);
	
	@ApiOperation("Buscar cozinha por ID")
	public ResponseEntity<?> buscar(
			@ApiParam(example = "1", value = "ID da cozinha", required = true)
			Long cozinhaId);
	
	@ApiOperation("Criar cozinha")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Cidade criada")
	})		
	public ResponseEntity<Cozinha> criar(
			@ApiParam(name="corpo", value = "Representação de uma nova cozinha") 
			Cozinha cozinha);

	@ApiOperation("Atualiza uma cozinha por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Cozinha atualiazada"),
		@ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
	})		
	public ResponseEntity<Cozinha> atualizar(
			@ApiParam(example = "1", value = "ID da cozinha", required = true) 
			Long cozinhaId,
			
			@ApiParam(name="corpo", value = "Representação de uma nova cozinha") 
			Cozinha cozinha);	
	
	void deletar(
			@ApiParam(example = "1", value = "ID da cozinha", required = true) 
			Long cozinhaId);
}
