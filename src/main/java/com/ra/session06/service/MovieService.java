package com.ra.session06.service;

import com.ra.session06.model.dao.MovieDao;
import com.ra.session06.model.entity.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private final MovieDao movieDao;

    public MovieService(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    public List<Movie> getAllMovies() {
        return movieDao.findAll();
    }

    public void addMovie(Movie movie) {
        movieDao.save(movie);
    }

    public Movie getMovieById(Long id) {
        return movieDao.findById(id);
    }

    public void updateMovie(Movie movie) {
        movieDao.update(movie);
    }

    public void deleteMovie(Long id) {
        movieDao.delete(id);
    }
}
