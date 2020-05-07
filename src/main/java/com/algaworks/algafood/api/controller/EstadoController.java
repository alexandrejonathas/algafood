package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.service.CadastroEstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRep;
	
	@Autowired
	private CadastroEstadoService cadastroEstado; 
	
	@GetMapping
	public List<Estado> listar(){
		return estadoRep.findAll();
	}
	
	@GetMapping("/{estadoId}")
	public ResponseEntity<?> buscar(@PathVariable Long estadoId) {
		
		Optional<Estado> optional = estadoRep.findById(estadoId);
		
		if(optional.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(optional.get());
	}
	
	@PostMapping
	public ResponseEntity<Estado> criar(@RequestBody @Valid Estado estado) {
		Estado estadoSalvo = cadastroEstado.salvar(estado);
		return ResponseEntity.status(HttpStatus.CREATED).body(estadoSalvo);
	}

	@PutMapping("/{estadoId}")
	public ResponseEntity<Estado> atualizar(@PathVariable Long estadoId, @RequestBody @Valid Estado estado) {		
		
		Estado estadoAtual = cadastroEstado.buscarOuFalhar(estadoId);
		BeanUtils.copyProperties(estado, estadoAtual, "id");

		estadoAtual = cadastroEstado.salvar(estadoAtual);
		
		return ResponseEntity.ok(estadoAtual);		
	}	
	
//	@DeleteMapping("/{estadoId}")
//	public ResponseEntity<?> deletar(@PathVariable Long estadoId) {
//		try {
//			cadastroEstado.deletar(estadoId);
//			return ResponseEntity.noContent().build();
//		} catch (EntidadeNaoEncontradaException e) {
//			return ResponseEntity.notFound().build();
//		}catch (EntidadeEmUsoException e) {
//			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
//		}
//	}	
	
	@DeleteMapping("/{estadoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long estadoId) {
		cadastroEstado.deletar(estadoId);
	}
	
	
}
