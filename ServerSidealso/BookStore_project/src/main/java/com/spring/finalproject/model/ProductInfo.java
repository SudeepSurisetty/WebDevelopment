package com.spring.finalproject.model;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.finalproject.pojo.Product;
 
public class ProductInfo {
    private String code;
    private String code1;
    private String name;
    private double price;
    private String buyCode;
    private String editCode;
 
    @JsonIgnore
    private boolean newProduct=false;
 
    
    // Upload file.
    @JsonIgnore
    private CommonsMultipartFile fileData;
 
    public ProductInfo() {
    }
 
    public ProductInfo(Product product) {
        this.code = product.getCode();
        this.name = product.getName();
        this.price = product.getPrice();
        code1 = buyCode = editCode = product.getCode();
    }
 
    public ProductInfo(String code, String name, double price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }
 
    public String getCode() {
        return code;
    }
 
    public void setCode(String code) {
        this.code = code;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public double getPrice() {
        return price;
    }
 
    public void setPrice(double price) {
        this.price = price;
    }
    
    
 
    public String getCode1() {
		return code;
	}

	public void setCode1(String code1) {
		this.code1 = code1;
	}

	public String getBuyCode() {
		return code;
	}

	public void setBuyCode(String buyCode) {
		this.buyCode = buyCode;
	}

	public String getEditCode() {
		return code;
	}

	public void setEditCode(String editCode) {
		this.editCode = editCode;
	}

	public CommonsMultipartFile getFileData() {
        return fileData;
    }
 
    public void setFileData(CommonsMultipartFile fileData) {
        this.fileData = fileData;
    }
 
    public boolean isNewProduct() {
        return newProduct;
    }
 
    public void setNewProduct(boolean newProduct) {
        this.newProduct = newProduct;
    }
 
}