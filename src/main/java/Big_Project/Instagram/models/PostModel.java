package Big_Project.Instagram.models;

public record PostModel(
        Long id,
        Long userId,
        String imageUrl,
        String caption
){}