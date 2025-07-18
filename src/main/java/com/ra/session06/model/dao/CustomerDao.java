package com.ra.session06.model.dao;

import com.ra.session06.model.constant.Role;
import com.ra.session06.model.entity.Customer;
import com.ra.session06.utils.ConnectDatabase;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
@Repository
public class CustomerDao {
    public Customer login(String username, String password) {
        try (Connection connection = ConnectDatabase.getConnection()){
            CallableStatement statement = connection.prepareCall("{call login(?,?)}");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getLong("id"));
                customer.setUsername(resultSet.getString("username"));
                customer.setPassword(resultSet.getString("password"));
                customer.setPhone(resultSet.getString("phone"));
                customer.setAddress(resultSet.getString("address"));
                customer.setGender(resultSet.getString("gender"));
                customer.setEmail(resultSet.getString("email"));
                customer.setRole(Role.valueOf(resultSet.getString("role")));
                return customer;
            }else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}
