package mk.ukim.finki.library_vp.repository;

import mk.ukim.finki.library_vp.model.Book;
import mk.ukim.finki.library_vp.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByAuthorContainingOrNameContaining(String text, String sameText);

    List<Book> findAllByCategory(Category category);

    List<Book> findFirst3ByCategory(Category c);

    List<Book> findTop3ByOrderByRatingDesc();

    void deleteByName(String name);

    Book findBookByName(String name);

    List<Book> findAllByOrderByCategoryAsc();


}
