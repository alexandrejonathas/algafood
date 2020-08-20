package com.algaworks.algafood.domain.service;

import java.io.InputStream;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.FotoProdutoNaoEncontradaException;
import com.algaworks.algafood.domain.model.FotoProduto;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.repository.ProdutoRepository;
import com.algaworks.algafood.domain.service.FotoStorageService.NovaFoto;

@Service
public class CatalogoFotoProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private FotoStorageService fotoStorageService;
	
	public FotoProduto buscarOuFalhar(Long restauranteId, Long produtoId) {
		return produtoRepository.findFotoById(restauranteId, produtoId)
				.orElseThrow(() -> new FotoProdutoNaoEncontradaException(restauranteId, produtoId));
	}
	
	@Transactional
	public FotoProduto salvar(FotoProduto foto, InputStream inputStream) {
		Produto produto = foto.getProduto();
		
		Long restauranteId = produto.getRestaurante().getId();
		Long produtoId = produto.getId();
		String nomeArquivo = fotoStorageService.gerarNomeArquivo(foto.getNomeArquivo());
		String nomeArquivoExistente = null;
		
		Optional<FotoProduto> optionalFotoProduto = produtoRepository.findFotoById(restauranteId, produtoId);
		
		if(optionalFotoProduto.isPresent()) {
			FotoProduto fp = optionalFotoProduto.get();
			nomeArquivoExistente = fp.getNomeArquivo();
			produtoRepository.delete(fp);
		}
		
		foto.setNomeArquivo(nomeArquivo);
		foto = produtoRepository.save(foto);
		produtoRepository.flush();
		
		NovaFoto novaFoto = NovaFoto.builder()
				.nomeArquivo(foto.getNomeArquivo())
				.contentType(foto.getContentType())
				.inputStream(inputStream).build();
		
		fotoStorageService.substituir(nomeArquivoExistente, novaFoto);
		
		return foto;
	}

	@Transactional
	public void deletar(Long restauranteId, Long produtoId) {
		FotoProduto fotoProduto = this.buscarOuFalhar(restauranteId, produtoId);
		
		String nomeArquivo = fotoProduto.getNomeArquivo();
		
		produtoRepository.delete(fotoProduto);
		produtoRepository.flush();
		
		fotoStorageService.remover(nomeArquivo);
	}
	
}
