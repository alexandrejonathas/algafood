package com.algaworks.algafood.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.v1.AlgaLinksV1;
import com.algaworks.algafood.api.v1.assembler.UsuarioModelAssembler;
import com.algaworks.algafood.api.v1.model.UsuarioModel;
import com.algaworks.algafood.api.v1.openapi.controller.RestauranteUsuarioResponsavelControllerOpenApi;
import com.algaworks.algafood.core.security.AlgaSecurity;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping(value = "/v1/restaurantes/{restauranteId}/responsaveis")
public class RestauranteUsuarioResponsavelController implements RestauranteUsuarioResponsavelControllerOpenApi {

    @Autowired
    private CadastroRestauranteService cadastroRestaurante;
    
    @Autowired
    private UsuarioModelAssembler usuarioModelAssembler;
    
    @Autowired
    private AlgaLinksV1 algaLinksHelper;    
    
    @Autowired
    private AlgaSecurity algaSecurity;      
    
    @Override
	@GetMapping
    public CollectionModel<UsuarioModel> listar(@PathVariable Long restauranteId) {
        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
        
        var usuariosModel = usuarioModelAssembler.toCollectionModel(restaurante.getResponsaveis())
                .removeLinks();
        usuariosModel.add(algaLinksHelper.linkToResponsaveisRestaurante(restauranteId));
        
        if (algaSecurity.podeGerenciarCadastroRestaurantes()) {
        	usuariosModel.add(algaLinksHelper.linkToRestauranteResponsavelVincular(restauranteId, "vincular"));
	        
	        usuariosModel.getContent().stream().forEach(usuarioModel -> {
	            usuarioModel.add(algaLinksHelper.linkToRestauranteResponsavelDesvincular(
	                    restauranteId, usuarioModel.getId(), "desassociar"));
	        });        
        }
        
        return usuariosModel;
    }
    
    @Override
	@DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> desvincular(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
        cadastroRestaurante.desvincularResponsavel(restauranteId, usuarioId);
        
        return ResponseEntity.noContent().build();
    }
    
    @Override
	@PutMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> vincular(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
        cadastroRestaurante.vincularResponsavel(restauranteId, usuarioId);
        
        return ResponseEntity.noContent().build();
    }
}
