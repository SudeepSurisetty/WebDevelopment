package com.spring.finalproject.validator;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;  
import java.util.regex.Pattern;  

import com.spring.finalproject.model.CustomerInfo;
 
// @Component: As a Bean.
@Component
public class CustomerInfoValidator implements Validator {
	
	 private Pattern pattern;  
	 private Matcher matcher;  
	 
	 String MOBILE_PATTERN = "[0-9]{10}";   
 
    private EmailValidator emailValidator = EmailValidator.getInstance();
 
    // This Validator support CustomerInfo class.
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == CustomerInfo.class;
    }
 
    @Override
    public void validate(Object target, Errors errors) {
    	

    	
        CustomerInfo custInfo = (CustomerInfo) target;
 
        // Check the fields of CustomerInfo class.
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.customerForm.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.customerForm.email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "NotEmpty.customerForm.address");
        //System.out.println(custInfo.getPhone());
        if (String.valueOf(custInfo.getPhone()) != null) {  
        	   pattern = Pattern.compile(MOBILE_PATTERN);  
        	   matcher = pattern.matcher(String.valueOf(custInfo.getPhone()));  
        	   if (!matcher.matches()) {  
        	    errors.rejectValue("phone", "phone is incorrect",  
        	      "Enter a 10 digit mobile number ");  
        	   }  
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "NotEmpty.customerForm.phone");
 
        if (!emailValidator.isValid(custInfo.getEmail())) {
            errors.rejectValue("email", "Pattern.customerForm.email");
        }
    }
    }
}