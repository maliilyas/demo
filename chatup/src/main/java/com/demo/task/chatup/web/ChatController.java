package com.demo.task.chatup.web;

import com.demo.task.chatup.datalayer.User;
import com.demo.task.chatup.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * ChatController for sending and receiving the messages among users
 * and maintain the session.
 */

@RestController
@RequestMapping("/chat")
public class ChatController {
    private final UserService userService;
    public ChatController(final UserService userService) {
        this.userService = checkNotNull(userService);
    }

    @RequestMapping(value="/login")
    public void login(final User user) {

    }

    @RequestMapping(value="/send")
    public void sendMessage(final Long to, final Long from, final String message) {

    }

    @RequestMapping(value="/receive")
    public void receiveMessage(final Long from, final Long to, final String message) {

    }

}
