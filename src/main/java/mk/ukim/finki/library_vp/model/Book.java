package mk.ukim.finki.library_vp.model;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String author;

    private int stock;

    private float rating;

    @Column(length = 4000)
    private String description;

    @ManyToOne
    @NotNull
    private Category category;


    public Book() {
    }

    public Book(String name, String author, int stock, float rating, String description) {
        this.name = name;
        this.author = author;
        this.stock = stock;
        this.rating = rating;
        this.description = description;
    }

}

