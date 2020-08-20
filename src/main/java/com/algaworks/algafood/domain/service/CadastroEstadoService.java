package com.algaworks.algafood.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {

	@Autowired
	private EstadoRepository estadoRep;
	
	@Transactional
	public Estado salvar(Estado estado) {
		return estadoRep.save(estado);
	}
	
	@Transactional
	public void deletar(Long id) {
		try {			
			estadoRep.deleteById(id);
			estadoRep.flush();
		}catch (EmptyResultDataAccessException e) {
			throw new EstadoNaoEncontradoException(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Estado de código %s não pode ser removido, pois está em uso por outra entidade", id));
		}
	}
	
	public Estado buscarOuFalhar(Long id) {
		return estadoRep.findById(id).orElseThrow(() -> new EstadoNaoEncontradoException(id));
	}
	
}
