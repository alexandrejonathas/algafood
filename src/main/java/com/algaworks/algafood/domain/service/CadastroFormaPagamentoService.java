package com.algaworks.algafood.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.FormaPagamentoNaoEncontradaException;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;

@Service
public class CadastroFormaPagamentoService {

	@Autowired
	private FormaPagamentoRepository repository;
	
	public FormaPagamento buscarOuFalhar(Long id) {
		return repository.findById(id).orElseThrow(() -> new FormaPagamentoNaoEncontradaException(id));
	}
	
	@Transactional
	public FormaPagamento salvar(FormaPagamento formaPagamento) {
		return repository.save(formaPagamento);
	}
	
	@Transactional
	public void deletar(Long id) {
		try {			
			repository.deleteById(id);
			repository.flush();
		}catch (EmptyResultDataAccessException e) {
			throw new FormaPagamentoNaoEncontradaException(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Forma de pagamento com código %s não pode ser removida, pois está em uso por outra entidade", id));
		}
	}	
	
}
