package com.ra.session06.service;

import com.ra.session06.model.dao.TicketDao;
import com.ra.session06.model.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    @Autowired
    private final TicketDao ticketDao;

    public TicketService(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    public void addTicket(Ticket ticket) {
        ticketDao.addTicket(ticket);
    }

    public List<Ticket> getTicketByScheduleId(long scheduleId) {
        return ticketDao.findTicketByScheduleId(scheduleId);
    }
}
