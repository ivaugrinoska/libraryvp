package mk.ukim.finki.library_vp.model;

import mk.ukim.finki.library_vp.model.pk.ReservationCartBooksPK;
import mk.ukim.finki.library_vp.model.pk.UserReadBooksPK;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "reservation_cart_books",
        schema = "public", catalog = "library")
@IdClass(ReservationCartBooksPK.class)
public class ReservationCartBooks {

    @Id
    private Long resCartId;
    @Id
    private Long bookId;
    private boolean status;

    public ReservationCartBooks() {
    }

    public ReservationCartBooks(Long resCartId, Long bookId) {
        this.resCartId = resCartId;
        this.bookId = bookId;
    }

    @Column(name = "reservation_cart_id")
    @Id
    public Long getResCartId() {
        return resCartId;
    }

    public void setResCartId(Long resCartId) {
        this.resCartId = resCartId;
    }

    @Column(name = "books_id")
    @Id
    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationCartBooks that = (ReservationCartBooks) o;
        return Objects.equals(resCartId, that.resCartId) &&
                Objects.equals(bookId, that.bookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resCartId, bookId);
    }

}
