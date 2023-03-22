package io.mikheenko.flightbookingapp.service;

import io.mikheenko.flightbookingapp.model.Seat;
import io.mikheenko.flightbookingapp.repository.SeatRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    @Nested
    class BookingSeat{
        @Test
        void shouldBeBookedIfAvailable() {
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
        void throwExceptionIfSeatNotExist(){
            Long seatId = 1L;
            assertAll(
                    ()->{
                        var exception = assertThrows(RuntimeException.class, () -> seatService.bookSeat(seatId));
                        assertThat(exception.getMessage()).isEqualTo("Seat not found");
                    });
        }
        @Test
        void throwExceptionIfSeatBooked(){
            Long seatId = 1L;
            Seat seat = Seat.builder()
                    .seatNumber("A1")
                    .isAvailable(false)
                    .build();
            seat.setId(seatId);
            when(seatRepository.findById(seatId)).thenReturn(Optional.of(seat));
            assertAll(
                    ()->{
                        var exception = assertThrows(RuntimeException.class, () -> seatService.bookSeat(seatId));
                        assertThat(exception.getMessage()).isEqualTo("Seat is already booked.");
                    });
        }
    }
    @Nested
    class CancelingSeat{
        @Test
        void shouldBeCanceledIfAvailable() {
            Long seatId = 1L;
            Seat seat = Seat.builder()
                    .seatNumber("A1")
                    .isAvailable(false)
                    .build();
            seat.setId(seatId);
            when(seatRepository.findById(seatId)).thenReturn(Optional.of(seat));
            seatService.cancelSeat(seatId);
            verify(seatRepository).save(any(Seat.class));
            assertThat(seat.isAvailable()).isTrue();
        }
        @Test
        void throwExceptionIfSeatNotBooked(){
            Long seatId = 1L;
            Seat seat = Seat.builder()
                    .seatNumber("A1")
                    .isAvailable(true)
                    .build();
            seat.setId(seatId);
            when(seatRepository.findById(seatId)).thenReturn(Optional.of(seat));
            assertAll(
                    ()->{
                        var exception = assertThrows(RuntimeException.class, () -> seatService.cancelSeat(seatId));
                        assertThat(exception.getMessage()).isEqualTo("Seat is not booked yet.");
                    }
            );
        }
        @Test
        void throwExceptionIfSeatNotExist(){
            Long seatId = 1L;
            assertAll(
                ()->{
                    var exception = assertThrows(RuntimeException.class, () -> seatService.cancelSeat(seatId));
                    assertThat(exception.getMessage()).isEqualTo("Seat not found");
                    });
            }
    }
    @Nested
    class findAllAvailableSeats{
        @Test
        void returnAvailableSeatsIfExist(){
            List<Seat> seats = Arrays.asList(new Seat(), new Seat(), new Seat(), new Seat());
            when(seatRepository.findByAvailable(true)).thenReturn(seats);
            assertThat(seatService.findAvailableSeats()).size().isEqualTo(4);
        }
        @Test
        void returnEmptyListIfSeatsNotAvailable(){
            List<Seat> seats = new ArrayList<>();
            when(seatRepository.findByAvailable(true)).thenReturn(seats);
            assertThat(seatService.findAvailableSeats()).isEmpty();
        }
    }
}