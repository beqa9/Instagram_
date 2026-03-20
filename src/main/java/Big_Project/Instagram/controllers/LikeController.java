package Big_Project.Instagram.controllers;

import Big_Project.Instagram.services.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/{postId}")
    public void likePost(
            @PathVariable Long postId,
            Authentication authentication
    ) {
        likeService.likePost(authentication.getName(), postId);
    }

    @DeleteMapping("/{postId}")
    public void unlikePost(
            @PathVariable Long postId,
            Authentication authentication
    ) {
        likeService.unlikePost(authentication.getName(), postId);
    }

}