package io.vividcode.webflux.starter.client;

import io.vividcode.webflux.starter.basic.User;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class RESTClient {
    public static void main(final String[] args) {
        final User user = new User();
        user.setName("Test");
        user.setEmail("test@example.org");
        final WebClient client = WebClient.create("http://localhost:8080/user");
        final Mono<User> createdUser = client.post()
                .uri("")
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(user), User.class)
                .exchange()
                .flatMap(response -> response.bodyToMono(User.class));
        System.out.println(createdUser.block());
    }
}
