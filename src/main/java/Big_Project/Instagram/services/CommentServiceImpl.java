package Big_Project.Instagram.services;

import Big_Project.Instagram.entities.Comment;
import Big_Project.Instagram.entities.Post;
import Big_Project.Instagram.entities.User;
import Big_Project.Instagram.repositories.CommentRepository;
import Big_Project.Instagram.repositories.PostRepository;
import Big_Project.Instagram.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    @Override
    public void commentPost(String username, Long postId, String text) {

        User user = userRepository
                .findByUsernameAndDeletedFalse(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        Comment comment = new Comment();
        comment.setUser(user);
        comment.setPost(post);
        comment.setText(text);

        commentRepository.save(comment);
    }

    @Transactional
    @Override
    public void replyComment(String username, Long parentCommentId, String text) {

        User user = userRepository
                .findByUsernameAndDeletedFalse(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Comment parent = commentRepository.findById(parentCommentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        Comment reply = new Comment();
        reply.setUser(user);
        reply.setPost(parent.getPost());
        reply.setParent(parent);
        reply.setText(text);

        commentRepository.save(reply);
    }
}