package com.demo.task.chatup.integration;

import com.demo.task.chatup.ChatUpApplication;
import com.demo.task.chatup.web.ChatController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.Test;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ChatUpApplication.class)
@TestPropertySource("classpath:application.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class ChatUpControllerIT extends AbstractTestNGSpringContextTests {

    //Testing port
    @Value("${local.server.port}")
    private int port;
    private final RestTemplate restTemplate = new RestTemplate();
    private final String APP_URL = "http://localhost:" + port+"/" + ChatController.APP_PREFIX;

    @Test
    public void testLoginUser() {

    }

    @Test
    public void testSignOutUser() {

    }

    @Test
    public void testSendingMessage() {

    }
}
