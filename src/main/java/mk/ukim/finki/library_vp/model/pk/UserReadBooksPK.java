package mk.ukim.finki.library_vp.model.pk;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

public class UserReadBooksPK implements Serializable {

    private Long readBooksId;
    private String username;

    public UserReadBooksPK() {
    }

    public UserReadBooksPK(Long readBooksId, String username) {
        this.readBooksId = readBooksId;
        this.username = username;
    }

    public Long getReadBooksId() {
        return readBooksId;
    }

    public void setReadBooksId(Long readBooksId) {
        this.readBooksId = readBooksId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
