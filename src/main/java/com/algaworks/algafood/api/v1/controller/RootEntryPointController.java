package com.algaworks.algafood.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.v1.AlgaLinksV1;

@RestController
@RequestMapping("/v1")
public class RootEntryPointController {

	@Autowired
	private AlgaLinksV1 algaLinksHelper;
	
	@GetMapping
	public RootEntryPointModel root() {
		var rootEntryPointModel = new RootEntryPointModel();
		
		rootEntryPointModel.add(algaLinksHelper.linkToCozinhas("cozinhas"));
		rootEntryPointModel.add(algaLinksHelper.linkToRestaurantes("restaurantes"));
		rootEntryPointModel.add(algaLinksHelper.linkToFormasPagamento("formas-pagamento"));
		rootEntryPointModel.add(algaLinksHelper.linkToPedidos("pedidos"));
		rootEntryPointModel.add(algaLinksHelper.linkToUsuarios("usuarios"));
		rootEntryPointModel.add(algaLinksHelper.linkToGrupos("grupos"));
		rootEntryPointModel.add(algaLinksHelper.linkToPermissoes("permissoes"));
		rootEntryPointModel.add(algaLinksHelper.linkToEstados("estados"));
		rootEntryPointModel.add(algaLinksHelper.linkToCozinhas("cidades"));
		rootEntryPointModel.add(algaLinksHelper.linkToEstatisticas("estatisticas"));
		
		return rootEntryPointModel;
	}
	
	private static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel>{
		
	}
	
}
