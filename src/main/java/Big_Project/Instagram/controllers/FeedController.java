package Big_Project.Instagram.controllers;

import Big_Project.Instagram.entities.Post;
import Big_Project.Instagram.mappers.PostMapper;
import Big_Project.Instagram.models.PostModel;
import Big_Project.Instagram.services.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@RequestMapping("/api/feed")
@RequiredArgsConstructor
public class FeedController {

    private final FeedService feedService;
    private final PostMapper postMapper;

    @GetMapping("/{userId}")
    public Page<PostModel> getFeed(
            @PathVariable Long userId,
            Pageable pageable
    ) {
        return feedService.getFeed(userId, pageable)
                .map(postMapper::toModel);
    }

}