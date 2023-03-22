package io.mikheenko.flightbookingapp.service;

import io.mikheenko.flightbookingapp.model.Seat;
import io.mikheenko.flightbookingapp.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class SeatServiceImpl implements SeatService{

    private final SeatRepository seatRepository;
    @Autowired
    public SeatServiceImpl(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    @Override
    public void bookSeat(Long seatId) {
        Optional<Seat> maybeSeat = seatRepository.findById(seatId);
        if (maybeSeat.isPresent()){
            var seat = maybeSeat.get();
            if (seat.isAvailable()){
                seat.setAvailable(false);
                seatRepository.save(seat);
            } else {
                throw new RuntimeException("Seat is already booked.");
            }
        } else {
            throw new RuntimeException("Seat not found");
        }
    }

    @Override
    public void cancelSeat(Long seatId) {
        Optional<Seat> maybeSeat = seatRepository.findById(seatId);
        if (maybeSeat.isPresent()){
            var seat = maybeSeat.get();
            if (!seat.isAvailable()){
                seat.setAvailable(false);
                seatRepository.save(seat);
            } else {
                throw new RuntimeException("Seat is not booked yet.");
            }
        } else {
            throw new RuntimeException("Seat not found");
        }
    }

    @Override
    public List<Seat> findAvailableSeats() {
        return seatRepository.findByAvailable(true);
    }
}
