package mk.ukim.finki.library_vp.service.impl;

import mk.ukim.finki.library_vp.model.Book;
import mk.ukim.finki.library_vp.model.Category;
import mk.ukim.finki.library_vp.model.User;
import mk.ukim.finki.library_vp.model.exceptions.BookNotFoundException;
import mk.ukim.finki.library_vp.repository.BookRepository;
import mk.ukim.finki.library_vp.repository.CategoryRepository;
import mk.ukim.finki.library_vp.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    public BookServiceImpl(BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAllByOrderByCategoryAsc();
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
    public List<Book> searchByTitleOrAuthor(String text) {
        return this.bookRepository.findAll().stream().filter(i -> i.getName().toLowerCase().contains(text.toLowerCase())
                || i.getAuthor().toLowerCase().
                contains(text.toLowerCase())).collect(Collectors.toList());
    }

    @Override
    public List<Book> searchByCategory(Category category) {
        return bookRepository.findAllByCategory(category);
    }

    @Override
    public List<Book> findFirst3(Category c) {
        return bookRepository.findFirst3ByCategory(c);
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Book editBook(Long id, String name, String author, int stock, float rating, String description, String url, Long categoryId) {
        Book book = this.bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        Category category = this.categoryRepository.findById(categoryId).get();

        book.setName(name);
        book.setAuthor(author);
        book.setStock(stock);
        book.setDescription(description);
        book.setUrl(url);
        book.setRating(rating);
        book.setCategory(category);

        return this.bookRepository.save(book);
    }


    @Override
    public Book addNewBook(String name, String author, int stock, float rating, String description, String url, Long categoryId) {

        Category category = this.categoryRepository.findById(categoryId).get();

        this.bookRepository.deleteByName(name);
        return this.bookRepository.save(new Book(name, author, stock, rating, description, url, category));
    }

    @Override
    public List<Book> findTopRated() {
        return this.bookRepository.findTop3ByOrderByRatingDesc();
    }

    @Override
    public Book rating(float num, long id) {
        Book book = this.bookRepository.findById(id).get();

        book.rate(num);

        return this.bookRepository.save(book);
    }

}
