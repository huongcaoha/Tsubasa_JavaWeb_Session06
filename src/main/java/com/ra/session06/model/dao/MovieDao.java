package com.ra.session06.model.dao;

import com.ra.session06.model.entity.Movie;
import com.ra.session06.utils.ConnectDatabase;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieDao {

    public List<Movie> findAll() {
        List<Movie> movies = new ArrayList<>();
        try (Connection connection = ConnectDatabase.getConnection();
             CallableStatement call = connection.prepareCall("{call findAll()}")) {
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                Movie movie = new Movie();
                movie.setId(rs.getLong("id"));
                movie.setTitle(rs.getString("title"));
                movie.setDirector(rs.getString("director"));
                movie.setGenre(rs.getString("genre"));
                movie.setDescription(rs.getString("description"));
                movie.setDuration(rs.getInt("duration"));
                movie.setLanguage(rs.getString("language"));
                movies.add(movie);
            }
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi nếu có
        }
        return movies;
    }

    public void save(Movie movie) {
        try (Connection connection = ConnectDatabase.getConnection();
             CallableStatement call = connection.prepareCall("{call AddMovie(?, ?, ?, ?, ?, ?)}")) {
            call.setString(1, movie.getTitle());
            call.setString(2, movie.getDirector());
            call.setString(3, movie.getGenre());
            call.setString(4, movie.getDescription());
            call.setInt(5, movie.getDuration());
            call.setString(6, movie.getLanguage());
            call.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi nếu có
        }
    }

    public Movie findById(Long id) {
        Movie movie = null;
        try (Connection connection = ConnectDatabase.getConnection();
             CallableStatement call = connection.prepareCall("{call findMovieById(?)}")) {
            call.setLong(1, id);
            ResultSet rs = call.executeQuery();
            if (rs.next()) {
                movie = new Movie();
                movie.setId(rs.getLong("id"));
                movie.setTitle(rs.getString("title"));
                movie.setDirector(rs.getString("director"));
                movie.setGenre(rs.getString("genre"));
                movie.setDescription(rs.getString("description"));
                movie.setDuration(rs.getInt("duration"));
                movie.setLanguage(rs.getString("language"));
            }
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi nếu có
        }
        return movie;
    }

    public void update(Movie movie) {
        try (Connection connection = ConnectDatabase.getConnection();
             CallableStatement call = connection.prepareCall("{call UpdateMovie(?, ?, ?, ?, ?, ?, ?)}")) {
            call.setLong(1, movie.getId());
            call.setString(2, movie.getTitle());
            call.setString(3, movie.getDirector());
            call.setString(4, movie.getGenre());
            call.setString(5, movie.getDescription());
            call.setInt(6, movie.getDuration());
            call.setString(7, movie.getLanguage());
            call.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi nếu có
        }
    }

    public void delete(Long id) {
        try (Connection connection = ConnectDatabase.getConnection();
             CallableStatement call = connection.prepareCall("{call DeleteMovie(?)}")) {
            call.setLong(1, id);
            call.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi nếu có
        }
    }
}