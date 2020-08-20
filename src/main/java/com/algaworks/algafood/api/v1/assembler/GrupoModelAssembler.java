package com.algaworks.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.AlgaLinksV1;
import com.algaworks.algafood.api.v1.controller.GrupoController;
import com.algaworks.algafood.api.v1.model.GrupoModel;
import com.algaworks.algafood.domain.model.Grupo;

@Component
public class GrupoModelAssembler extends RepresentationModelAssemblerSupport<Grupo, GrupoModel> {
	
	@Autowired
	private ModelMapper modelMapper;
	
    @Autowired
    private AlgaLinksV1 algaLinksHelper;	
	
	public GrupoModelAssembler() {
		super(GrupoController.class, GrupoModel.class);
	}
	
	public GrupoModel toModel(Grupo grupo) {		
        GrupoModel grupoModel = createModelWithId(grupo.getId(), grupo);
        modelMapper.map(grupo, grupoModel);
        
        grupoModel.add(algaLinksHelper.linkToGrupos("grupos"));
        
        grupoModel.add(algaLinksHelper.linkToGrupoPermissoes(grupo.getId(), "permissoes"));
        
        return grupoModel;
	}
	
    @Override
    public CollectionModel<GrupoModel> toCollectionModel(Iterable<? extends Grupo> entities) {
        return super.toCollectionModel(entities)
                .add(algaLinksHelper.linkToGrupos());
    } 	
	
	/*public List<GrupoModel> toCollectionModel(Collection<Grupo> grupos) {
		return grupos.stream()
				.map(grupo -> toModel(grupo))
				.collect(Collectors.toList());
	}*/	
}
