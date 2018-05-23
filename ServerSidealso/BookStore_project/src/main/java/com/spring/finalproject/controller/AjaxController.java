package com.spring.finalproject.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.finalproject.dao.ProductDAO;
import com.spring.finalproject.model.PaginationResult;
import com.spring.finalproject.model.ProductInfo;
import com.spring.finalproject.pojo.Product;

@Controller
public class AjaxController {
	
	@Autowired
    private ProductDAO productDAO;
	/*
	ArrayList<String> courseList;
	public AjaxController(){
		courseList = new ArrayList<String>();
		courseList.add("AED");
		courseList.add("Web tools");
		courseList.add("Web Design");
		courseList.add("Cloud computing");
		courseList.add("Data Science");
	}
	
	@RequestMapping(value= "/ajaxservice.htm", method=RequestMethod.POST)
	@ResponseBody
	public String ajaxService(HttpServletRequest request)
	{
		String queryString = request.getParameter("product");
		String result = "";
		
		for(int i =0;i<courseList.size();i++){
			if(courseList.get(i).toLowerCase().contains(queryString.toLowerCase())){
				result +=courseList.get(i)+",";
			}
		}
		
		return result;
		
	}
	*/
	
	/*
	// Product search pages
	@RequestMapping(value= {"/ajaxservice.htm"},method=RequestMethod.POST)
	@ResponseBody
    public String listProductHandler(HttpServletRequest request, Model model, //
            @RequestParam(value = "page", defaultValue = "1") int page) {
        final int maxResult = 4;
        final int maxNavigationPage = 10;
        
        String queryString = request.getParameter("product");
		String result = "";
 
        PaginationResult<ProductInfo> result1 = productDAO.queryProducts(page, //
                maxResult, maxNavigationPage, queryString);
 
        for(ProductInfo pf:result1.getList())
        {
        		if(pf.getName().toLowerCase().contains(queryString.toLowerCase()))
        			result += pf.getName()+" , ";
        }
        
        System.out.println(result);
        
        model.addAttribute("paginationProducts", result1);
        return "productList";
        
        //return result;
    }
	*/
	/*
	// Product search pages
		@RequestMapping(value= {"/ajaxservice.htm"},method=RequestMethod.POST)
		@ResponseBody
	    public List<Product> listProductHandler1(HttpServletRequest request, Model model, //
	            @RequestParam(value = "page", defaultValue = "1") int page) {
	        final int maxResult = 4;
	        final int maxNavigationPage = 10;
	        
	        String queryString = request.getParameter("product");
			String result = "";
	 
	        List<Product> result1 = productDAO.listSearchProducts(queryString);
	 
	        for (Product p : result1) {
				System.out.println(p.getName());
			}
	        
	        return result1;
	    }*/
	

}
