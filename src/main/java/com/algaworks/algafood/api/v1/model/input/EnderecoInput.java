package com.algaworks.algafood.api.v1.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoInput {
	
	@ApiModelProperty(example = "55038-565", required = true)
	@NotBlank
	private String cep;

	@ApiModelProperty(example = "Rua José Martins Sobrinho", required = true)
	@NotBlank
	private String logradouro;

	@ApiModelProperty(example = "330", required = true)
	@NotBlank
	private String numero;

	@ApiModelProperty(example = "Apto 901")
	private String complemento;

	@ApiModelProperty(example = "Boa Vista", required = true)
	@NotBlank
	private String bairro;

	@Valid
	@NotNull
	private CidadeIdInput cidade;
	
}
