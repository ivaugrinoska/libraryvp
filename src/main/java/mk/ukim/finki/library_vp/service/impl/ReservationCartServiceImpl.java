package mk.ukim.finki.library_vp.service.impl;

import mk.ukim.finki.library_vp.model.ReservationCart;
import mk.ukim.finki.library_vp.model.User;
import mk.ukim.finki.library_vp.repository.ReservationCartRepository;
import mk.ukim.finki.library_vp.service.ReservationCartService;
import mk.ukim.finki.library_vp.service.ReviewService;
import org.springframework.stereotype.Service;

@Service
public class ReservationCartServiceImpl implements ReservationCartService {

    private final ReservationCartRepository reservationCartRepository;

    public ReservationCartServiceImpl(ReservationCartRepository reservationCartRepository) {
        this.reservationCartRepository = reservationCartRepository;
    }

    @Override
    public ReservationCart findCartByUser(User user) {
        return reservationCartRepository.findReservationCartByUser(user);
    }
}
