package com.ra.session06.model.dao;

import com.ra.session06.model.entity.ScreenRoom;
import com.ra.session06.utils.ConnectDatabase;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ScreenRoomDao {
    public List<ScreenRoom> getScreenRooms() {
        try (Connection connection = ConnectDatabase.getConnection()){
            CallableStatement callableStatement = connection.prepareCall("{call findAllScreenRoom()}");
            ResultSet resultSet = callableStatement.executeQuery();
            List<ScreenRoom> screenRooms = new ArrayList<>();
            while (resultSet.next()) {
                ScreenRoom screenRoom = new ScreenRoom();
                screenRoom.setId(resultSet.getLong("id"));
                screenRoom.setScreenRoomName(resultSet.getString("screenRoomName"));
                screenRoom.setTotalSeat(resultSet.getInt("totalSeat"));
                screenRooms.add(screenRoom);

            }
            return screenRooms;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ScreenRoom findById(Long id) {
        try (Connection connection = ConnectDatabase.getConnection()){
            CallableStatement callableStatement = connection.prepareCall("{call findScreenRoomById(?)}");
            callableStatement.setLong(1, id);
            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                ScreenRoom screenRoom = new ScreenRoom();
                screenRoom.setId(resultSet.getLong("id"));
                screenRoom.setScreenRoomName(resultSet.getString("screenRoomName"));
                screenRoom.setTotalSeat(resultSet.getInt("totalSeat"));
                return screenRoom;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
