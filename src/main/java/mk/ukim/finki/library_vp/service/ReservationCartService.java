package mk.ukim.finki.library_vp.service;

import mk.ukim.finki.library_vp.model.Book;
import mk.ukim.finki.library_vp.model.ReservationCart;
import mk.ukim.finki.library_vp.model.User;

import java.util.List;

public interface ReservationCartService {
    ReservationCart findCartByUser(User user);
    ReservationCart findById(Long id);
    ReservationCart addBookToReservationCart(String username, Long bookId);
    ReservationCart getActiveReservationCart(String username);
    List<Book> listAllBooksInReservationCart(Long id);
    void checkout(Long id);
}
