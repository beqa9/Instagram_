package Big_Project.Instagram.services;

import Big_Project.Instagram.entities.Post;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface FeedService {

    Page<Post> getFeed(Long userId, Pageable pageable);
}