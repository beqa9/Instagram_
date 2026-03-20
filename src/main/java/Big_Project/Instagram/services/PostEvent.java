package Big_Project.Instagram.services;

public record PostEvent(
        Long postId,
        Long userId,
        String username,
        String type
) {
}
