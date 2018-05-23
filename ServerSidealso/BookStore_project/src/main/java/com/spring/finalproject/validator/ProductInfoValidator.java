package com.spring.finalproject.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.spring.finalproject.dao.ProductDAO;
import com.spring.finalproject.model.ProductInfo;
import com.spring.finalproject.pojo.Product;

//@Component: As a Bean.
@Component
public class ProductInfoValidator implements Validator {


	
	@Autowired
	private ProductDAO productDAO;

	private Pattern pattern;
	private Matcher matcher;

	String PATTERN = "[0-9]";

	// This Validator support ProductInfo class.
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == ProductInfo.class;
	}

	@Override
   public void validate(Object target, Errors errors) {
       ProductInfo productInfo = (ProductInfo) target;

       // Check the fields of ProductInfo class.
       ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "NotEmpty.productForm.code");
       ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.productForm.name");
       ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "NotEmpty.productForm.price");

       if(productInfo.getPrice()<=0) {
    	   errors.rejectValue("price", "price is incorrect",  
    	    	      "Enter price above 0 "); 
       }
       /*
       System.out.println(String.valueOf((int)productInfo.getPrice()));
       if (String.valueOf(productInfo.getPrice()) != null) {  
    	   try {
    		   
    	   pattern = Pattern.compile(PATTERN);  
    	   matcher = pattern.matcher(String.valueOf((int)productInfo.getPrice()));  
    	   if (!matcher.matches()) {  
    	    errors.rejectValue("price", "price is incorrect",  
    	      "Matcher error Enter price in number format(1000) ");  
    	   }}
       catch(NumberFormatException e) {
    	   errors.rejectValue("price", "price is incorrect",  
    	    	      "format exception Enter price in number format(1000) ");  
       }*/
       
       
       String code = productInfo.getCode();
       if (code != null && code.length() > 0) {
           if (code.matches("\\s+")) {
               errors.rejectValue("code", "Pattern.productForm.code");
           } else if(productInfo.isNewProduct()) {
               Product product = productDAO.findProduct(code);
               if (product != null) {
                   errors.rejectValue("code", "Duplicate.productForm.code");
               }
           }
       }
   }
	//}
}
