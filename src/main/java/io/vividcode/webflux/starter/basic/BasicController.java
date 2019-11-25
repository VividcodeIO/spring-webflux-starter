package io.vividcode.webflux.starter.basic;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class BasicController {
    @GetMapping("/hello_world")
    public Mono<String> sayHelloWorld() {
        return Mono.just("Hello World");
    }
}
