package Big_Project.Instagram.controllers;

import Big_Project.Instagram.models.PostModel;
import Big_Project.Instagram.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public PostModel createPost(
            @RequestParam MultipartFile image,
            @RequestParam String caption,
            Authentication authentication
    ) {

        return postService.createPost(
                authentication.getName(),
                image,
                caption
        );
    }

    @GetMapping("/{userId}")
    public List<PostModel> getUserPosts(@PathVariable Long userId) {
        return postService.getUserPosts(userId);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
    }

}