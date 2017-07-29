package com.demo.task.chatup.service;

import com.demo.task.chatup.datalayer.DbConstants;
import com.demo.task.chatup.datalayer.Message;
import com.demo.task.chatup.datalayer.SimpleUserDao;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class UserServiceTest {

    private JdbcTemplate mockJdbcTemplate;
    private SimpleUserDao simpleUserDao;
    private UserService userService;

    @BeforeMethod
    public void setUp() {
        mockJdbcTemplate = mock(JdbcTemplate.class);
        simpleUserDao = new SimpleUserDao(mockJdbcTemplate);
        userService = new UserService(simpleUserDao);
    }

    /**
     * Verifying that update query for a Insert Message executes exactly once.
     */
    @Test
    public void testSendMessage() {
        final Long to = 1L;
        final Long from = 2L;
        final String message = "Testing Message";
        final String now = DbConstants.MSG_DATE_FORMAT.format(new Date());
        userService.sendMessage(to, from, message);
        verify(mockJdbcTemplate, times(1)).update(DbConstants.INSERT_MSG_QRY, new Object[]{
                to,
                from,
                message,
                now
        });
    }

    /**
     * Testing exception less execution  of the method
     */
    @Test
    public void testShowMessages()  {
        final Long to = 1L;
        final Long from = 2L;
        List<Message> messages = userService.getChatHistory(to, from);
    }

    /**
     * Fetching messages where to = from
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testShowMessagesWhenSenderisReceiver ( ) {
        final Long to = 1L;
        final Long from = 1L;
        List<Message> messages = userService.getChatHistory(to, from);

    }

}