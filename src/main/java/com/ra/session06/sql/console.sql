CREATE DATABASE tsubasa_javaweb_session06;

USE tsubasa_javaweb_session06;

CREATE TABLE Customer (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          username VARCHAR(50) NOT NULL,
                          password VARCHAR(50) NOT NULL,
                          phone VARCHAR(15),
                          address VARCHAR(100),
                          gender VARCHAR(10),
                          email VARCHAR(50),
                          role ENUM('ADMIN', 'USER') NOT NULL
);

-- Thêm một tài khoản mẫu
INSERT INTO Customer (username, password, phone, address, gender, email, role) VALUES
                                                                                   ('admin', 'admin123', '0123456789', '123 Admin St', 'Male', 'admin@example.com', 'ADMIN'),
                                                                                   ('user', 'user123', '0987654321', '456 User St', 'Female', 'user@example.com', 'USER');

DELIMITER //
    create procedure login(username varchar(100) , password varchar(100))
    begin
        select * from customer c where c.username = username and c.password = password limit 1 ;
    end //
//
DELIMITER ;

CREATE TABLE Movies (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        title VARCHAR(100) NOT NULL,
                        director VARCHAR(100) NOT NULL,
                        genre VARCHAR(50) NOT NULL,
                        description TEXT,
                        duration INT NOT NULL,
                        language VARCHAR(50) NOT NULL
);

DELIMITER //
    create procedure findAll()
    begin
        select * from movies;
    end //
//
DELIMITER  ;

DELIMITER //

CREATE PROCEDURE AddMovie(
    IN p_title VARCHAR(100),
    IN p_director VARCHAR(100),
    IN p_genre VARCHAR(50),
    IN p_description TEXT,
    IN p_duration INT,
    IN p_language VARCHAR(50)
)
BEGIN
    INSERT INTO Movies (title, director, genre, description, duration, language)
    VALUES (p_title, p_director, p_genre, p_description, p_duration, p_language);
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE findMovieById(IN p_id BIGINT)
BEGIN
    SELECT * FROM Movies WHERE id = p_id;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE UpdateMovie(
    IN p_id BIGINT,
    IN p_title VARCHAR(100),
    IN p_director VARCHAR(100),
    IN p_genre VARCHAR(50),
    IN p_description TEXT,
    IN p_duration INT,
    IN p_language VARCHAR(50)
)
BEGIN
    UPDATE Movies SET title = p_title, director = p_director, genre = p_genre, description = p_description, duration = p_duration, language = p_language WHERE id = p_id;
END //

DELIMITER ;


DELIMITER //

CREATE PROCEDURE DeleteMovie(IN p_id BIGINT)
BEGIN
    DELETE FROM Movies WHERE id = p_id;
END //

DELIMITER ;

CREATE TABLE ScreenRooms (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             screenRoomName VARCHAR(100) NOT NULL,
                             totalSeat INT NOT NULL
);

-- Thêm 10 phòng chiếu mẫu
INSERT INTO ScreenRooms (screenRoomName, totalSeat) VALUES
                                                        ('Phòng Chiếu 1', 100),
                                                        ('Phòng Chiếu 2', 100),
                                                        ('Phòng Chiếu 3', 150),
                                                        ('Phòng Chiếu 4', 200),
                                                        ('Phòng Chiếu 5', 120),
                                                        ('Phòng Chiếu 6', 80),
                                                        ('Phòng Chiếu 7', 90),
                                                        ('Phòng Chiếu 8', 110),
                                                        ('Phòng Chiếu 9', 130),
                                                        ('Phòng Chiếu 10', 140);

CREATE TABLE Schedules (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           movie_title VARCHAR(100) NOT NULL,
                           show_time DATETIME NOT NULL,
                           screen_room_id BIGINT NOT NULL,
                           available_seats INT NOT NULL,
                           format VARCHAR(10) NOT NULL,
                           FOREIGN KEY (screen_room_id) REFERENCES ScreenRooms(id)
);

DELIMITER //

CREATE PROCEDURE findAllSchedules()
BEGIN
    SELECT * FROM Schedules;
END //

DELIMITER ;


DELIMITER //

CREATE PROCEDURE AddSchedule(
    IN p_movie_title VARCHAR(100),
    IN p_show_time DATETIME,
    IN p_screen_room_id BIGINT,
    IN p_available_seats INT,
    IN p_format VARCHAR(10)
)
BEGIN
    INSERT INTO Schedules (movie_title, show_time, screen_room_id, available_seats, format)
    VALUES (p_movie_title, p_show_time, p_screen_room_id, p_available_seats, p_format);
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE findScheduleById(IN p_id BIGINT)
BEGIN
    SELECT * FROM Schedules WHERE id = p_id;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE UpdateSchedule(
    IN p_id BIGINT,
    IN p_movie_title VARCHAR(100),
    IN p_show_time DATETIME,
    IN p_screen_room_id BIGINT,
    IN p_available_seats INT,
    IN p_format VARCHAR(10)
)
BEGIN
    UPDATE Schedules
    SET movie_title = p_movie_title,
        show_time = p_show_time,
        screen_room_id = p_screen_room_id,
        available_seats = p_available_seats,
        format = p_format
    WHERE id = p_id;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE DeleteSchedule(IN p_id BIGINT)
BEGIN
    DELETE FROM Schedules WHERE id = p_id;
END //

DELIMITER ;


CREATE TABLE tickets (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         customer_id BIGINT NOT NULL,
                         schedule_id BIGINT NOT NULL,
                         seat_name VARCHAR(20) NOT NULL,
                         total_money DOUBLE NOT NULL,
                         created_at DATETIME NOT NULL,
                         FOREIGN KEY (schedule_id) REFERENCES Schedules(id)
);

DELIMITER //

CREATE PROCEDURE AddTicket(
    IN p_customer_id BIGINT,
    IN p_schedule_id BIGINT,
    IN p_seat_name VARCHAR(20),
    IN p_total_money DOUBLE,
    IN p_created_at DATETIME
)
BEGIN
    INSERT INTO tickets (customer_id, schedule_id, seat_name, total_money, created_at)
    VALUES (p_customer_id, p_schedule_id, p_seat_name, p_total_money, p_created_at);
END //

DELIMITER ;

DELIMITER //
    create procedure findAllScreenRoom()
    begin
        select * from screenrooms;
    end //
//
DELIMITER ;

DELIMITER //
    create procedure findAllByMovieTitle(movieTitle_in varchar(100))
    begin
        select * from schedules s where s.movie_title = movieTitle_in ;
    end //
//
DELIMITER ;

DELIMITER //
    create procedure findScreenRoomById(id_in long)
    begin
        select * from screenrooms s where s.id = id_in ;
    end //
//
DELIMITER  ;

DELIMITER //
    create procedure findAllTicketByScheduleId(id_in long)
    begin
        select * from tickets t where t.schedule_id = id_in ;
    end //
//
DELIMITER ;