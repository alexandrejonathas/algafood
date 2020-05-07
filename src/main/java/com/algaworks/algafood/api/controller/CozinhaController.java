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

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRep;
	
	@Autowired
	private CadastroCozinhaService cadastroCozinha;
	
	@GetMapping
	public List<Cozinha> listar(){
		return cozinhaRep.findAll();
	}
	
	@GetMapping("/{cozinhaId}")
	public ResponseEntity<?> buscar(@PathVariable Long cozinhaId) {
		
		Optional<Cozinha> optional = cozinhaRep.findById(cozinhaId);
		
		if(optional.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(optional.get());
	}
	
	@PostMapping
	public ResponseEntity<Cozinha> criar(@RequestBody @Valid Cozinha cozinha) {
		Cozinha cozinhaSalva = cadastroCozinha.salvar(cozinha);
		return ResponseEntity.status(HttpStatus.CREATED).body(cozinhaSalva);
	}

	@PutMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId, @RequestBody @Valid Cozinha cozinha) {		
		Optional<Cozinha> optional = cozinhaRep.findById(cozinhaId);		
		if(optional.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		Cozinha cozinhaAtual = optional.get();
		BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
		cozinhaAtual = cadastroCozinha.salvar(cozinhaAtual);
		
		return ResponseEntity.ok(cozinhaAtual);		
	}	
	
//	@DeleteMapping("/{cozinhaId}")
//	public ResponseEntity<?> deletar(@PathVariable Long cozinhaId) {
//		try {
//			cadastroCozinha.deletar(cozinhaId);
//			return ResponseEntity.noContent().build();
//		} catch (EntidadeNaoEncontradaException e) {
//			return ResponseEntity.notFound().build();
//		}catch (EntidadeEmUsoException e) {
//			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
//		}
//	}	

	@DeleteMapping("/{cozinhaId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long cozinhaId) {
		cadastroCozinha.deletar(cozinhaId);
	}	
	
}
