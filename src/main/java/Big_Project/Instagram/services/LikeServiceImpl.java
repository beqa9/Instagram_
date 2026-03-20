package Big_Project.Instagram.services;


import Big_Project.Instagram.entities.Like;
import Big_Project.Instagram.entities.Post;
import Big_Project.Instagram.entities.User;
import Big_Project.Instagram.repositories.LikeRepository;
import Big_Project.Instagram.repositories.PostRepository;
import Big_Project.Instagram.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    @Override
    @CacheEvict(value = {"posts","feed"}, allEntries = true)
    public void likePost(String username, Long postId) {

        User user = userRepository
                .findByUsernameAndDeletedFalse(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        Like like = new Like();
        like.setUser(user);
        like.setPost(post);

        likeRepository.save(like);
    }

    @Transactional
    @Override
    @CacheEvict(value = {"posts","feed"}, allEntries = true)
    public void unlikePost(String username, Long postId) {

        User user = userRepository
                .findByUsernameAndDeletedFalse(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Like like = likeRepository
                .findByUserIdAndPostId(user.getId(), postId)
                .orElseThrow(() -> new RuntimeException("Like not found"));

        likeRepository.delete(like);
    }
}