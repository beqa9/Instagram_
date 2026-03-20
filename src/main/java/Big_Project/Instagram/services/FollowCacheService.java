package Big_Project.Instagram.services;

import Big_Project.Instagram.entities.Follow;
import Big_Project.Instagram.repositories.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowCacheService {

    private final FollowRepository followRepository;

    @Cacheable(value = "followers", key = "#userId")
    public List<Follow> getFollowers(Long userId) {
        return followRepository.findByFollowingId(userId);
    }

    @Cacheable(value = "following", key = "#userId")
    public List<Follow> getFollowing(Long userId) {
        return followRepository.findByFollowerId(userId);
    }
}