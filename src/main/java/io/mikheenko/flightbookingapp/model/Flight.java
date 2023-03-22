package io.mikheenko.flightbookingapp.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;

import lombok.*;

import javax.money.MonetaryAmount;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
public class Flight extends BaseEntity {
    @Column(nullable = false)
    private String flightNumber;
    @Column(nullable = false)
    private String airlineName;
    @Column(nullable = false)
    private String departureAirport;
    @Column(nullable = false)
    private String arrivalAirport;
    @Column(nullable = false)
    private LocalDateTime departureTime;
    @Column(nullable = false)
    private LocalDateTime arrivalTime;
    @Column(nullable = false)
    @Embedded
    private MonetaryAmount price;
}