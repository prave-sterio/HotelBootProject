package com.mobi.hotel.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;
@Entity
public class Booking {

    @Id
    private int id;
    private String  username;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String RoomType;
    private boolean active;

    public Booking(int id, String username, LocalDate checkInDate, LocalDate checkOutDate, String roomType) {
        this.id = id;
        this.username = username;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        RoomType = roomType;
    }

    public Booking(String username, com.mobi.hotel.Model.RoomType roomType, LocalDate checkIn, LocalDate checkOut) {
    }

    public Booking() {

    }

    public Booking(LocalDate checkinDate, LocalDate checkoutDate, String roomType, String username) {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getRoomType() {
        return RoomType;
    }

    public void setRoomType(String roomType) {
        RoomType = roomType;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Booking(boolean active) {
        this.active = active;
    }

}

