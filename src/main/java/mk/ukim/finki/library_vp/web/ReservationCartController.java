package mk.ukim.finki.library_vp.web;

import mk.ukim.finki.library_vp.model.ReservationCart;
import mk.ukim.finki.library_vp.model.User;
import mk.ukim.finki.library_vp.service.impl.ReservationCartServiceImpl;
import mk.ukim.finki.library_vp.service.impl.UserServiceImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reservations")
public class ReservationCartController {

    private final ReservationCartServiceImpl reservationCartService;
    private final UserServiceImpl userService;

    public ReservationCartController(ReservationCartServiceImpl reservationCartService, UserServiceImpl userService) {
        this.reservationCartService = reservationCartService;
        this.userService = userService;
    }

    @GetMapping("/current")
    public String currentReservationCart(Model model)
    {
        String username = "";
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        User user = userService.findUserByUsername(username);
        ReservationCart cart = reservationCartService.findCartByUser(user);
        model.addAttribute("currentCart",cart);
        return "reservationCart";
    }

//    @GetMapping("/new/{id}")
//    public String addBookToReservationCartPage(@PathVariable Long id, Model model)
//    {
//
//    }
//
//    @PostMapping("/new")
//    public String addBookToReservationCartPage(@RequestParam Long bookId,
//                                               @RequestParam Integer quantity)
//    {
            //dodaj vo reservation cart nov zapis, pa vo reservation_cart_books novi knigi
//        return "redirect:/books"+bookId;
//    }
}
