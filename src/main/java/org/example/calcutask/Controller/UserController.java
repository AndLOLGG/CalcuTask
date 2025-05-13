
package org.example.calcutask.Controller;
import org.springframework.stereotype.Controller;
import org.example.calcutask.Service.UserService;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // This should correspond to a `login.html` file in `src/main/resources/templates`
    }
}





/**
package org.example.calcutask.Controller;
//
//import jakarta.servlet.http.HttpSession;
//import org.example.calcutask.Service.UserService;
import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//
@Controller
public class UserController {
//    private final UserService userService;
//
//    public ProjectController(UserService userService) {
//        this.userService = userService;
//    }
//    @GetMapping("/user/create")
//    public String showCreateForm(Model model) {
//        model.addAttribute("user", new User());
//        return "add-user";
//    }
//    @PostMapping("/user/create")
//    public String addUser(@ModelAttribute User user) {
//        userService.addUser(user);
//        return "redirect:/login";
//    }
//    @GetMapping("/user/{id}/edit")
//    public String showEditUserForm(@PathVariable("id") int userId, HttpSession session, Model model) {
//        Integer loggedInUserId = (Integer) session.getAttribute("userId");
//        if (loggedInUserId == null || loggedInUserId != userId) {
//            return "redirect:/";
//        }
//
//        User user = userService.getUserById(userId);
//        model.addAttribute("user", user);
//        return "edit-user";
//    }
//
//    @PostMapping("/user/{id}/edit")
//    public String updateUser(@PathVariable("id") int userId, @ModelAttribute User user, HttpSession session) {
//        Integer loggedInUserId = (Integer) session.getAttribute("userId");
//        if (loggedInUserId == null || loggedInUserId != userId) {
//            return "redirect:/";
//        }
//        user.setUserId(userId);
//        userService.updateUser(user);
//        return "redirect:/home";
//    }
//    @GetMapping("/user/delete/confirm/{id}")
//    public String confirmDeleteUser(@PathVariable int id, Model model, HttpSession session) {
//        Integer loggedInUserId = (Integer) session.getAttribute("userId");
//        if (loggedInUserId == null) {
//            return "redirect:/login";
//        }
//
//        User user = userService.getUserById(id);
//
//        if (user == null || user.getUserId() != loggedInUserId) {
//            return "redirect:/";
//        }
//
//        model.addAttribute("user", user);
//        return "confirm-delete-user";
//    }
//    @PostMapping("/user/delete/{id}")
//    public String deleteUser(@PathVariable int id, HttpSession session) {
//        Integer loggedInUserId = (Integer) session.getAttribute("userId");
//
//        if (loggedInUserId == null || loggedInUserId != id) {
//            return "redirect:/login";
//        }
//
//        userService.deleteUserById(id);
//        session.invalidate();
//        return "redirect:/";
//    }
//
//
}

**/

