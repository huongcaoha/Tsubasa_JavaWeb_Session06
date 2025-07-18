package com.ra.session06.service;


import com.ra.session06.model.constant.Role;
import com.ra.session06.model.dao.CustomerDao;
import com.ra.session06.model.entity.Customer;
import com.ra.session06.utils.ConnectDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

@Service
public class UserService {
    @Autowired
    CustomerDao customerDao;
    public Customer login(String username, String password) {
        return customerDao.login(username, password);
    }
}
