package com.spring.finalproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.finalproject.dao.OrderDAO;
import com.spring.finalproject.dao.ProductDAO;
import com.spring.finalproject.model.OrderInfo;
import com.spring.finalproject.pojo.Product;


/*
@Controller
public class JsonDataController {
	
	@Autowired
    private ProductDAO productDAO;
	
	@RequestMapping({ "/productsList" })
	@ResponseBody
	public List<Product> getAllProducts(){
		 
		 return productDAO.listActiveProducts();
		
	}

}
@RestController
public class JsonDataController {
	
	@Autowired
	private OrderDAO orderDAO;

	@RequestMapping(path="/ordersList", method=RequestMethod.GET,produces = "application/json")
	public List<OrderInfo> getAllOrders(){
		System.out.println("Entered controller");
		List<OrderInfo> ll = orderDAO.getAllOrders();
		System.out.println("Printing the size of orders table" + ll.size());
		return orderDAO.getAllOrders();
	}*/
