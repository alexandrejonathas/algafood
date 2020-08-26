package com.algaworks.algafood.api.v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.v1.assembler.EstadoInputDisassembler;
import com.algaworks.algafood.api.v1.assembler.EstadoModelAssembler;
import com.algaworks.algafood.api.v1.model.EstadoModel;
import com.algaworks.algafood.api.v1.model.input.EstadoInput;
import com.algaworks.algafood.api.v1.openapi.controller.EstadoControllerOpenApi;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.service.CadastroEstadoService;

@RestController
@RequestMapping("/v1/estados")
public class EstadoController implements EstadoControllerOpenApi {

	@Autowired
	private EstadoRepository estadoRep;
	
	@Autowired
	private CadastroEstadoService cadastroEstado; 
	
	@Autowired
	private EstadoModelAssembler estadoModelAssembler;
	
	@Autowired
	private EstadoInputDisassembler estadoInputDisassembler;	
	
	@CheckSecurity.Estados.PodeConsultar
	@GetMapping
	public CollectionModel<EstadoModel> listar(){
		List<Estado> estados = estadoRep.findAll();
		return estadoModelAssembler.toCollectionModel(estados);
	}
	
	@CheckSecurity.Estados.PodeConsultar
	@GetMapping("/{estadoId}")
	public EstadoModel buscar(@PathVariable Long estadoId) {		
		Estado estado = cadastroEstado.buscarOuFalhar(estadoId);
		
		return estadoModelAssembler.toModel(estado);
	}
	
	@CheckSecurity.Estados.PodeEditar
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EstadoModel criar(@RequestBody @Valid EstadoInput estadoInput) {		
		Estado estado = cadastroEstado.salvar(estadoInputDisassembler.toDomainObject(estadoInput));
		
		return estadoModelAssembler.toModel(estado);
	}

	@CheckSecurity.Estados.PodeEditar
	@PutMapping("/{estadoId}")
	public ResponseEntity<EstadoModel> atualizar(@PathVariable Long estadoId, 
			@RequestBody @Valid EstadoInput estadoInput) {		
		
		Estado estado = estadoInputDisassembler.toDomainObject(estadoInput);
		
		Estado estadoAtual = cadastroEstado.buscarOuFalhar(estadoId);
		BeanUtils.copyProperties(estado, estadoAtual, "id");

		estadoAtual = cadastroEstado.salvar(estadoAtual);
		
		return ResponseEntity.ok(estadoModelAssembler.toModel(estadoAtual));		
	}		
	
	@CheckSecurity.Estados.PodeEditar
	@DeleteMapping("/{estadoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long estadoId) {
		cadastroEstado.deletar(estadoId);
	}
	
	
}
