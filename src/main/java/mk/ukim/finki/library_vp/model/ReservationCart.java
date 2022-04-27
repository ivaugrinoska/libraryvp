package mk.ukim.finki.library_vp.model;

import com.sun.istack.NotNull;
import lombok.Data;
import mk.ukim.finki.library_vp.model.enumerations.ReservationStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class ReservationCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateTime;

    @ManyToOne
    @NotNull
    private User user;

    @ManyToMany
    private List<Book> books;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    public ReservationCart() {
    }

    public ReservationCart(User user) {
        this.user = user;
        this.books = new ArrayList<>();
        this.dateTime = LocalDateTime.now();
        this.status = ReservationStatus.CREATED;
    }

}
