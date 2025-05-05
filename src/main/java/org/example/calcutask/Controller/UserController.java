package org.example.calcutask.Controller;
import org.springframework.stereotype.Controller;
import org.example.calcutask.Service.UserService;

@Controller
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
}
