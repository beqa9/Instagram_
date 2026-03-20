package Big_Project.Instagram.models;

public record UserModel(
        Long id,
        String username,
        String email,
        String profileImageUrl,
        String bio
){}