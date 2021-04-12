package name.russkikh.controller;

import name.russkikh.model.User;
import name.russkikh.service.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public String show(@PathVariable long id, Model model) {
        User user = userService.findById(id).orElseThrow(() -> new UsernameNotFoundException("Invalid user id " + id));
        model.addAttribute("user", user);
        return "show";
    }
}
