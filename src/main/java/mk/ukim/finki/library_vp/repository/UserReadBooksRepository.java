package mk.ukim.finki.library_vp.repository;

import mk.ukim.finki.library_vp.model.UserReadBooks;
import mk.ukim.finki.library_vp.model.pk.UserReadBooksPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReadBooksRepository extends JpaRepository<UserReadBooks, UserReadBooksPK> {

}
