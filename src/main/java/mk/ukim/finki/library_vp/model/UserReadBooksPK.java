//package mk.ukim.finki.library_vp.model;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.util.Objects;
//
//public class UserReadBooksPK implements Serializable {
//
//    private Long bookId;
//    private String username;
//
//    public UserReadBooksPK() {
//    }
//
//
//    public Long getBookId() {
//        return bookId;
//    }
//
//    public void setBookId(Long bookId) {
//        this.bookId = bookId;
//    }
//
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        UserReadBooksPK that = (UserReadBooksPK) o;
//        return bookId == that.bookId && Objects.equals(username, that.username);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(bookId, username);
//    }
//}
