package mk.ukim.finki.library_vp.repository;

import mk.ukim.finki.library_vp.model.ReservationCartBooks;
import mk.ukim.finki.library_vp.model.pk.ReservationCartBooksPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReservationCartBooksRepository extends
        JpaRepository<ReservationCartBooks, ReservationCartBooksPK> {
    List<ReservationCartBooks> removeByResCartId(Long id);
    @Modifying
    @Query("delete from ReservationCartBooks rcb where rcb.resCartId = ?1")
    void deleteReservationCartBooksByResCartId(Long id);
//    void deleteReservationCartBooksByResCartId(Long id);
}
