package mk.ukim.finki.library_vp.service;

import mk.ukim.finki.library_vp.model.ReservationCart;
import mk.ukim.finki.library_vp.model.User;

public interface ReservationCartService {
    ReservationCart findCartByUser(User user);
}
