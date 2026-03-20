package Big_Project.Instagram.services;

import Big_Project.Instagram.entities.Post;
import Big_Project.Instagram.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostCacheService {

    private final PostRepository postRepository;

    @Cacheable(value = "posts", key = "#id")
    public Post getPostEntityById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }

    @Cacheable(value = "user-posts", key = "#userId")
    public List<Post> getUserPostsEntity(Long userId) {
        return postRepository.findByUserId(userId);
    }
}