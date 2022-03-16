package mk.ukim.finki.library_vp.repository;

import mk.ukim.finki.library_vp.model.Book;
import mk.ukim.finki.library_vp.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByBook(Book book);
}
