package com.ra.session06.model.dao;

import com.ra.session06.model.entity.Schedule;
import com.ra.session06.utils.ConnectDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Repository
public class ScheduleDao {

    public List<Schedule> findAll() {
        List<Schedule> schedules = new ArrayList<>();
        try (Connection connection = ConnectDatabase.getConnection();
             CallableStatement call = connection.prepareCall("{call findAllSchedules()}")) {
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                Schedule schedule = new Schedule();
                schedule.setId(rs.getLong("id"));
                schedule.setMovieTitle(rs.getString("movie_title"));
                schedule.setShowTime(rs.getTimestamp("show_time").toLocalDateTime());
                schedule.setScreenRoomId(rs.getLong("screen_room_id"));
                schedule.setAvailableSeats(rs.getInt("available_seats"));
                schedule.setFormat(rs.getString("format"));
                schedules.add(schedule);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return schedules;
    }

    public void save(Schedule schedule) {
        Timestamp timestamp = Timestamp.valueOf(schedule.getShowTime());
        try (Connection connection = ConnectDatabase.getConnection();
             CallableStatement call = connection.prepareCall("{call AddSchedule(?, ?, ?, ?, ?)}")) {
            call.setString(1, schedule.getMovieTitle());
            call.setTimestamp(2, timestamp);
            call.setLong(3, schedule.getScreenRoomId());
            call.setInt(4, schedule.getAvailableSeats());
            call.setString(5, schedule.getFormat());
            call.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Schedule findById(Long id) {
        Schedule schedule = null;
        try (Connection connection = ConnectDatabase.getConnection();
             CallableStatement call = connection.prepareCall("{call findScheduleById(?)}")) {
            call.setLong(1, id);
            ResultSet rs = call.executeQuery();
            if (rs.next()) {
                schedule = new Schedule();
                schedule.setId(rs.getLong("id"));
                schedule.setMovieTitle(rs.getString("movie_title"));
                schedule.setShowTime(rs.getTimestamp("show_time").toLocalDateTime());
                schedule.setScreenRoomId(rs.getLong("screen_room_id"));
                schedule.setAvailableSeats(rs.getInt("available_seats"));
                schedule.setFormat(rs.getString("format"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return schedule;
    }

    public void update(Schedule schedule) {
        Timestamp sqlTimestamp = Timestamp.valueOf(schedule.getShowTime());

        try (Connection connection = ConnectDatabase.getConnection();
             CallableStatement call = connection.prepareCall("{call UpdateSchedule(?, ?, ?, ?, ?, ?)}")) {
            call.setLong(1, schedule.getId());
            call.setString(2, schedule.getMovieTitle());
            call.setTimestamp(3, sqlTimestamp);
            call.setLong(4, schedule.getScreenRoomId());
            call.setInt(5, schedule.getAvailableSeats());
            call.setString(6, schedule.getFormat());
            call.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Long id) {
        try (Connection connection = ConnectDatabase.getConnection();
             CallableStatement call = connection.prepareCall("{call DeleteSchedule(?)}")) {
            call.setLong(1, id);
            call.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Schedule> findAllByMovieId(String movieTile) {
        List<Schedule> schedules = new ArrayList<>();
        try (Connection connection = ConnectDatabase.getConnection()){
            CallableStatement callableStatement = connection.prepareCall("call findAllByMovieTitle(?)");
            callableStatement.setString(1, movieTile);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                Schedule schedule = new Schedule();
                schedule.setId(rs.getLong("id"));
                schedule.setMovieTitle(rs.getString("movie_title"));
                schedule.setShowTime(rs.getTimestamp("show_time").toLocalDateTime());
                schedule.setScreenRoomId(rs.getLong("screen_room_id"));
                schedule.setAvailableSeats(rs.getInt("available_seats"));
                schedule.setFormat(rs.getString("format"));
                schedules.add(schedule);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return schedules;
    }
}