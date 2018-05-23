package com.spring.finalproject.dao;

import java.util.List;

import com.spring.finalproject.model.CartInfo;
import com.spring.finalproject.model.OrderDetailInfo;
import com.spring.finalproject.model.OrderInfo;
import com.spring.finalproject.model.PaginationResult;


public interface OrderDAO {
 
    public void saveOrder(CartInfo cartInfo);
 
    public PaginationResult<OrderInfo> listOrderInfo(int page,
            int maxResult, int maxNavigationPage);
    
    public OrderInfo getOrderInfo(String orderId);
    
    public List<OrderDetailInfo> listOrderDetailInfos(String orderId);
    
    public List<OrderInfo> getAllOrders();
 
}