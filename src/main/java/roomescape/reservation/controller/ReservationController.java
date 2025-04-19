package roomescape.reservation.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import roomescape.reservation.domain.Reservation;
import roomescape.reservation.dto.ReservationRequest;
import roomescape.reservation.dto.ReservationResponse;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    private static final long INITIAL_VALUE = 1;

    private final AtomicLong id = new AtomicLong(INITIAL_VALUE);
    private final List<Reservation> reservations = new ArrayList<>();

    @GetMapping
    public List<Reservation> getReservations() {
        return reservations;
    }

    @PostMapping
    public ReservationResponse createReservation(@RequestBody ReservationRequest reservationRequest) {
        Reservation newReservation = Reservation.withId(reservationRequest, id.getAndIncrement());
        reservations.add(newReservation);

        return new ReservationResponse(
                newReservation.getId(),
                newReservation.getName(),
                newReservation.getDate(),
                newReservation.getTime()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservations(@PathVariable("id") long id) {
        boolean isRemoved = reservations.removeIf(reservation -> reservation.isSameId(id));
        if (isRemoved) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
