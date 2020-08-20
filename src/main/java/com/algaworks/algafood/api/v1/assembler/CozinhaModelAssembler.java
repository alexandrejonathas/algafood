package com.algaworks.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.AlgaLinksV1;
import com.algaworks.algafood.api.v1.controller.CozinhaController;
import com.algaworks.algafood.api.v1.model.CozinhaModel;
import com.algaworks.algafood.domain.model.Cozinha;

@Component
public class CozinhaModelAssembler extends RepresentationModelAssemblerSupport<Cozinha, CozinhaModel> {
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AlgaLinksV1 algaLinksHelper;	
	
	public CozinhaModelAssembler() {
		super(CozinhaController.class, CozinhaModel.class);
	}	
	
	public CozinhaModel toModel(Cozinha cozinha) {
		var cozinhaModel = createModelWithId(cozinha.getId(), cozinha);
		modelMapper.map(cozinha, cozinhaModel);
		
		cozinhaModel.add(algaLinksHelper.linkToCozinhas("cozinhas"));	
		
		return cozinhaModel;
	}
	
	@Override
	public CollectionModel<CozinhaModel> toCollectionModel(Iterable<? extends Cozinha> entities) {
		return super.toCollectionModel(entities)
    			.add(algaLinksHelper.linkToCozinhas("cozinhas"));
	}

	/*public List<CozinhaModel> toCollectionModel(List<Cozinha> cozinhas) {
		return cozinhas.stream()
				.map(cozinha -> toModel(cozinha))
				.collect(Collectors.toList());
	}*/	
}
