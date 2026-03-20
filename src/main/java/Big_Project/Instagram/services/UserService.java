package Big_Project.Instagram.services;

import Big_Project.Instagram.entities.User;
import Big_Project.Instagram.models.CreateUserRequest;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Long id);

    User register(CreateUserRequest request);

    void deleteUser(Long id);
}