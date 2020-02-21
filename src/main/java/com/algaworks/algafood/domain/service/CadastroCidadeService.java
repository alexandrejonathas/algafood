package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.CidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;

@Service
public class CadastroCidadeService {
	
	@Autowired
	private CidadeRepository cidadeRep;
	
	@Autowired
	private CadastroEstadoService cadastroEstado;
	
	public Cidade buscarOuFalhar(Long id) {
		return cidadeRep.findById(id).orElseThrow(() -> new CidadeNaoEncontradaException(id));
	}
	
	public Cidade salvar(Cidade cidade) {
		
		Long id = cidade.getEstado().getId();
		
		Estado estado = cadastroEstado.buscarOuFalhar(id);		
		cidade.setEstado(estado);
		
		return cidadeRep.save(cidade);
	}
	
	public void deletar(Long id) {
		try {			
			cidadeRep.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			throw new CidadeNaoEncontradaException(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Cidade de código %s não pode ser removida, pois está em uso por outra entidade", id));
		}
	}
	
	
}
