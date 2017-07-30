package com.demo.task.chatup.service;

import com.demo.task.chatup.datalayer.DbConstants;
import com.demo.task.chatup.datalayer.Message;
import com.demo.task.chatup.datalayer.SimpleUserDao;
import com.demo.task.chatup.datalayer.User;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    private JdbcTemplate mockJdbcTemplate;
    private SimpleUserDao simpleUserDao;
    private UserService userService;

    @BeforeMethod
    public void setUp() {
        mockJdbcTemplate = mock(JdbcTemplate.class);
        simpleUserDao = new SimpleUserDao(mockJdbcTemplate);
        final User mockUser    = mock(User.class);
        when(mockUser.getUserDao()).thenReturn(simpleUserDao);
        userService = new UserService(mockUser);
    }

    /**
     * Verifying that update query for a Insert Message executes exactly once.
     */
    @Test
    public void testSendMessage() {
        final String to = "user1";
        final String from = "user2";
        final String message = "Testing Message";
        final String now = DbConstants.MSG_DATE_FORMAT.format(new Date());
        userService.persistMessage(to, from, message);
        verify(mockJdbcTemplate, times(1)).update(DbConstants.INSERT_MSG_QRY, new Object[]{
                to,
                from,
                message,
                now
        });
    }

    /**
     * Testing the sending of the empty message.
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testSendEmptyMessage() {
        final String to = "user1";
        final String from = "user2";
        final String message = "";
        userService.persistMessage(to, from, message);
    }

    /**
     * Testing exception less execution  of the method
     */
    @Test
    public void testShowMessages()  {
        final String to = "user1";
        final String from = "user2";
        List<Message> messages = userService.getChatHistory(to, from);
    }

    /**
     * Fetching messages where to = from
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testShowMessagesWhenSenderisReceiver ( ) {
        final String to = "user1";
        final String from = "user1";
        List<Message> messages = userService.getChatHistory(to, from);

    }

}