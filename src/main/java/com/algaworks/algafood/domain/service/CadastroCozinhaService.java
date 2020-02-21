package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {

	@Autowired
	private CozinhaRepository cozinhaRep;
	
	public Cozinha buscarOuFalhar(Long id) {
		return cozinhaRep.findById(id).orElseThrow(() -> new CozinhaNaoEncontradaException(id));
	}
	

	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRep.save(cozinha);
	}
	
	public void deletar(Long id) {
		try {			
			cozinhaRep.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new CozinhaNaoEncontradaException(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Cozinha de código %s não pode ser removida, pois está em uso por outra entidade", id));
		}
	}
	
}
