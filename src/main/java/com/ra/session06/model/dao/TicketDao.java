package com.ra.session06.model.dao;


import com.ra.session06.model.entity.Ticket;
import com.ra.session06.utils.ConnectDatabase;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository

public class TicketDao {

    public void addTicket(Ticket ticket) {
        try (Connection connection = ConnectDatabase.getConnection();
             CallableStatement call = connection.prepareCall("{call AddTicket(?, ?, ?, ?, ?)}")) {
            call.setLong(1, ticket.getCustomerId());
            call.setLong(2, ticket.getScheduleId());
            call.setString(3, ticket.getSeatName());
            call.setDouble(4, ticket.getTotalMoney());
            call.setDate(5, new java.sql.Date(ticket.getCreatedAt().getTime()));
            call.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Ticket> findTicketByScheduleId(long scheduleId) {
        try (Connection connection = ConnectDatabase.getConnection()){
            CallableStatement call = connection.prepareCall("{call findAllTicketByScheduleId(?)}");
            call.setLong(1, scheduleId);
            ResultSet rs = call.executeQuery();
            List<Ticket> tickets = new ArrayList<>();
            while (rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(rs.getLong("id"));
                ticket.setCustomerId(rs.getLong("customer_id"));
                ticket.setScheduleId(rs.getLong("schedule_id"));
                ticket.setSeatName(rs.getString("seat_name"));
                ticket.setTotalMoney(rs.getDouble("total_money"));
                ticket.setCreatedAt(rs.getDate("created_at"));
                tickets.add(ticket);

            }
            return tickets;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
