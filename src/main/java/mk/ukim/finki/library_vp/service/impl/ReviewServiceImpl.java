package mk.ukim.finki.library_vp.service.impl;

import mk.ukim.finki.library_vp.model.Book;
import mk.ukim.finki.library_vp.model.Review;
import mk.ukim.finki.library_vp.repository.ReviewRepository;
import mk.ukim.finki.library_vp.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository repository;

    public ReviewServiceImpl(ReviewRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Review> findReviewsByBook(Book book) {
        return repository.findAllByBook(book);
    }
}
