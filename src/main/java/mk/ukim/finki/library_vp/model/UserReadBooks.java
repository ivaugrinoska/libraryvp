package mk.ukim.finki.library_vp.model;

import mk.ukim.finki.library_vp.model.pk.UserReadBooksPK;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "res_users_read_books",
        schema = "public", catalog = "library")
@IdClass(UserReadBooksPK.class)
public class UserReadBooks {

    @Id
    private Long readBooksId;
    @Id
    private String username;

    public UserReadBooks() {
    }


    @Column(name = "read_books_id")
    @Id
    public Long getReadBooksId() {
        return readBooksId;
    }

    public void setReadBooksId(Long readBooksId) {
        this.readBooksId = readBooksId;
    }

    @Column(name = "user_username")
    @Id
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserReadBooks that = (UserReadBooks) o;
        return Objects.equals(readBooksId, that.readBooksId) &&
                Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(readBooksId, username);
    }
}
