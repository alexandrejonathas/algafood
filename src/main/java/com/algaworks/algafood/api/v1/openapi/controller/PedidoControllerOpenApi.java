package com.algaworks.algafood.api.v1.openapi.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.converter.json.MappingJacksonValue;

import com.algaworks.algafood.api.v1.model.PedidoModel;
import com.algaworks.algafood.api.v1.model.PedidoResumoModel;
import com.algaworks.algafood.api.v1.model.input.PedidoInput;
import com.algaworks.algafood.domain.filter.PedidoFilter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Pedidos")
public interface PedidoControllerOpenApi {

	List<PedidoResumoModel> listar();

	@ApiImplicitParams({
		@ApiImplicitParam(name = "campos", paramType = "query", type = "string",
				value = "Nomes das propriedades para filtrar na resposta, separados por vírgula")
	})
	@ApiOperation("Listar os pedidos")	
	PagedModel<PedidoResumoModel> pesquisar(PedidoFilter filtro, Pageable pageable);

	MappingJacksonValue listar(String campos);

	@ApiImplicitParams({
		@ApiImplicitParam(name = "campos", paramType = "query", type = "string",
				value = "Nomes das propriedades para filtrar na resposta, separados por vírgula")
	})	
	@ApiOperation("Buscar um pedido pelo códgo")
	PedidoModel buscar(String codigo);

	@ApiOperation("Criar um pedido")
	PedidoModel adicionar(PedidoInput pedidoInput);

}