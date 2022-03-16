package mk.ukim.finki.library_vp.repository;

import mk.ukim.finki.library_vp.model.ReservationCart;
import mk.ukim.finki.library_vp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationCartRepository extends JpaRepository<ReservationCart, Long> {
    ReservationCart findReservationCartByUser(User user);
}
