/*
 * package com.casestudy.car;
 * 
 * import org.junit.jupiter.api.Test; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.boot.test.context.SpringBootTest; import
 * org.springframework.stereotype.Service;
 * 
 * @SpringBootTest class CarApplicationTests {
 * 
 * // A service that calls out over HTTP
 * 
 * @Autowired private Service service;
 * 
 * @BeforeEach public void setup() { this.service.setBase("http://localhost:" +
 * this.environment.getProperty("wiremock.server.port")); }
 * 
 * @Test void contextLoads() { }
 * 
 * }
 */