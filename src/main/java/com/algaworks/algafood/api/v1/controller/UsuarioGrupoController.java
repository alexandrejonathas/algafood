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
import com.algaworks.algafood.api.v1.assembler.GrupoModelAssembler;
import com.algaworks.algafood.api.v1.model.GrupoModel;
import com.algaworks.algafood.api.v1.openapi.controller.UsuarioGrupoControllerOpenApi;
import com.algaworks.algafood.core.security.AlgaSecurity;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping(value = "/v1/usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController implements UsuarioGrupoControllerOpenApi {

    @Autowired
    private CadastroUsuarioService cadastroUsuario;
    
    @Autowired
    private GrupoModelAssembler grupoModelAssembler;
    
    @Autowired
    private AlgaLinksV1 algaLinksHelper;
    
    @Autowired
    private AlgaSecurity algaSecurity;     
    
    @CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
    @GetMapping
    public CollectionModel<GrupoModel> listar(@PathVariable Long usuarioId) {
        Usuario usuario = cadastroUsuario.buscarOuFalhar(usuarioId);
        
        CollectionModel<GrupoModel> gruposModel = grupoModelAssembler.toCollectionModel(usuario.getGrupos())
                .removeLinks();
        
        if (algaSecurity.podeEditarUsuariosGruposPermissoes()) {
	        gruposModel.add(algaLinksHelper.linkToUsuarioGrupoVincular(usuarioId, "vincular"));
	        
	        gruposModel.getContent().forEach(grupoModel -> {
	            grupoModel.add(algaLinksHelper.linkToUsuarioGrupoDesvincular(
	                    usuarioId, grupoModel.getId(), "desvincular"));
	        });
        }
        
        return gruposModel;
    }
    
    @CheckSecurity.UsuariosGruposPermissoes.PodeEditar
    @DeleteMapping("/{grupoId}")
    public ResponseEntity<Void> desvincular(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        cadastroUsuario.desvincularGrupo(usuarioId, grupoId);
        return ResponseEntity.noContent().build();
    }
    
    @CheckSecurity.UsuariosGruposPermissoes.PodeEditar
    @PutMapping("/{grupoId}")
    public ResponseEntity<Void> vincular(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        cadastroUsuario.vincularGrupo(usuarioId, grupoId);
        return ResponseEntity.noContent().build();
    }        
} 
