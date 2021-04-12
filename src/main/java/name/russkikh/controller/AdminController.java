package name.russkikh.controller;

import name.russkikh.model.User;
import name.russkikh.service.RoleService;
import name.russkikh.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@Controller
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String getUserPage(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @GetMapping("/user/new")
    public String createNewUser(@ModelAttribute("user") User user,
                                Model model) {
        model.addAttribute("allRoles", new HashSet<>(roleService.findAll()));
        return "new";
    }

    @PostMapping("/admin")
    public String createNewUser(@ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            return "new";
        }
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/user/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.getOne(id));
        model.addAttribute("allRoles", new HashSet<>(roleService.findAll()));
        return "edit";
    }

    @PatchMapping("/user/{id}")
    public String updateUser(@PathVariable long id, User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            user.setId(id);
            return "edit";
        }
        userService.save(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable long id, Model model) {
        userService.deleteById(id);
        return "redirect:/admin";
    }
}
