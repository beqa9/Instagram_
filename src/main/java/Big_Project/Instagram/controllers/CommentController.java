package Big_Project.Instagram.controllers;


import Big_Project.Instagram.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/post/{postId}")
    public void commentPost(
            @PathVariable Long postId,
            @RequestParam String text,
            Authentication authentication
    ) {
        commentService.commentPost(authentication.getName(), postId, text);
    }

    @PostMapping("/reply/{commentId}")
    public void replyComment(
            @PathVariable Long commentId,
            @RequestParam String text,
            Authentication authentication
    ) {
        commentService.replyComment(authentication.getName(), commentId, text);
    }

}