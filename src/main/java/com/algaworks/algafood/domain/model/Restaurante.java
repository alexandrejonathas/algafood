package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import com.algaworks.algafood.core.validation.Groups;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurante {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;

	@NotBlank
	private String nome;

	@PositiveOrZero
	@Column(name = "taxa_frete")
	private BigDecimal taxaFrete;
	
	@Valid //Ativa a validação em cascata, ou seja, diz para validar o objeto cozinha.
	@ConvertGroup(from = Default.class, to = Groups.CozinhaId.class)
	@NotNull
	@ManyToOne
	@JoinColumn(name = "cozinha_id")
	private Cozinha cozinha;
	
	@Embedded
	private Endereco endereco;
	
	private Boolean inativo = Boolean.FALSE;
	
	private Boolean aberto = Boolean.FALSE;
	
	@CreationTimestamp
	@Column(name = "data_cadastro", nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dataCadastro;
	
	@UpdateTimestamp
	@Column(name = "data_atualizacao", nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dataAtualizacao;	
	
	@OneToMany(mappedBy = "restaurante")
	private List<Produto> produtos = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(name = "restaurante_forma_pagamento", 
		joinColumns = @JoinColumn(name = "restaurante_id"), inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
	private Set<FormaPagamento> formasPagamento = new HashSet<>();
	
	@ManyToMany
	@JoinTable(name = "restaurante_usuario_responsavel",
	        joinColumns = @JoinColumn(name = "restaurante_id"),
	        inverseJoinColumns = @JoinColumn(name = "usuario_id"))
	private Set<Usuario> responsaveis = new HashSet<>(); 	
	
	public void ativar() {
		setInativo(false);
	}

	public void inativar() {
		setInativo(true);
	}
	
	public void abrir() {
		setAberto(true);
	}

	public void fechar() {
		setAberto(false);
	}	
	
	public boolean vincularFormaPagamento(FormaPagamento formaPagamento) {
		return getFormasPagamento().add(formaPagamento);
	}
	
	public boolean desvincularFormaPagamento(FormaPagamento formaPagamento) {
		return getFormasPagamento().remove(formaPagamento);
	}	
	
	public boolean desvincularResponsavel(Usuario usuario) {
	    return getResponsaveis().remove(usuario);
	}

	public boolean vincularResponsavel(Usuario usuario) {
	    return getResponsaveis().add(usuario);
	}
	
	public boolean aceitaFormaPagamento(FormaPagamento formaPagamento) {
	    return getFormasPagamento().contains(formaPagamento);
	}

	public boolean naoAceitaFormaPagamento(FormaPagamento formaPagamento) {
	    return !aceitaFormaPagamento(formaPagamento);
	}	
	
	public boolean isAberto() {
	    return this.aberto;
	}

	public boolean isFechado() {
	    return !isAberto();
	}

	public boolean isInativo() {
	    return this.inativo;
	}

	public boolean isAtivo() {
		return !isInativo();
	}

	public boolean aberturaPermitida() {
	    return isAtivo() && isFechado();
	}
	
	public boolean fechamentoPermitido() {
	    return isAtivo() && isAberto();
	}	

	public boolean ativacaoPermitida() {
	    return isInativo();
	}	
	
	public boolean inativacaoPermitida() {
	    return isAtivo();
	}	
	
}
