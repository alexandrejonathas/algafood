package com.algaworks.algafood.api.v1.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
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

import com.algaworks.algafood.api.v1.assembler.CozinhaModelAssembler;
import com.algaworks.algafood.api.v1.model.CozinhaModel;
import com.algaworks.algafood.api.v1.openapi.controller.CozinhaControllerOpenApi;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping("/v1/cozinhas")
public class CozinhaController implements CozinhaControllerOpenApi {

	@Autowired
	private CozinhaRepository cozinhaRep;
	
	@Autowired
	private CadastroCozinhaService cadastroCozinha;
	
	@Autowired
	private CozinhaModelAssembler cozinhaModelAssembler; 
	
	@Autowired
	private PagedResourcesAssembler<Cozinha> pagedResourcesAssembler; 
	
	@GetMapping
	public PagedModel<CozinhaModel> listar(Pageable pageable){
		Page<Cozinha> cozinhasPage = cozinhaRep.findAll(pageable);
		
		PagedModel<CozinhaModel> pagedModel = pagedResourcesAssembler.toModel(cozinhasPage, cozinhaModelAssembler);
		
		return pagedModel;
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
