package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.v1.model.GrupoModel;
import com.algaworks.algafood.api.v1.model.input.GrupoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Grupos")
public interface GrupoControllerOpenApi {

	@ApiOperation("Lista os grupos")
	CollectionModel<GrupoModel> listar();

	@ApiOperation("Busca o grupo por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do Grupo inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
	})		
	ResponseEntity<?> buscar(Long grupoId);

	@ApiOperation("Cria um grupo")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Grupo criado"),
	})		
	GrupoModel criar(GrupoInput grupoInput);

	@ApiOperation("Atualiza um grupo por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Grupo atualiazado"),
		@ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
	})		
	GrupoModel atualizar(Long grupoId, GrupoInput grupoInput);

	@ApiOperation("Exclui um grupo por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Grupo excluído"),
		@ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
	})		
	void remover(Long grupoId);

}