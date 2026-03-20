package Big_Project.Instagram.controllers;

import Big_Project.Instagram.entities.Follow;
import Big_Project.Instagram.services.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/follows")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @PostMapping("/{userId}")
    public void followUser(
            @PathVariable Long userId,
            Authentication authentication
    ) {
        followService.followUser(authentication.getName(), userId);
    }

    @DeleteMapping("/{userId}")
    public void unfollowUser(
            @PathVariable Long userId,
            Authentication authentication
    ) {
        followService.unfollowUser(authentication.getName(), userId);
    }

    @GetMapping("/followers/{userId}")
    public List<Follow> getFollowers(@PathVariable Long userId) {
        return followService.getFollowers(userId);
    }

    @GetMapping("/following/{userId}")
    public List<Follow> getFollowing(@PathVariable Long userId) {
        return followService.getFollowing(userId);
    }

}