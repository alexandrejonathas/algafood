package com.algaworks.algafood.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.GrupoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.GrupoRepository;

@Service
public class CadastroGrupoService {
	
	@Autowired
	private GrupoRepository grupoRep;
	
	@Autowired
	private CadastroPermissaoService cadastroPermissao;	
	
	public Grupo buscarOuFalhar(Long id) {
		return grupoRep.findById(id).orElseThrow(() -> new GrupoNaoEncontradoException(id));
	}
	
	@Transactional
	public Grupo salvar(Grupo grupo) {
		return grupoRep.save(grupo);
	}
	
	@Transactional
	public void deletar(Long id) {
		try {			
			grupoRep.deleteById(id);
			grupoRep.flush();
		}catch (EmptyResultDataAccessException e) {
			throw new GrupoNaoEncontradoException(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Grupo de código %s não pode ser removida, pois está em uso por outra entidade", id));
		}
	}
	
	
	@Transactional
	public void desvincularPermissao(Long grupoId, Long permissaoId) {
	    Grupo grupo = buscarOuFalhar(grupoId);
	    Permissao permissao = cadastroPermissao.buscarOuFalhar(permissaoId);
	    
	    grupo.desvincularPermissao(permissao);
	}

	@Transactional
	public void vincularPermissao(Long grupoId, Long permissaoId) {
	    Grupo grupo = buscarOuFalhar(grupoId);
	    Permissao permissao = cadastroPermissao.buscarOuFalhar(permissaoId);
	    
	    grupo.vincularPermissao(permissao);
	} 	
	
}
