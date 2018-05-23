package com.spring.finalproject.dao;

import com.spring.finalproject.pojo.Account;

public interface AccountDAO {
 
    
    public Account findAccount(String userName );
    
    public Account register(Account a) throws Exception;
    
    public boolean updateUser(String email) throws Exception;
    
}