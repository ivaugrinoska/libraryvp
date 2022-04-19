package mk.ukim.finki.library_vp.service;

import mk.ukim.finki.library_vp.model.Book;
import mk.ukim.finki.library_vp.model.Review;
import mk.ukim.finki.library_vp.model.User;

import java.util.List;

public interface ReviewService {
    List<Review> findReviewsByBook(Book book);

    Review addNewReview(String review, Long bookId, User user);
}
