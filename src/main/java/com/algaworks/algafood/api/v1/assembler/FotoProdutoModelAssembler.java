package com.algaworks.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.AlgaLinksV1;
import com.algaworks.algafood.api.v1.controller.RestauranteProdutoFotoController;
import com.algaworks.algafood.api.v1.model.FotoProdutoModel;
import com.algaworks.algafood.core.security.AlgaSecurity;
import com.algaworks.algafood.domain.model.FotoProduto;

@Component
public class FotoProdutoModelAssembler extends RepresentationModelAssemblerSupport<FotoProduto, FotoProdutoModel> {
	
	@Autowired
	private ModelMapper modelMapper;
	
    @Autowired
    private AlgaLinksV1 algaLinksHelper;
    
    @Autowired
    private AlgaSecurity algaSecurity;     
    
    public FotoProdutoModelAssembler() {
        super(RestauranteProdutoFotoController.class, FotoProdutoModel.class);
    }	
	
	public FotoProdutoModel toModel(FotoProduto foto) {		
        FotoProdutoModel fotoProdutoModel = modelMapper.map(foto, FotoProdutoModel.class);       
        
        if (algaSecurity.podeConsultarRestaurantes()) {
	        fotoProdutoModel.add(algaLinksHelper.linkToFotoProduto(
	                foto.getRestauranteId(), foto.getProduto().getId()));
	        
	        fotoProdutoModel.add(algaLinksHelper.linkToProduto(
	                foto.getRestauranteId(), foto.getProduto().getId(), "produto"));
        }
        
        return fotoProdutoModel;
	}
	
	/*public List<FotoProdutoModel> toCollectionModel(List<FotoProduto> fotosProdutos) {
		return fotosProdutos.stream()
				.map(fotoProduto -> toModel(fotoProduto))
				.collect(Collectors.toList());
	}*/	
}
