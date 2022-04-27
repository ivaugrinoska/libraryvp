package mk.ukim.finki.library_vp.model.pk;

import java.io.Serializable;

public class ReservationCartBooksPK implements Serializable {

    private Long resCartId;
    private Long bookId;

    public ReservationCartBooksPK() {
    }

    public ReservationCartBooksPK(Long resCartId, Long bookId) {
        this.resCartId = resCartId;
        this.bookId = bookId;
    }

    public Long getResCartId() {
        return resCartId;
    }

    public void setResCartId(Long resCartId) {
        this.resCartId = resCartId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
