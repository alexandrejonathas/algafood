package com.algaworks.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.AlgaLinksV1;
import com.algaworks.algafood.api.v1.controller.RestauranteController;
import com.algaworks.algafood.api.v1.model.RestauranteModel;
import com.algaworks.algafood.core.security.AlgaSecurity;
import com.algaworks.algafood.domain.model.Restaurante;

@Component
public class RestauranteModelAssembler
	extends RepresentationModelAssemblerSupport<Restaurante, RestauranteModel>{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinksV1 algaLinksHelper;	
	
	@Autowired
	private AlgaSecurity algaSecurity;  	
	
    public RestauranteModelAssembler() {
        super(RestauranteController.class, RestauranteModel.class);
    }	
	
	public RestauranteModel toModel(Restaurante restaurante) {
        RestauranteModel restauranteModel = createModelWithId(restaurante.getId(), restaurante);
        modelMapper.map(restaurante, restauranteModel);
        
        if (algaSecurity.podeConsultarRestaurantes()) {
        	restauranteModel.add(algaLinksHelper.linkToRestaurantes("restaurantes"));
        }
        
        if (algaSecurity.podeConsultarCidades()) {
	        if (restauranteModel.getEndereco() != null 
	                && restauranteModel.getEndereco().getCidade() != null) {
	        	restauranteModel.getEndereco().getCidade().add(
	        		algaLinksHelper.linkToCidade(restaurante.getEndereco().getCidade().getId()));
	        }
        }
        
        if (algaSecurity.podeConsultarRestaurantes()) {
        	restauranteModel.add(algaLinksHelper.linkToRestauranteFormasPagamento(restaurante.getId(), 
                "formas-pagamento"));
        }
        
        if (algaSecurity.podeGerenciarCadastroRestaurantes()) {
        	restauranteModel.add(algaLinksHelper.linkToRestauranteResponsaveis(restaurante.getId(), 
                "responsaveis"));		
        }
        
        if (algaSecurity.podeGerenciarCadastroRestaurantes()) {
	        if (restaurante.ativacaoPermitida()) {
	        	restauranteModel.add(
	        			algaLinksHelper.linkToRestauranteAtivacao(restaurante.getId(), "ativar"));
	        }
	
	        if (restaurante.inativacaoPermitida()) {
	        	restauranteModel.add(
	        			algaLinksHelper.linkToRestauranteInativacao(restaurante.getId(), "inativar"));
	        }
        }
        
        
        if (algaSecurity.podeGerenciarFuncionamentoRestaurantes(restaurante.getId())) {
	        if (restaurante.aberturaPermitida()) {
	        	restauranteModel.add(
	        			algaLinksHelper.linkToRestauranteAbertura(restaurante.getId(), "abrir"));
	        }
	
	        if (restaurante.fechamentoPermitido()) {
	        	restauranteModel.add(
	        			algaLinksHelper.linkToRestauranteFechamento(restaurante.getId(), "fechar"));
	        }        
        }
        
        if (algaSecurity.podeConsultarRestaurantes()) {
            restauranteModel.add(algaLinksHelper.linkToProdutos(restaurante.getId(), "produtos"));
        }        
		
        if (algaSecurity.podeConsultarCozinhas()) {
        	restauranteModel.getCozinha().add(
        		algaLinksHelper.linkToCozinha(restaurante.getCozinha().getId()));        
        }
        
		return restauranteModel;
	}
	
    @Override
    public CollectionModel<RestauranteModel> toCollectionModel(Iterable<? extends Restaurante> entities) {
    	CollectionModel<RestauranteModel> collectionModel = super.toCollectionModel(entities);
    	
    	if (algaSecurity.podeConsultarRestaurantes()) {
    		collectionModel.add(algaLinksHelper.linkToRestaurantes());
    	}
    	
    	return collectionModel;
    }	
	
	
	/*public List<RestauranteModel> toCollectionModel(List<Restaurante> restaurantes) {
		return restaurantes.stream()
				.map(restaurante -> toModel(restaurante))
				.collect(Collectors.toList());
	}*/
}
