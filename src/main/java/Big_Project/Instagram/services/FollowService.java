package Big_Project.Instagram.services;

import Big_Project.Instagram.entities.Follow;

import java.util.List;

public interface FollowService {

    void followUser(String username, Long followingId);

    void unfollowUser(String username, Long followingId);

    List<Follow> getFollowers(Long userId);

    List<Follow> getFollowing(Long userId);

}