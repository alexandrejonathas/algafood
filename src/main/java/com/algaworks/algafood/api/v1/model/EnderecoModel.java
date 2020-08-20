package com.algaworks.algafood.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoModel {

	@ApiModelProperty(example = "55038-565")
	private String cep;

	@ApiModelProperty(example = "Rua José Martins Sobrinho")
	private String logradouro;
	
	@ApiModelProperty(example = "330")	
	private String numero;

	@ApiModelProperty(example = "Apto 901")
	private String complemento;

	@ApiModelProperty(example = "Boa Vista")
	private String bairro;
	
	private CidadeResumoModel cidade;
}
