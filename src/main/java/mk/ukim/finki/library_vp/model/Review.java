package mk.ukim.finki.library_vp.model;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @ManyToOne
    @NotNull
    private User user;

    @ManyToOne
    @NotNull
    private Book book;

    public Review() {
    }

    public Review(String text, User user, Book book) {
        this.text = text;
        this.user = user;
        this.book = book;
    }
}
