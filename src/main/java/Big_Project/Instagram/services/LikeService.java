package Big_Project.Instagram.services;

public interface LikeService {

    void likePost(String username, Long postId);

    void unlikePost(String username, Long postId);
}