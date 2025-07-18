package com.ra.session06.controller;


import com.ra.session06.model.entity.Movie;
import com.ra.session06.model.entity.Schedule;
import com.ra.session06.service.MovieService;
import com.ra.session06.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/")
    public String home(Model model) {
        List<Movie> movies = movieService.getAllMovies();
        model.addAttribute("movies", movies);
        return "home"; // Trang home.html
    }

    @GetMapping("/detailMovie/{id}")
    public String detailMovie(@PathVariable Long id, Model model) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        Movie movie = movieService.getMovieById(id);
        List<Schedule> schedules = scheduleService.findAllByMovieTitle(movie.getTitle());
        model.addAttribute("schedules", schedules);
        model.addAttribute("movie", movie);
        model.addAttribute("formater" ,formatter);
        return "detailMovie"; // Trang detailMovie.html
    }
}
