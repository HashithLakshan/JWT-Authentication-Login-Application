package User.Login.com.example.Backend.controller;

import User.Login.com.example.Backend.service.UserService;
import User.Login.com.example.Backend.utill.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_EMPLOYEE')")
    @GetMapping("/getAllUsers")
    public CommonResponse getAllUsers(
                                     @RequestParam String commonStatus,
                                      @RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "5") int size) {
        return userService.getAllUsers(commonStatus,page,size);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/updateStatus")
    public CommonResponse updateStatus(@RequestParam String commonStatus,
                                       @RequestParam String userId){
        return userService.updateStatus(commonStatus,userId);
    }
}
