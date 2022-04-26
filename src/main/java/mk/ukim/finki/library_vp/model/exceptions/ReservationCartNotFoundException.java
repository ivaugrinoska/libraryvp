package mk.ukim.finki.library_vp.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReservationCartNotFoundException extends RuntimeException {

    public ReservationCartNotFoundException(Long id) {
        super(String.format("Reservation cart with id: %d was not found", id));
    }
}

