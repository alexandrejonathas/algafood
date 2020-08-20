package com.algaworks.algafood.api.v1.openapi.controller;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.v1.model.RestauranteApenasNomeModel;
import com.algaworks.algafood.api.v1.model.RestauranteBasicoModel;
import com.algaworks.algafood.api.v1.model.RestauranteModel;
import com.algaworks.algafood.api.v1.model.input.RestauranteInput;
import com.algaworks.algafood.api.v1.openapi.model.RestauranteBasicoModelOpenApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "Restaurantes")
public interface RestauranteControllerOpenApi {

	@ApiOperation(value = "Lista os restaurantes", response = RestauranteBasicoModelOpenApi.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "projecao", value = "Nome da projeção de pedidos", 
				paramType = "query", type = "string", allowableValues = "apenas-nome")
	})
	CollectionModel<RestauranteBasicoModel> listar();

	@ApiIgnore
	@ApiOperation(value = "Listar restaurantes", hidden = true)
	//@JsonView(RestauranteView.Resumo.class)	
	CollectionModel<RestauranteApenasNomeModel> listarApenasNomes();

	@ApiOperation("Busca o restaurante por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do restaurante inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
	})		
	ResponseEntity<RestauranteModel> buscar(Long restauranteId);

	@ApiOperation("Cria um restaurante")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Restaurante criado"),
	})		
	ResponseEntity<?> criar(RestauranteInput restauranteInput);

	@ApiOperation("Atualiza um restaurante por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Restaurante atualiazado"),
		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
	})		
	RestauranteModel atualizar(Long restauranteId, RestauranteInput restauranteInput);

	@ApiOperation("Exclui um restaurante por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Restaurante excluído"),
		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
	})	
	ResponseEntity<?> deletar(Long restauranteId);

	@ApiOperation("Ativa um restaurante por ID")
	ResponseEntity<Void> ativar(
			@ApiParam(value = "ID de um restaurante", example = "1")
			Long restauranteId);

	@ApiOperation("Inativa um restaurante por ID")
	ResponseEntity<Void> inativar(
			@ApiParam(value = "ID de um restaurante", example = "1")
			Long restauranteId);

	@ApiOperation("Ativa uma lista de restaurantes por IDs")
	ResponseEntity<Void> ativarLista(
			@ApiParam(value = "IDs de restaurantes", example = "[1,2,3]")
			List<Long> restauranteIds);
	
	@ApiOperation("Inativa uma lista de restaurantes por IDs")
	ResponseEntity<Void> inativarLista(
			@ApiParam(value = "IDs de restaurantes", example = "[1,2,3]")			
			List<Long> restauranteIds);

	@ApiOperation("Abre um restaurante por ID")
	ResponseEntity<Void> abrir(
			@ApiParam(value = "ID de um restaurante", example = "1")
			Long restauranteId);

	@ApiOperation("Fecha um restaurante por ID")
	ResponseEntity<Void> fechar(
			@ApiParam(value = "ID de um restaurante", example = "1")
			Long restauranteId);

}