package mk.ukim.finki.library_vp.service;

import mk.ukim.finki.library_vp.model.Book;
import mk.ukim.finki.library_vp.model.Category;
import mk.ukim.finki.library_vp.model.User;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

public interface BookService {
    List<Book> findAll();

    Book findBookById(Long id);

    List<Book> searchByTitleOrAuthor(String text, String sameText);

    List<Book> searchByCategory(Category category);

    List<Book> findFirst10(Category c);

    void markAsRead(Long bookId, User user);

    List<Book> findTopRated();

}
