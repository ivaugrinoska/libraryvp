package mk.ukim.finki.library_vp.service.impl;

import mk.ukim.finki.library_vp.model.Book;
import mk.ukim.finki.library_vp.model.Category;
import mk.ukim.finki.library_vp.model.User;
import mk.ukim.finki.library_vp.model.exceptions.BookNotFoundException;
import mk.ukim.finki.library_vp.repository.BookRepository;
import mk.ukim.finki.library_vp.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.of(this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id)));
    }

    @Override
    public List<Book> searchByTitleOrAuthor(String text, String sameText) {
        return bookRepository.findAllByAuthorContainingOrNameContaining(text, sameText);
    }

    @Override
    public List<Book> searchByCategory(Category category) {
        return bookRepository.findAllByCategory(category);
    }

    @Override
    public List<Book> findFirst10(Category c) {
        return bookRepository.findFirst10ByCategory(c);
    }

    @Override
    public void markAsRead(Long bookId, User user) {


    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Book editBook(Long id, String name, String author, int stock, float rating, String description, String url) {
        Book book = this.bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        book.setName(name);
        book.setAuthor(author);
        book.setStock(stock);
        book.setDescription(description);
        book.setUrl(url);
        // TODO: KATEGORIJA
      //  book.setCategory(category);

        return this.bookRepository.save(book);
    }

    @Override
    public Book addNewBook(String name, String author, int stock, float rating, String description, String url) {
        if (name.isEmpty())
            return null;

        return this.bookRepository.save(new Book(name, author, stock, rating, description, url));
    }

    @Override
    public List<Book> findTopRated() {
        return this.bookRepository.findTop3ByOrderByRatingDesc();
    }

}
