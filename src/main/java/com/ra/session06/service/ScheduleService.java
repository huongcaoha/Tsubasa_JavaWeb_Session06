package com.ra.session06.service;

import com.ra.session06.model.dao.ScheduleDao;
import com.ra.session06.model.entity.Schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {
    @Autowired
    private final ScheduleDao scheduleDao;

    public ScheduleService(ScheduleDao scheduleDao) {
        this.scheduleDao = scheduleDao;
    }

    public List<Schedule> findAllByMovieTitle(String movieTitle) {
        return scheduleDao.findAllByMovieId(movieTitle);
    }

    public List<Schedule> getAllSchedules() {
        return scheduleDao.findAll();
    }

    public void addSchedule(Schedule schedule) {
        scheduleDao.save(schedule);
    }

    public Schedule getScheduleById(Long id) {
        return scheduleDao.findById(id);
    }

    public void updateSchedule(Schedule schedule) {
        scheduleDao.update(schedule);
    }

    public void deleteSchedule(Long id) {
        scheduleDao.delete(id);
    }
}