package Big_Project.Instagram.services;

import Big_Project.Instagram.entities.Post;
import Big_Project.Instagram.entities.User;
import Big_Project.Instagram.mappers.PostMapper;
import Big_Project.Instagram.models.PostModel;
import Big_Project.Instagram.repositories.PostRepository;
import Big_Project.Instagram.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostCacheService postCacheService;
    private final PostEventPublisher postEventPublisher;
    private final UserRepository userRepository;
    private final UserCacheService userCacheService;
    private final PostMapper postMapper;
    private final ImageStorageService imageStorageService;

    @Transactional
    @Override
    @CacheEvict(value = {"user-posts"}, allEntries = true)
    public PostModel createPost(String username, MultipartFile image, String caption) {

        User user = userCacheService.getUserByUsername(username);

        String imageUrl = imageStorageService.uploadImage(image);

        Post post = new Post();
        post.setUser(user);
        post.setImageUrl(imageUrl);
        post.setCaption(caption);

        post = postRepository.save(post);

        postEventPublisher.publish(
                new PostEvent(
                        post.getId(),
                        user.getId(),
                        user.getUsername(),
                        "CREATED"
                )
        );

        return postMapper.toModel(post);
    }

    public List<PostModel> getUserPosts(Long userId) {

        return postMapper.toModelList(
                postCacheService.getUserPostsEntity(userId)
        );
    }

    @Transactional
    @Override
    @CacheEvict(value = {"posts","user-posts"}, allEntries = true)
    public void deletePost(Long postId) {

        Post post = postCacheService.getPostEntityById(postId);

        postRepository.delete(post);
    }
}