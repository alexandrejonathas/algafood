package com.algaworks.algafood.core.validation;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;

import org.springframework.beans.BeanUtils;

public class ValorZeroIncluiDescricaoValidator implements ConstraintValidator<ValorZeroIncluiDescricao, Object> {

	private String valorCampo;
	
	private String descricaoCampo;
	
	private String descricaoObrigatoria;
	
	@Override
	public void initialize(ValorZeroIncluiDescricao constraint) {
		this.valorCampo = constraint.valorCampo();
		this.descricaoCampo = constraint.descricaoCampo();
		this.descricaoObrigatoria = constraint.descricaoObrigatoria();
	}
	
	@Override
	public boolean isValid(Object object, ConstraintValidatorContext context) {
		try {
			boolean valido = true;
			
			BigDecimal valor = (BigDecimal) BeanUtils.getPropertyDescriptor(object.getClass(), valorCampo)
					.getReadMethod().invoke(object);

			String descricao = (String) BeanUtils.getPropertyDescriptor(object.getClass(), descricaoCampo)
					.getReadMethod().invoke(object);			
			
			if(valor != null && BigDecimal.ZERO.compareTo(valor)==0 && descricao != null) {
				valido = descricao.toLowerCase().contains(descricaoObrigatoria.toLowerCase());
			}
			
			return valido;
		} catch (Exception e) {
			throw new ValidationException(e);
		}
	}

}
