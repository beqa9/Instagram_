package Big_Project.Instagram.controllers;

import Big_Project.Instagram.mappers.UserMapper;
import Big_Project.Instagram.models.CreateUserRequest;
import Big_Project.Instagram.models.UserModel;
import Big_Project.Instagram.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public List<UserModel> getUsers(){
        return userMapper.toModelList(userService.getAllUsers());
    }


}