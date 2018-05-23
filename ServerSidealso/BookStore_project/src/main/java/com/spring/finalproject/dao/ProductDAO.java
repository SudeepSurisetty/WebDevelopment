package com.spring.finalproject.dao;

import java.util.List;

import com.spring.finalproject.model.PaginationResult;
import com.spring.finalproject.model.ProductInfo;
import com.spring.finalproject.pojo.Product;

public interface ProductDAO {
 
    
    
    public Product findProduct(String code);
    
    public ProductInfo findProductInfo(String code) ;
  
    
    public PaginationResult<ProductInfo> queryProducts(int page,
                       int maxResult, int maxNavigationPage  );
    
    public PaginationResult<ProductInfo> queryProducts(int page, int maxResult,
                       int maxNavigationPage, String likeName);
 
    public void save(ProductInfo productInfo);
    
    public List<ProductInfo> getAllProducts();
    
}