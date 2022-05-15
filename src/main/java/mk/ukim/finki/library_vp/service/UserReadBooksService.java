package mk.ukim.finki.library_vp.service;

import mk.ukim.finki.library_vp.model.Book;
import mk.ukim.finki.library_vp.model.ReservationCart;
import mk.ukim.finki.library_vp.model.User;
import mk.ukim.finki.library_vp.model.UserReadBooks;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserReadBooksService {
//    void deleteAll();
//    void deleteAllByUsername(String username);
     User addBookToProfile(String username, Long bookId);
     List<Book> listAllBooksInProfile(String username);
}
