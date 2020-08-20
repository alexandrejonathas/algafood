package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.v1.model.FormaPagamentoModel;
import com.algaworks.algafood.api.v1.model.input.FormaPagamentoInput;
import com.algaworks.algafood.api.v1.openapi.model.FormasPagamentoModelOpenApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Formas de pagamento")
public interface FormaPagamentoControllerOpenApi {

	@ApiOperation(value = "Lista as formas de pagamento", response = FormasPagamentoModelOpenApi.class)
	ResponseEntity<CollectionModel<FormaPagamentoModel>> listar();

	@ApiOperation("Busca a forma de pagamento por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID da forma de pagamento inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Forma de pagamento não encontrada", response = Problem.class)
	})		
	FormaPagamentoModel buscar(Long formaPagamentoId, ServletWebRequest request);

	@ApiOperation("Cria uma forma de pagamento")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Forma de pagamento criada"),
	})		
	FormaPagamentoModel adicionar(FormaPagamentoInput formaPagamentoInput);

	@ApiOperation("Atualiza uma forma de pagamento por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Forma de pagamento atualiazada"),
		@ApiResponse(code = 404, message = "Forma de pagamento não encontrada", response = Problem.class)
	})		
	FormaPagamentoModel atualizar(
			
			Long formaPagamentoId, 
			
			FormaPagamentoInput formaPagamentoInput);

	@ApiOperation("Exclui uma forma de pagamento por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Forma de pagamento excluída"),
		@ApiResponse(code = 404, message = "Forma de pagamento não encontrada", response = Problem.class)
	})			
	void remover(Long formaPagamentoId);

}