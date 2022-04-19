//package mk.ukim.finki.library_vp.model;
//
//import javax.persistence.*;
//import java.util.Objects;
//
//@Entity
//@Table(name = "res_users_read_books")
//@IdClass(UserReadBooksPK.class)
//public class UserReadBooks {
//
//    private Long bookId;
//    private String username;
//
//    public UserReadBooks() {
//    }
//
//
//    @Column(name = "read_books_id")
//    @Id
//    public Long getBookId() {
//        return bookId;
//    }
//
//    public void setBookId(Long bookId) {
//        this.bookId = bookId;
//    }
//
//    @Column(name = "user_username")
//    @Id
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
//        UserReadBooks that = (UserReadBooks) o;
//        return bookId == that.bookId && Objects.equals(username, that.username);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(bookId, username);
//    }
//}
