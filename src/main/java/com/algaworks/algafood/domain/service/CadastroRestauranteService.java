package com.algaworks.algafood.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

	@Autowired
	private RestauranteRepository restauranteRep;
	
	@Autowired
	private CadastroCozinhaService cadastroCozinha;
	
	@Autowired
	private CadastroCidadeService cadastroCidade;
	
	@Autowired
	private CadastroFormaPagamentoService cadastroFormaPagamento;
	
	@Autowired
	private CadastroUsuarioService cadastroUsuario;	
	
	public Restaurante buscarOuFalhar(Long id) {
		return restauranteRep.findById(id).orElseThrow(()-> new RestauranteNaoEncontradoException(id));
	}
	
	@Transactional
	public void ativar(Long restauranteId) {
		Restaurante restaurante = buscarOuFalhar(restauranteId);
		restaurante.ativar();
	}
	
	@Transactional
	public void ativar(List<Long> restauranteIds) {
		restauranteIds.forEach(this::ativar);
	}
	
	@Transactional
	public void inativar(Long restauranteId) {
		Restaurante restaurante = buscarOuFalhar(restauranteId);
		restaurante.inativar();		
	}

	@Transactional
	public void inativar(List<Long> restauranteIds) {
		restauranteIds.forEach(this::inativar);
	}	
	
	@Transactional
	public void abrir(Long restauranteId) {
		Restaurante restaurante = buscarOuFalhar(restauranteId);
		restaurante.abrir();
	}
	
	@Transactional
	public void fechar(Long restauranteId) {
		Restaurante restaurante = buscarOuFalhar(restauranteId);
		restaurante.fechar();
	}	
	
	@Transactional
	public Restaurante salvar(Restaurante restaurante) {
		
		Long cozinhaId = restaurante.getCozinha().getId();		
		Cozinha cozinha = cadastroCozinha.buscarOuFalhar(cozinhaId);
		restaurante.setCozinha(cozinha);
		
		Long cidadeId = restaurante.getEndereco().getCidade().getId();		
		Cidade cidade = cadastroCidade.buscarOuFalhar(cidadeId);
		restaurante.getEndereco().setCidade(cidade);
		
		return restauranteRep.save(restaurante);
	}
	
	@Transactional
	public void deletar(Long id) {
		try {			
			restauranteRep.deleteById(id);
			restauranteRep.flush();
		}catch (EmptyResultDataAccessException e) {
			throw new RestauranteNaoEncontradoException(id);
		}catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Restaurante de código %s não pode ser removido, pois está em uso por outra entidade", id));
		}		
	}
	
	@Transactional
	public void desvincularFormaPagamento(Long restauranteId, Long formaPagamentoId) {
		Restaurante restaurante = buscarOuFalhar(restauranteId);
		FormaPagamento formaPagamento = cadastroFormaPagamento.buscarOuFalhar(formaPagamentoId);
		restaurante.desvincularFormaPagamento(formaPagamento);
	}
	
	@Transactional
	public void vincularFormaPagamento(Long restauranteId, Long formaPagamentoId) {
		Restaurante restaurante = buscarOuFalhar(restauranteId);
		FormaPagamento formaPagamento = cadastroFormaPagamento.buscarOuFalhar(formaPagamentoId);
		restaurante.vincularFormaPagamento(formaPagamento);
	}
	
	@Transactional
	public void desvincularResponsavel(Long restauranteId, Long usuarioId) {
	    Restaurante restaurante = buscarOuFalhar(restauranteId);
	    Usuario usuario = cadastroUsuario.buscarOuFalhar(usuarioId);
	    
	    restaurante.desvincularResponsavel(usuario);
	}

	@Transactional
	public void vincularResponsavel(Long restauranteId, Long usuarioId) {
	    Restaurante restaurante = buscarOuFalhar(restauranteId);
	    Usuario usuario = cadastroUsuario.buscarOuFalhar(usuarioId);
	    
	    restaurante.vincularResponsavel(usuario);
	}	
	
}
