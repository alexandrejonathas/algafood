package com.algaworks.algafood.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.v1.AlgaLinksV1;
import com.algaworks.algafood.api.v1.assembler.PermissaoModelAssembler;
import com.algaworks.algafood.api.v1.model.PermissaoModel;
import com.algaworks.algafood.api.v1.openapi.controller.GrupoPermissaoControllerOpenApi;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.service.CadastroGrupoService;

@RestController
@RequestMapping("/v1/grupos/{grupoId/permissoes}")
public class GrupoPermissaoController implements GrupoPermissaoControllerOpenApi {
	
    @Autowired
    private CadastroGrupoService cadastroGrupo;
    
    @Autowired
    private PermissaoModelAssembler permissaoModelAssembler;
    
    @Autowired
    private AlgaLinksV1 algaLinksHelper;
    
    @GetMapping
    public CollectionModel<PermissaoModel> listar(@PathVariable Long grupoId) {
        Grupo grupo = cadastroGrupo.buscarOuFalhar(grupoId);
        
        CollectionModel<PermissaoModel> permissoesModel 
            = permissaoModelAssembler.toCollectionModel(grupo.getPermissoes())
                .removeLinks()
                .add(algaLinksHelper.linkToGrupoPermissoes(grupoId))
                .add(algaLinksHelper.linkToGrupoPermissaoVincular(grupoId, "associar"));
        
        permissoesModel.getContent().forEach(permissaoModel -> {
            permissaoModel.add(algaLinksHelper.linkToGrupoPermissaoDesvincular(
                    grupoId, permissaoModel.getId(), "desassociar"));
        });
        
        return permissoesModel;
    }
    
    @DeleteMapping("/{permissaoId}")
    public ResponseEntity<Void> desvincular(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        cadastroGrupo.desvincularPermissao(grupoId, permissaoId);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/{permissaoId}")
    public ResponseEntity<Void> vincular(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        cadastroGrupo.vincularPermissao(grupoId, permissaoId);
        return ResponseEntity.noContent().build();
    } 	
	
}
