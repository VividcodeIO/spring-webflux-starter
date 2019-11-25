package io.vividcode.webflux.starter.basic;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

public class UserControllerTest {

	private final WebTestClient client = WebTestClient.bindToServer().baseUrl("http://localhost:8080").build();

	@Test
	public void testCreateUser() throws Exception {
		final User user = new User();
		user.setName("Test");
		user.setEmail("test@example.org");
		client.post().uri("/user")
				.contentType(MediaType.APPLICATION_JSON)
				.body(Mono.just(user), User.class)
				.exchange()
				.expectStatus().isOk()
				.expectBody().jsonPath("name").isEqualTo("Test");
	}
}
