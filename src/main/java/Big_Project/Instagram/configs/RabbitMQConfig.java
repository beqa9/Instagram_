package Big_Project.Instagram.configs;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE = "instagram.exchange";

    public static final String POST_QUEUE = "post.queue";

    public static final String POST_ROUTING_KEY = "post.created";

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Queue postQueue() {
        return new Queue(POST_QUEUE);
    }

    @Bean
    public Binding postBinding() {

        return BindingBuilder
                .bind(postQueue())
                .to(exchange())
                .with(POST_ROUTING_KEY);

    }
}
