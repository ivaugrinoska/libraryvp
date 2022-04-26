package mk.ukim.finki.library_vp.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class BookAlreadyInReservationCartException extends RuntimeException {
    public BookAlreadyInReservationCartException(Long id, String username) {
        super(String.format("Book with id: %d already exists in reservation cart for user with username %s",
                id, username));
    }
}

