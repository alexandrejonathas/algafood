package com.algaworks.algafood.core.authorizationserver;

import javax.validation.constraints.NotBlank;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

@Validated
@Component
@ConfigurationProperties("algafood.jwt.keystore")
@Getter
@Setter
public class JwtKeyStoreProperties {

	private Resource jksLocation;

	@NotBlank
	private String password;

	@NotBlank
	private String keypairAlias;

}
