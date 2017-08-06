package com.demo.task.chatup.web;

import com.demo.task.chatup.datalayer.Message;
import com.demo.task.chatup.datalayer.SimpleUserDao;
import com.demo.task.chatup.datalayer.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockingDetails;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;


public class ChatControllerTest {

    private SimpleUserDao simpleUserDao;
    private ChatController controller ;
    @BeforeClass
    public void setUp() {
        simpleUserDao = mock(SimpleUserDao.class);
        controller    = mock(ChatController.class);

    }
    @Test
    public void testLoginUser() {
        List<Message> expected = new ArrayList(5);
        when(controller.login(any())).thenReturn(expected);
        List<Message> actual = controller.login(any());
        assertTrue(actual.size() == expected.size());
    }

    @Test
    public void testUserSession() {
        final User user = new User("ali","ilyas");
        final ChatController controller = new ChatController();
        controller.initUserSession(user);
        assertTrue(controller.loggedInUsers.contains(user));

        controller.signOff(user);
        assertTrue(!controller.loggedInUsers.contains(user));

    }

}