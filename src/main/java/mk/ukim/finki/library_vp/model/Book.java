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

    private String description;

    private String url;

    @ManyToOne
    @NotNull
    private Category category;


    public Book() {
    }

    public Book(String name, String author, int stock, float rating, String description, String url) {
        this.name = name;
        this.author = author;
        this.stock = stock;
        this.rating = rating;
        this.description = description;
        this.url = url;
    }

}

