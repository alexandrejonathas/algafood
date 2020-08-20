package com.algaworks.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.AlgaLinksV1;
import com.algaworks.algafood.api.v1.controller.RestauranteController;
import com.algaworks.algafood.api.v1.model.RestauranteModel;
import com.algaworks.algafood.domain.model.Restaurante;

@Component
public class RestauranteModelAssembler
	extends RepresentationModelAssemblerSupport<Restaurante, RestauranteModel>{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinksV1 algaLinksHelper;	
	
    public RestauranteModelAssembler() {
        super(RestauranteController.class, RestauranteModel.class);
    }	
	
	public RestauranteModel toModel(Restaurante restaurante) {
        RestauranteModel restauranteModel = createModelWithId(restaurante.getId(), restaurante);
        modelMapper.map(restaurante, restauranteModel);
        
        restauranteModel.add(algaLinksHelper.linkToRestaurantes("restaurantes"));
        
        restauranteModel.getCozinha().add(
        		algaLinksHelper.linkToCozinha(restaurante.getCozinha().getId()));
        
        if (restauranteModel.getEndereco() != null 
                && restauranteModel.getEndereco().getCidade() != null) {
        	restauranteModel.getEndereco().getCidade().add(
        		algaLinksHelper.linkToCidade(restaurante.getEndereco().getCidade().getId()));
        }
        
        restauranteModel.add(algaLinksHelper.linkToRestauranteFormasPagamento(restaurante.getId(), 
                "formas-pagamento"));
        
        restauranteModel.add(algaLinksHelper.linkToRestauranteResponsaveis(restaurante.getId(), 
                "responsaveis"));		
		
        if (restaurante.ativacaoPermitida()) {
        	restauranteModel.add(
        			algaLinksHelper.linkToRestauranteAtivacao(restaurante.getId(), "ativar"));
        }

        if (restaurante.inativacaoPermitida()) {
        	restauranteModel.add(
        			algaLinksHelper.linkToRestauranteInativacao(restaurante.getId(), "inativar"));
        }

        if (restaurante.aberturaPermitida()) {
        	restauranteModel.add(
        			algaLinksHelper.linkToRestauranteAbertura(restaurante.getId(), "abrir"));
        }

        if (restaurante.fechamentoPermitido()) {
        	restauranteModel.add(
        			algaLinksHelper.linkToRestauranteFechamento(restaurante.getId(), "fechar"));
        }        
        
		
		return restauranteModel;
	}
	
    @Override
    public CollectionModel<RestauranteModel> toCollectionModel(Iterable<? extends Restaurante> entities) {
        return super.toCollectionModel(entities)
                .add(algaLinksHelper.linkToRestaurantes());
    }	
	
	
	/*public List<RestauranteModel> toCollectionModel(List<Restaurante> restaurantes) {
		return restaurantes.stream()
				.map(restaurante -> toModel(restaurante))
				.collect(Collectors.toList());
	}*/
}
