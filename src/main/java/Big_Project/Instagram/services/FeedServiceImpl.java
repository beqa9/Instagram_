package Big_Project.Instagram.services;

import Big_Project.Instagram.entities.Post;
import Big_Project.Instagram.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService {

    private final PostRepository postRepository;

    @Cacheable(value = "feed", key = "#userId")
    @Override
    public Page<Post> getFeed(Long userId, Pageable pageable) {
        return postRepository.getFeed(userId, pageable);
    }
}