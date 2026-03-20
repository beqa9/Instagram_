package Big_Project.Instagram.services;

import Big_Project.Instagram.entities.Follow;
import Big_Project.Instagram.entities.User;
import Big_Project.Instagram.repositories.FollowRepository;
import Big_Project.Instagram.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    private final FollowCacheService followCacheService;

    @Transactional
    @Override
    @CacheEvict(value = {"followers","following","feed"}, allEntries = true)
    public void followUser(String username, Long followingId) {

        User follower = userRepository
                .findByUsernameAndDeletedFalse(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        User following = userRepository.findById(followingId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Follow follow = new Follow();
        follow.setFollower(follower);
        follow.setFollowing(following);

        followRepository.save(follow);
    }

    @Transactional
    @Override
    @CacheEvict(value = {"followers","following","feed"}, allEntries = true)
    public void unfollowUser(String username, Long followingId) {

        User follower = userRepository
                .findByUsernameAndDeletedFalse(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Follow follow = followRepository
                .findByFollowerIdAndFollowingId(follower.getId(), followingId)
                .orElseThrow(() -> new RuntimeException("Follow not found"));

        followRepository.delete(follow);
    }

    @Override
    public List<Follow> getFollowers(Long userId) {

        return followCacheService.getFollowers(userId);

    }

    @Override
    public List<Follow> getFollowing(Long userId) {

        return followCacheService.getFollowing(userId);

    }
}