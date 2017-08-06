package com.demo.task.chatup.web;

import com.demo.task.chatup.datalayer.Message;
import com.demo.task.chatup.datalayer.User;
import com.demo.task.chatup.service.UserService;
import com.google.common.annotations.VisibleForTesting;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * ChatController for sending and receiving the messages among users
 * and maintain the session.
 */

@RestController
@RequestMapping(ChatController.APP_PREFIX)
public class ChatController {

    public static final String APP_PREFIX = "/chat";
    public static final String MESSAAGE_BROKER_ENDPOINT = "/send";

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    private UserService userService;

    @RequestMapping(value="/login", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {"application/json"})
    @ApiOperation(value = "login", notes = "Login User with username and password.")
    public void login(@RequestBody final User user) {

    }

    @RequestMapping(value=MESSAAGE_BROKER_ENDPOINT, method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {"application/json"})
    @ApiOperation(value = "persistMessage", notes = "Sending the Message to the user.")
    public void sendMessage(@RequestBody final Message msg) {
        if(this.userService != null) {
            this.userService.persistMessage(msg.getTo(), msg.getFrom(), msg.getMessage());
            // notify receiver
        } else {
            throw new IllegalStateException("User Service is not initated!!!");
        }

    }

    @VisibleForTesting
    protected void initUserSession(final User user) {
        checkNotNull(user, "User can not be null!!!");
        this.userService = new UserService(user);
    }

}
