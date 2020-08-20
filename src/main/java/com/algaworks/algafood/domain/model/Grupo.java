package com.algaworks.algafood.domain.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Grupo {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;
	
	private String nome;
	
	@ManyToMany
	@JoinTable(name = "grupo_permissao", joinColumns = @JoinColumn( name= "grupo_id"), inverseJoinColumns = @JoinColumn(name = "permissao_id"))
	private Set<Permissao> permissoes = new HashSet<>();
	
	public boolean desvincularPermissao(Permissao permissao) {
	    return getPermissoes().remove(permissao);
	}

	public boolean vincularPermissao(Permissao permissao) {
	    return getPermissoes().add(permissao);
	} 	
}
