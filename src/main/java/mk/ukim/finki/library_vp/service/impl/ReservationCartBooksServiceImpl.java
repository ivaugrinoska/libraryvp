package mk.ukim.finki.library_vp.service.impl;


import mk.ukim.finki.library_vp.model.ReservationCartBooks;
import mk.ukim.finki.library_vp.repository.ReservationCartBooksRepository;
import mk.ukim.finki.library_vp.service.ReservationCartBooksService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ReservationCartBooksServiceImpl implements ReservationCartBooksService {

    private final ReservationCartBooksRepository reservationCartBooksRepository;

    public ReservationCartBooksServiceImpl(ReservationCartBooksRepository reservationCartBooksRepository) {
        this.reservationCartBooksRepository = reservationCartBooksRepository;
    }


    @Override
    @Transactional
    public void deleteAllByResCartId(Long id) {

        this.reservationCartBooksRepository.deleteReservationCartBooksByResCartId(id);
    }
}
