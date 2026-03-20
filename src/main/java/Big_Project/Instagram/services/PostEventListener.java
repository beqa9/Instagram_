package Big_Project.Instagram.services;

import Big_Project.Instagram.configs.RabbitMQConfig;
import Big_Project.Instagram.repositories.FollowRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostEventListener {

    private final EmailService emailService;
    private final FollowRepository followRepository;

    @RabbitListener(queues = RabbitMQConfig.POST_QUEUE)
    public void handlePostEvent(PostEvent event) {

        log.info("Received Post Event: {}", event);

        // get followers
        var followers = followRepository.findByFollowingId(event.userId());

        for (var follow : followers) {

            String email = follow.getFollower().getEmail();

            emailService.sendEmail(
                    email,
                    "New Post!",
                    "User " + event.username() + " created a new post!"
            );
        }
    }
}