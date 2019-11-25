package io.vividcode.webflux.starter.basic;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
class UserService {
    private final Map<String, User> data = new ConcurrentHashMap<>();

    Flux<User> list() {
        return Flux.fromIterable(this.data.values());
    }

    Flux<User> getById(final Flux<String> ids) {
        return ids.flatMap(id -> Mono.justOrEmpty(this.data.get(id)));
    }

    Mono<User> getById(final String id) {
        return Mono.justOrEmpty(this.data.get(id))
                .switchIfEmpty(Mono.error(new ResourceNotFoundException()));
    }

    Flux<User> createOrUpdate(final Flux<User> users) {
        return users.doOnNext(user -> this.data.put(user.getId(), user));
    }

    Mono<User> createOrUpdate(final User user) {
        this.data.put(user.getId(), user);
        return Mono.just(user);
    }

    Mono<User> delete(final String id) {
        return Mono.justOrEmpty(this.data.remove(id));
    }
}
