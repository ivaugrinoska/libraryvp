package mk.ukim.finki.library_vp.service.impl;

import mk.ukim.finki.library_vp.model.Book;
import mk.ukim.finki.library_vp.model.Review;
import mk.ukim.finki.library_vp.model.User;
import mk.ukim.finki.library_vp.repository.BookRepository;
import mk.ukim.finki.library_vp.repository.ReviewRepository;
import mk.ukim.finki.library_vp.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository repository;
    private final BookRepository bookRepository;

    public ReviewServiceImpl(ReviewRepository repository, BookRepository bookRepository) {
        this.repository = repository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Review> findReviewsByBook(Book book) {
        return repository.findAllByBook(book);
    }

    @Override
    public Review addNewReview(String review, Long bookId, User user) {
        Book book = bookRepository.getById(bookId);
        Review r = new Review(review,user,book);
        return repository.save(r);
    }
}
