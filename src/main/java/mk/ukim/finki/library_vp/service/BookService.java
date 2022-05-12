package mk.ukim.finki.library_vp.service;

import mk.ukim.finki.library_vp.model.Book;
import mk.ukim.finki.library_vp.model.Category;
import mk.ukim.finki.library_vp.model.User;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();

    Book findBookById(Long id);

    Optional<Book> findById(Long id);

    List<Book> searchByTitleOrAuthor(String text, String sameText);

    List<Book> searchByCategory(Category category);

    List<Book> findFirst10(Category c);

    void markAsRead(Long bookId, User user);

    void deleteById(Long id);

    //TODO: sredi kako ke se stava rating preku baza; zatoa ne stavam vo funkciive librarian da stava rating
    Book editBook(Long id, String name, String author, int stock, float rating, String description, String url);

    Book addNewBook(String name, String author, int stock, float rating, String description, String url);

    List<Book> findTopRated();

    Book rating(float num, long id);

}
