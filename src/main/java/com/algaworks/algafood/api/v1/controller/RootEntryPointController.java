package com.algaworks.algafood.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.v1.AlgaLinksV1;
import com.algaworks.algafood.core.security.AlgaSecurity;

@RestController
@RequestMapping("/v1")
public class RootEntryPointController {

	@Autowired
	private AlgaLinksV1 algaLinks;
	
	@Autowired
	private AlgaSecurity algaSecurity; 	
	
	@GetMapping
	public RootEntryPointModel root() {
		var rootEntryPointModel = new RootEntryPointModel();
		
		/*rootEntryPointModel.add(algaLinksHelper.linkToCozinhas("cozinhas"));
		rootEntryPointModel.add(algaLinksHelper.linkToRestaurantes("restaurantes"));
		rootEntryPointModel.add(algaLinksHelper.linkToFormasPagamento("formas-pagamento"));
		rootEntryPointModel.add(algaLinksHelper.linkToPedidos("pedidos"));
		rootEntryPointModel.add(algaLinksHelper.linkToUsuarios("usuarios"));
		rootEntryPointModel.add(algaLinksHelper.linkToGrupos("grupos"));
		rootEntryPointModel.add(algaLinksHelper.linkToPermissoes("permissoes"));
		rootEntryPointModel.add(algaLinksHelper.linkToEstados("estados"));
		rootEntryPointModel.add(algaLinksHelper.linkToCozinhas("cidades"));
		rootEntryPointModel.add(algaLinksHelper.linkToEstatisticas("estatisticas"));*/
		
	    if (algaSecurity.podeConsultarCozinhas()) {
	        rootEntryPointModel.add(algaLinks.linkToCozinhas("cozinhas"));
	    }
	    
	    if (algaSecurity.podePesquisarPedidos()) {
	        rootEntryPointModel.add(algaLinks.linkToPedidos("pedidos"));
	    }
	    
	    if (algaSecurity.podeConsultarRestaurantes()) {
	        rootEntryPointModel.add(algaLinks.linkToRestaurantes("restaurantes"));
	    }
	    
	    if (algaSecurity.podeConsultarUsuariosGruposPermissoes()) {
	        rootEntryPointModel.add(algaLinks.linkToGrupos("grupos"));
	        rootEntryPointModel.add(algaLinks.linkToUsuarios("usuarios"));
	        rootEntryPointModel.add(algaLinks.linkToPermissoes("permissoes"));
	    }
	    
	    if (algaSecurity.podeConsultarFormasPagamento()) {
	        rootEntryPointModel.add(algaLinks.linkToFormasPagamento("formas-pagamento"));
	    }
	    
	    if (algaSecurity.podeConsultarEstados()) {
	        rootEntryPointModel.add(algaLinks.linkToEstados("estados"));
	    }
	    
	    if (algaSecurity.podeConsultarCidades()) {
	        rootEntryPointModel.add(algaLinks.linkToCidades("cidades"));
	    }
	    
	    if (algaSecurity.podeConsultarEstatisticas()) {
	        rootEntryPointModel.add(algaLinks.linkToEstatisticas("estatisticas"));
	    }		
		
		return rootEntryPointModel;
	}
	
	private static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel>{
		
	}
	
}
