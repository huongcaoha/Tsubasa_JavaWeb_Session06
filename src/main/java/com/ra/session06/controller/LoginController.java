package com.ra.session06.controller;

import com.ra.session06.model.constant.Role;
import com.ra.session06.model.entity.Customer;
import com.ra.session06.model.entity.UserSession;
import com.ra.session06.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(String username, String password, Model model,
                               RedirectAttributes redirectAttributes) {
        Customer customer = userService.login(username, password);

        if (customer != null) {
            UserSession.customer = customer;
            redirectAttributes.addFlashAttribute("message" ,"Login success !");
            if (customer.getRole() == Role.USER) {
                return "redirect:/"; // Trang home
            } else if (customer.getRole() == Role.ADMIN) {
                return "redirect:/admin"; // Trang admin
            }
        }
        model.addAttribute("error", "Thông tin đăng nhập không hợp lệ");
        return "login"; // Quay lại trang đăng nhập
    }
}
