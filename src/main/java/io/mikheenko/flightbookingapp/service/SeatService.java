package io.mikheenko.flightbookingapp.service;

import io.mikheenko.flightbookingapp.model.Seat;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SeatService {
    void bookSeat(Long seatId);
    void cancelSeat(Long seatId);
    List<Seat> findAvailableSeats();
}
