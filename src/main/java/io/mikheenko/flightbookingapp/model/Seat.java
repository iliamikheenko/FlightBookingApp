package io.mikheenko.flightbookingapp.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "seat")
@Entity
public class Seat extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id")
    @ToString.Exclude
    private Flight flight;
    @Column(unique = true, nullable = false)
    private String seatNumber;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    @ToString.Exclude
    private Reservation reservation;
    @Column(nullable = false)
    private boolean isAvailable;
}

