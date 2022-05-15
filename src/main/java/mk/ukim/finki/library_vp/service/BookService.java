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

    List<Book> searchByTitleOrAuthor(String text);

    List<Book> searchByCategory(Category category);

    List<Book> findFirst3(Category c);


    void deleteById(Long id);

    Book editBook(Long id, String name, String author, int stock, float rating, String description, String url, Long categoryId);

    Book addNewBook(String name, String author, int stock, float rating, String description, String url, Long categoryId);

    List<Book> findTopRated();

    Book rating(float num, long id);

}
