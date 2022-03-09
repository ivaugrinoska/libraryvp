package mk.ukim.finki.library_vp.repository;

import mk.ukim.finki.library_vp.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
