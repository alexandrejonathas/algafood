package com.algaworks.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.AlgaLinksV1;
import com.algaworks.algafood.api.v1.controller.PermissaoController;
import com.algaworks.algafood.api.v1.model.PermissaoModel;
import com.algaworks.algafood.core.security.AlgaSecurity;
import com.algaworks.algafood.domain.model.Permissao;

@Component
public class PermissaoModelAssembler extends RepresentationModelAssemblerSupport<Permissao, PermissaoModel> {

	@Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinksV1 algaLinksHelper;
	
    @Autowired
    private AlgaSecurity algaSecurity;     
	
    public PermissaoModelAssembler() {
		super(PermissaoController.class, PermissaoModel.class);
	}	
	
    @Override
    public PermissaoModel toModel(Permissao permissao) {
        PermissaoModel permissaoModel = modelMapper.map(permissao, PermissaoModel.class);
        return permissaoModel;
    }
    
    @Override
    public CollectionModel<PermissaoModel> toCollectionModel(Iterable<? extends Permissao> entities) {
    	CollectionModel<PermissaoModel> collectionModel = super.toCollectionModel(entities);
    	
    	if (algaSecurity.podeConsultarUsuariosGruposPermissoes()) {
    		collectionModel.add(algaLinksHelper.linkToPermissoes());
    	}
    	
    	return collectionModel;
    }
    
}
