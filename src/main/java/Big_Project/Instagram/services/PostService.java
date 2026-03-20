package Big_Project.Instagram.services;

import Big_Project.Instagram.entities.Post;
import Big_Project.Instagram.models.PostModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {

    PostModel createPost(String username, MultipartFile image, String caption);

    List<PostModel> getUserPosts(Long userId);

    void deletePost(Long postId);

}