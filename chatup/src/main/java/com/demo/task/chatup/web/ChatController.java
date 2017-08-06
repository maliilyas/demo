package com.demo.task.chatup.web;

import com.demo.task.chatup.datalayer.Message;
import com.demo.task.chatup.datalayer.SimpleUserDao;
import com.demo.task.chatup.datalayer.User;
import com.google.common.annotations.VisibleForTesting;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * ChatController for sending and receiving the messages among loggedInUsers
 * and maintain the session.
 */

@RestController
@RequestMapping(ChatController.APP_PREFIX)
public class ChatController {

    public static final String APP_PREFIX               = "/chat";
    public static final String MESSAAGE_BROKER_ENDPOINT = "/send";
    public static final String LOGIN_ENDPOINT           = "/login";

    @Autowired
    private SimpleUserDao userService;
    protected Set<User> loggedInUsers = new HashSet();

    @RequestMapping(value = "/login", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {"application/json"})
    @ApiOperation(value = "login", notes = "Login User with username and password.")
    public List<Message> login(@ApiParam (value="user to be logged in", required = true) @RequestBody final User user) {
        if (!loggedInUsers.contains(user)) {
            initUserSession(user);
            return userService.fetchMessages(user.getName());
        }
        return new ArrayList();

    }

    @RequestMapping(value = MESSAAGE_BROKER_ENDPOINT, method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {"application/json"})
    @ApiOperation(value = "persistMessage", notes = "Sending the Message to the user.")
    @SendTo("/chat/sent_message")
    public String sendMessage(@ApiParam(value="Message from a sender", required = true) @RequestBody final Message msg) {
        checkNotNull(this.userService, "User has not be Logged In.");
        this.userService.insertMessage(msg.getTo(), msg.getFrom(), msg.getMessage());
        return msg.getMessage();
    }

    @VisibleForTesting
    protected void initUserSession(final User user) {
        checkNotNull(user, "User can not be null!!!");
        user.setLoggedIn(true);
        loggedInUsers.add(user);
    }

    protected void signOff(final User user) {
        checkNotNull(user, "User can not be null!!!");
        user.setLoggedIn(false);
        loggedInUsers.remove(user);

    }

}
