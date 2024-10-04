package com.mobi.hotel.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor

@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int mini;
    private int standard;
    private int premium;// e.g., "mini-suite", "standard", "premium"
    private LocalDate checkInDate;
    private LocalDate checkOutDate;



}

