package io.mikheenko.flightbookingapp.service;

import io.mikheenko.flightbookingapp.model.Seat;
import io.mikheenko.flightbookingapp.repository.SeatRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SeatServiceImplTest {
    @Mock
    private SeatRepository seatRepository;
    @InjectMocks
    private SeatServiceImpl seatService;
    @BeforeEach
    public void setup(){
        seatRepository = mock(SeatRepository.class);
        seatService = new SeatServiceImpl(seatRepository);
    }
    @Test
    void seatShouldBookedIfAvailable() {
        Long seatId = 1L;
        Seat seat = Seat.builder()
                .seatNumber("A1")
                .isAvailable(true)
                .build();
        seat.setId(seatId);
        when(seatRepository.findById(seatId)).thenReturn(Optional.of(seat));
        seatService.bookSeat(seatId);
        verify(seatRepository).save(any(Seat.class));
        assertThat(seat.isAvailable()).isFalse();
    }

    @Test
    void cancelSeat() {
    }

    @Test
    void findAvailableSeats() {
    }
}