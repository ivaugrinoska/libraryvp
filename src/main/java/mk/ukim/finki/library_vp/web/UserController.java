package mk.ukim.finki.library_vp.web;

import mk.ukim.finki.library_vp.service.impl.UserServiceImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
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
        model.addAttribute("loggedUser",user);
        return "profile";
    }

}
