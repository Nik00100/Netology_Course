package ru.netology.conditionalapp;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {
	private static GenericContainer<?> myapp1 = new GenericContainer<>("devapp")
			.withExposedPorts(8080);

	private static GenericContainer<?> myapp2 = new GenericContainer<>("prodapp")
			.withExposedPorts(8081);

	@Autowired
	TestRestTemplate restTemplate;

	@BeforeAll
	public static void setUp() {
		myapp1.start();
		myapp2.start();
	}

	@Test
	void contextLoads() {
		ResponseEntity<String> forEntity1 = restTemplate.getForEntity("http://localhost:" + myapp1.getMappedPort(8080), String.class);
		System.out.println(forEntity1.getBody());

		ResponseEntity<String> forEntity2 = restTemplate.getForEntity("http://localhost:" + myapp2.getMappedPort(8081), String.class);
		System.out.println(forEntity2.getBody());
	}

}