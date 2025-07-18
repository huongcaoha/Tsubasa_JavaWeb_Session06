package com.ra.session06.service;

import com.ra.session06.model.dao.ScreenRoomDao;
import com.ra.session06.model.entity.ScreenRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScreenRoomService {
    @Autowired
    private ScreenRoomDao screenRoomDao;
    public List<ScreenRoom> getAllScreenRooms() {
        return screenRoomDao.getScreenRooms();
    }

    public ScreenRoom getScreenRoomById(long id) {
        return screenRoomDao.findById(id);
    }
}
