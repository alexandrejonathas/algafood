package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

	@Autowired
	private RestauranteRepository restauranteRep;
	
	@Autowired
	private CadastroCozinhaService cadastroCozinha;
	
	public Restaurante buscarOuFalhar(Long id) {
		return restauranteRep.findById(id).orElseThrow(()-> new RestauranteNaoEncontradoException(id));
	}
	
	public Restaurante salvar(Restaurante restaurante) {
		Long id = restaurante.getCozinha().getId();
		
		Cozinha cozinha = cadastroCozinha.buscarOuFalhar(id);
		restaurante.setCozinha(cozinha);
		
		return restauranteRep.save(restaurante);
	}
	
	public void deletar(Long id) {
		try {			
			restauranteRep.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			throw new RestauranteNaoEncontradoException(id);
		}catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Restaurante de código %s não pode ser removido, pois está em uso por outra entidade", id));
		}		
	}
	
}
