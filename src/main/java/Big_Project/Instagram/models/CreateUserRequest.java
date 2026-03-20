package Big_Project.Instagram.models;

public record CreateUserRequest(
        String username,
        String email,
        String password,
        String bio
){}