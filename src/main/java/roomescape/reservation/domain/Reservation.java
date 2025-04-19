package roomescape.reservation.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import roomescape.reservation.dto.ReservationRequest;

public class Reservation {
    private final Long id;
    private final String name;
    private final LocalDate date;
    private final LocalTime time;

    public Reservation(final long id, final String name, final LocalDate date, final LocalTime time) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
    }

    public static Reservation withId(ReservationRequest reservationRequest, Long id) {
        return new Reservation(id, reservationRequest.name(), reservationRequest.date(), reservationRequest.time());
    }

    public boolean isSameId(final long id) {
        return this.id == id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }
}
