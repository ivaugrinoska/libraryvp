package mk.ukim.finki.library_vp.web;


import mk.ukim.finki.library_vp.model.User;
import mk.ukim.finki.library_vp.service.UserReadBooksService;
import mk.ukim.finki.library_vp.service.impl.UserServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userService;
    private final UserReadBooksService userReadBooksService;

    public UserController(UserServiceImpl userService, UserReadBooksService userReadBooksService) {
        this.userService = userService;
        this.userReadBooksService = userReadBooksService;
    }



    @GetMapping("/profile")
    public String userProfile(Model model)
    {
        String username = "";
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
                 username = ((UserDetails)principal).getUsername();
            } else {
                 username = principal.toString();
            }
        UserDetails user = userService.loadUserByUsername(username);
        model.addAttribute("books", this.userReadBooksService.
                listAllBooksInProfile(username));
        model.addAttribute("loggedUser",user);
        return "profile";
    }

    @PostMapping("/add-book/{id}")
    public String addBookToProfile(@PathVariable Long id,
                                           Authentication authentication) {
        try {
            User user = (User) authentication.getPrincipal();
            this.userReadBooksService.addBookToProfile(user.getUsername(), id);
            return "redirect:/users/profile";
        } catch(RuntimeException exception) {
            return "redirect:/users/profile?error=" + exception.getMessage();
        }
    }

}
