package com.spring.finalproject.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.spring.finalproject.model.CartInfo;

@Component
public class CartInfoValidator implements Validator {


	private Pattern pattern;
	private Matcher matcher;

	String PATTERN = "[0-9]{3}";

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == CartInfo.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		CartInfo cartInfo = (CartInfo) target;
		
		
	       ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quantityTotal","Enter Quantity in number format");
	       
	       if (String.valueOf(cartInfo.getQuantityTotal()) != null) {  
	    	   try {
	    	   pattern = Pattern.compile(PATTERN);  
	    	   matcher = pattern.matcher(String.valueOf(cartInfo.getQuantityTotal()));  
	    	   if (!matcher.matches()) {  
	    	    errors.rejectValue("quantityTotal", "Quantity is incorrect",  
	    	      "Enter quantity in number format(<100) ");  
	    	   }}
	       catch(NumberFormatException e) {
	    	   errors.rejectValue("quantityTotal", "Quantity is incorrect",   
	    			   "Enter quantity in number format(<100) ");  
	       }
		
	}
   
       
       }

}
