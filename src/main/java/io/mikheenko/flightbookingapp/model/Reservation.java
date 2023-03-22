package io.mikheenko.flightbookingapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "reservation")
@Entity
public class Reservation extends BaseEntity {
    @Column(nullable = false)
    private String reservationNumber;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ToString.Exclude
    private Passenger passenger;
    @ManyToOne
    @JoinColumn(name = "flight_id", referencedColumnName = "id")
    @ToString.Exclude
    private Flight flight;
    @Column(nullable = false)
    private int numSeats;
    @Column(nullable = false)
    private BigDecimal totalPrice;

}

