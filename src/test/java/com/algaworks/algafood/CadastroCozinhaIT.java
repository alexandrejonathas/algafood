package com.algaworks.algafood;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CadastroCozinhaIT {

	@Autowired
	private CadastroCozinhaService cadastroCozinha;
	
	@Test
	public void deveCadastrarCozinhaComSucesso() {
		//Cenário
		Cozinha cozinha = new Cozinha();
		cozinha.setNome("Chinesa");
		
		//Ação
		cozinha = cadastroCozinha.salvar(cozinha);
		
		//Validação
		assertThat(cozinha).isNotNull();
		assertThat(cozinha.getId()).isNotNull();
	}
	
	//@Test(expected = ConstraintViolationException.class)
	@Test(expected = TransactionSystemException.class)
	public void deveFalharAoCadastrarCozinhaSemNome() {

		Cozinha cozinha = new Cozinha();
		cozinha.setNome(null);
		
		cadastroCozinha.salvar(cozinha);

	}
	
	@Test(expected = EntidadeEmUsoException.class)
	public void deveFalharAoExcluirCozinhaEmUso() {
		cadastroCozinha.deletar(1L);
	}
	
	@Test(expected = EntidadeNaoEncontradaException.class)
	public void deveFalharAoExcluirCozinhaInexistente() {
		cadastroCozinha.deletar(100L);
	}
	
}
