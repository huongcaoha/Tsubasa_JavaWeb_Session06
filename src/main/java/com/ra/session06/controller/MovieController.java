package com.ra.session06.controller;

import com.ra.session06.model.constant.Role;
import com.ra.session06.model.entity.Customer;
import com.ra.session06.model.entity.Movie;
import com.ra.session06.model.entity.UserSession;
import com.ra.session06.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public String listMovies(Model model,RedirectAttributes redirectAttributes) {
        Customer customer = UserSession.customer;
        if (customer == null || customer.getRole() != Role.ADMIN ) {
            redirectAttributes.addFlashAttribute("message", "Bạn cần phải đăng nhập trước nhé !");
            return "redirect:/login";
        }
        model.addAttribute("movies", movieService.getAllMovies());
        return "listMovie"; // Trang hiển thị danh sách phim
    }

    @GetMapping("/add")
    public String showAddMovieForm(Model model , RedirectAttributes redirectAttributes) {
        Customer customer = UserSession.customer;
        if (customer == null || customer.getRole() != Role.ADMIN ) {
            redirectAttributes.addFlashAttribute("message", "Bạn cần phải đăng nhập trước nhé !");
            return "redirect:/login";
        }
        model.addAttribute("movie", new Movie());
        return "formAddMovie"; // Trang thêm phim
    }

    @PostMapping("/add")
    public String addMovie(@ModelAttribute Movie movie, Model model, RedirectAttributes redirectAttributes) {
        // Validate dữ liệu đầu vào
        Customer customer = UserSession.customer;
        if (customer == null || customer.getRole() != Role.ADMIN ) {
            redirectAttributes.addFlashAttribute("message", "Bạn cần phải đăng nhập trước nhé !");
            return "redirect:/login";
        }
        if (movie.getTitle() == null || movie.getTitle().isEmpty()) {
            model.addAttribute("error", "Tiêu đề không được để trống");
            return "formAddMovie"; // Quay lại trang thêm phim
        }
        movieService.addMovie(movie);
        redirectAttributes.addFlashAttribute("message" , "Thêm mới phim thành công !");
        return "redirect:/movies"; // Quay lại danh sách phim
    }

    @GetMapping("/edit/{id}")
    public String showEditMovieForm(@PathVariable Long id, Model model ,RedirectAttributes redirectAttributes) {
        Customer customer = UserSession.customer;
        if (customer == null || customer.getRole() != Role.ADMIN ) {
            redirectAttributes.addFlashAttribute("message", "Bạn cần phải đăng nhập trước nhé !");
            return "redirect:/login";
        }
        Movie movie = movieService.getMovieById(id);
        model.addAttribute("movie", movie);
        return "formUpdateMovie"; // Trang sửa phim
    }

    @PostMapping("/edit/{id}")
    public String updateMovie(@PathVariable Long id, @ModelAttribute Movie movie, Model model,RedirectAttributes redirectAttributes) {
        Customer customer = UserSession.customer;
        if (customer == null || customer.getRole() != Role.ADMIN ) {
            redirectAttributes.addFlashAttribute("message", "Bạn cần phải đăng nhập trước nhé !");
            return "redirect:/login";
        }
        // Validate dữ liệu đầu vào
        if (movie.getTitle() == null || movie.getTitle().isEmpty()) {
            model.addAttribute("error", "Tiêu đề không được để trống");
            return "formUpdateMovie"; // Quay lại trang sửa phim
        }
        movie.setId(id);
        movieService.updateMovie(movie);
        redirectAttributes.addFlashAttribute("message" , "Sửa phim thành công !");
        return "redirect:/movies"; // Quay lại danh sách phim
    }

    @GetMapping("/delete/{id}")
    public String deleteMovie(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Customer customer = UserSession.customer;
        if (customer == null || customer.getRole() != Role.ADMIN ) {
            redirectAttributes.addFlashAttribute("message", "Bạn cần phải đăng nhập trước nhé !");
            return "redirect:/login";
        }
        movieService.deleteMovie(id);
        return "redirect:/movies"; // Quay lại danh sách phim
    }
}
