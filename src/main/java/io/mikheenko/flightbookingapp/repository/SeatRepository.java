package io.mikheenko.flightbookingapp.repository;

import io.mikheenko.flightbookingapp.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat,Long>{
    List<Seat> findByAvailable(boolean isAvailable);
}
