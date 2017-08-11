package com.demo.task.chatup.integration;

import com.demo.task.chatup.ChatUpApplication;
import com.demo.task.chatup.configuration.ChatUpConfig;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by aliilyas on 11/08/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChatUpApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WebAppConfiguration
public class ChatUpIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeClass
    public void setUp() {
        restTemplate = new TestRestTemplate();
    }
    @Test
    public void testLoginUser() {
    }

    @Test
    public void testLogOutUser() {
    }

    @Test
    public void testsendChat() {
    }
}
