package com.ra.session06.controller;

import com.ra.session06.model.constant.Role;
import com.ra.session06.model.entity.*;
import com.ra.session06.service.MovieService;
import com.ra.session06.service.ScheduleService;
import com.ra.session06.service.ScreenRoomService;
import com.ra.session06.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private MovieService movieService;
    @Autowired
    private ScreenRoomService screenRoomService;

    @GetMapping
    public String listSchedules(Model model, RedirectAttributes redirectAttributes) {
        Customer customer = UserSession.customer;
        if (customer == null || customer.getRole() != Role.ADMIN ) {
            redirectAttributes.addFlashAttribute("message", "Bạn cần phải đăng nhập trước nhé !");
            return "redirect:/login";
        }
        List<Schedule> schedules = scheduleService.getAllSchedules();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        model.addAttribute("schedules", schedules);
        model.addAttribute("formater", formatter);
        return "listSchedule"; // Trang listSchedule.jsp
    }

    @GetMapping("/add")
    public String showAddScheduleForm(Model model,RedirectAttributes redirectAttributes) {
        Customer customer = UserSession.customer;
        if (customer == null || customer.getRole() != Role.ADMIN ) {
            redirectAttributes.addFlashAttribute("message", "Bạn cần phải đăng nhập trước nhé !");
            return "redirect:/login";
        }
        List<ScreenRoom> screenRooms = screenRoomService.getAllScreenRooms();
        List<Movie> movies = movieService.getAllMovies();
        model.addAttribute("movies", movies);
        model.addAttribute("schedule", new Schedule());
        model.addAttribute("screenRooms", screenRooms);
        return "addSchedule"; // Trang addSchedule.jsp
    }

    @PostMapping("/add")
    public String addSchedule(@ModelAttribute Schedule schedule,RedirectAttributes redirectAttributes) {
        Customer customer = UserSession.customer;
        if (customer == null || customer.getRole() != Role.ADMIN ) {
            redirectAttributes.addFlashAttribute("message", "Bạn cần phải đăng nhập trước nhé !");
            return "redirect:/login";
        }
        scheduleService.addSchedule(schedule);
        return "redirect:/schedules"; // Quay lại danh sách lịch chiếu
    }

    @GetMapping("/edit/{id}")
    public String showEditScheduleForm(@PathVariable Long id, Model model,RedirectAttributes redirectAttributes) {
        Customer customer = UserSession.customer;
        if (customer == null || customer.getRole() != Role.ADMIN ) {
            redirectAttributes.addFlashAttribute("message", "Bạn cần phải đăng nhập trước nhé !");
            return "redirect:/login";
        }
        Schedule schedule = scheduleService.getScheduleById(id);
        model.addAttribute("schedule", schedule);
        return "editSchedule"; // Trang editSchedule.jsp
    }

    @PostMapping("/edit/{id}")
    public String updateSchedule(@PathVariable Long id, @ModelAttribute Schedule schedule,RedirectAttributes redirectAttributes) {
        Customer customer = UserSession.customer;
        if (customer == null || customer.getRole() != Role.ADMIN ) {
            redirectAttributes.addFlashAttribute("message", "Bạn cần phải đăng nhập trước nhé !");
            return "redirect:/login";
        }
        schedule.setId(id);
        scheduleService.updateSchedule(schedule);
        return "redirect:/schedules"; // Quay lại danh sách lịch chiếu
    }

    @GetMapping("/delete/{id}")
    public String deleteSchedule(@PathVariable Long id ,RedirectAttributes redirectAttributes) {
        Customer customer = UserSession.customer;
        if (customer == null || customer.getRole() != Role.ADMIN ) {
            redirectAttributes.addFlashAttribute("message", "Bạn cần phải đăng nhập trước nhé !");
            return "redirect:/login";
        }
        scheduleService.deleteSchedule(id);
        return "redirect:/schedules"; // Quay lại danh sách lịch chiếu
    }
}
