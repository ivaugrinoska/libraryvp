package mk.ukim.finki.library_vp.web;

import mk.ukim.finki.library_vp.model.ReservationCart;
import mk.ukim.finki.library_vp.model.User;
import mk.ukim.finki.library_vp.service.CategoryService;
import mk.ukim.finki.library_vp.service.impl.ReservationCartServiceImpl;
import mk.ukim.finki.library_vp.service.impl.UserServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/reservations")
public class ReservationCartController {

    private final ReservationCartServiceImpl reservationCartService;
    private final UserServiceImpl userService;
    private final CategoryService categoryService;

    public ReservationCartController(ReservationCartServiceImpl reservationCartService, UserServiceImpl userService, CategoryService categoryService) {
        this.reservationCartService = reservationCartService;
        this.userService = userService;
        this.categoryService = categoryService;
    }

//    @GetMapping("/current")
//    public String currentReservationCart(Model model)
//    {
//        String username = "";
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (principal instanceof UserDetails) {
//            username = ((UserDetails)principal).getUsername();
//        } else {
//            username = principal.toString();
//        }
//        User user = userService.findUserByUsername(username);
//        ReservationCart cart = reservationCartService.findCartByUser(user);
//        model.addAttribute("categories",this.categoryService.findAll());
//        model.addAttribute("currentCart",cart);
//        model.addAttribute("bodyContent", "reservationCart");
//        return "master-template";
//    }
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
        String username = req.getRemoteUser();
        ReservationCart reservationCart = this.reservationCartService.findCartByUser(user);
        model.addAttribute("books", this.reservationCartService.
                listAllBooksInReservationCart(reservationCart.getId()));
        model.addAttribute("bodyContent","reservationCart");
        return "master-template";
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
////            dodaj vo reservation cart nov zapis, pa vo reservation_cart_books novi knigi
//
//        return "redirect:/books"+bookId;
//    }

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

    @PostMapping("/delete")
    public String checkout(Authentication authentication) {
        try {
            User user = (User) authentication.getPrincipal();
            this.reservationCartService.checkout();
            return "redirect:/reservations/current";
        } catch(RuntimeException exception) {
            return "redirect:/reservations/current?error=" + exception.getMessage();
        }
    }


}
