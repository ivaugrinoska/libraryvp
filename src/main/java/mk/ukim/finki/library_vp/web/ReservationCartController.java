package mk.ukim.finki.library_vp.web;

import mk.ukim.finki.library_vp.model.ReservationCart;
import mk.ukim.finki.library_vp.model.User;
import mk.ukim.finki.library_vp.service.CategoryService;
import mk.ukim.finki.library_vp.service.ReservationCartBooksService;

import mk.ukim.finki.library_vp.service.impl.ReservationCartServiceImpl;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/reservations")
public class ReservationCartController {

    private final ReservationCartServiceImpl reservationCartService;
    private final CategoryService categoryService;
    private final ReservationCartBooksService reservationCartBooksService;

    public ReservationCartController(ReservationCartServiceImpl reservationCartService, CategoryService categoryService, ReservationCartBooksService reservationCartBooksService) {
        this.reservationCartService = reservationCartService;
        this.categoryService = categoryService;
        this.reservationCartBooksService = reservationCartBooksService;
    }

    @GetMapping("/current")
    public String getReservationCartPage(@RequestParam(required = false) String error,
                                      HttpServletRequest req,
                                      Model model, Authentication authentication) {

        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("categories",this.categoryService.findAll());
        User user = (User) authentication.getPrincipal();
        String username = user.getUsername();
        ReservationCart reservationCart = this.reservationCartService.findCartByUser(user);
        model.addAttribute("books", this.reservationCartService.
                listAllBooksInReservationCart(reservationCart.getId()));
        model.addAttribute("reservationCart", this.reservationCartService.findById(reservationCart.getId()));
        model.addAttribute("bodyContent","reservationCart");
        return "master-template";
    }



    @PostMapping("/add-book/{id}")
    public String addBookToReservationCart(@PathVariable Long id,
                                           HttpServletRequest req,
                                           Authentication authentication) {
        try {
            User user = (User) authentication.getPrincipal();
            ReservationCart reservationCart = this.reservationCartService.findCartByUser(user);

            reservationCart = this.reservationCartService.addBookToReservationCart(user.getUsername(), id);
            return "redirect:/reservations/current";
        } catch(RuntimeException exception) {
            return "redirect:/reservations/current?error=" + exception.getMessage();
        }
    }

    @DeleteMapping("/delete/{id}")
    public String checkout(@PathVariable Long id) {
        try {
            this.reservationCartBooksService.deleteAllByResCartId(id);
            return "redirect:/reservations/current";
       } catch(RuntimeException exception) {
           return "redirect:/reservations/current?error=" + exception.getMessage();
       }
    }


}
