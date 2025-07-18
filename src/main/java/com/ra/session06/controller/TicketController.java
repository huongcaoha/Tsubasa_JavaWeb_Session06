package com.ra.session06.controller;

import com.ra.session06.model.constant.Role;
import com.ra.session06.model.entity.*;
import com.ra.session06.service.ScheduleService;
import com.ra.session06.service.ScreenRoomService;
import com.ra.session06.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;
    @Autowired
    private ScreenRoomService screenRoomService;
    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/book/{scheduleId}")
    public String showBookingForm(@PathVariable Long scheduleId, Model model, RedirectAttributes redirectAttributes) {
        Customer customer = UserSession.customer;
        if (customer == null || customer.getRole() != Role.USER ) {
            redirectAttributes.addFlashAttribute("message", "Bạn cần phải đăng nhập trước nhé !");
            return "redirect:/login";
        }
        getInfoBooking(scheduleId, model);
        // Thêm thông tin lịch chiếu nếu cần thiết
        return "bookTicket"; // Trang bookTicket.jsp
    }

    private void getInfoBooking(Long scheduleId, Model model) {

        Schedule schedule = scheduleService.getScheduleById(scheduleId);
        ScreenRoom screenRoom = screenRoomService.getScreenRoomById(schedule.getScreenRoomId());
        List<String> tickets = ticketService.getTicketByScheduleId(scheduleId).stream().map(Ticket::getSeatName).collect(Collectors.toList());
        int numberRow = (int) Math.ceil((double) screenRoom.getTotalSeat() /10);
        List<List<Seat>> listRow = new ArrayList<>();
        String[] nameRow = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        for (int i = 0; i < numberRow; i++) {
            List<Seat> row = new ArrayList<>();
            for(int j = 1; j <= 10; j++){
                String nameSeat = nameRow[i] + j ;
                Seat seat = new Seat();
                seat.setSeatName(nameSeat);
                seat.setStatus(tickets.contains(nameSeat));
                row.add(seat);
            }
            listRow.add(row);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        model.addAttribute("schedule", schedule);
        model.addAttribute("listRowSeat", listRow);
        model.addAttribute("screenRoom", screenRoom);
        model.addAttribute("formater", formatter);
    }

    @PostMapping("/book")
    public String bookTicket(@RequestParam("scheduleId") long scheduleId,RedirectAttributes redirectAttributes,
            @RequestParam(name = "listSeat" ,required = false) List<String> listSeat, Model model) {
        Customer customer = UserSession.customer;
        if (customer == null || customer.getRole() != Role.USER ) {
            redirectAttributes.addFlashAttribute("message", "Bạn cần phải đăng nhập trước nhé !");
            return "redirect:/login";
        }
        // Xử lý validate dữ liệu
        if (listSeat.isEmpty()) {
            getInfoBooking(scheduleId,model);
            model.addAttribute("message", "Vui lòng chọn ghế trớc khi đặt ! ");
            return "bookTicket";
        }

        for (String seatName : listSeat){
            Ticket ticket = new Ticket();
            ticket.setSeatName(seatName);
            ticket.setCustomerId(customer.getId());
            ticket.setScheduleId(scheduleId);
            ticket.setSeatName(seatName);
            ticket.setTotalMoney(50000.0);
            ticket.setCreatedAt(new Date());
            ticketService.addTicket(ticket);
        }



        // Cập nhật số ghế trống (cần tạo phương thức trong ScheduleService)
        // scheduleService.updateAvailableSeats(ticket.getScheduleId());
        redirectAttributes.addFlashAttribute("message","Đặt vé thành công !");
        return "redirect:/"; // Quay lại trang chính hoặc danh sách phim
    }
}
