package com.demo.task.chatup.datalayer;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

public class User {

    private String name;
    private String password;
    private Map<String, List<Message>> friendMessagesMap = new HashMap();

    @Autowired
    private UserDao userDao;

    public User(final String name, final String password) {
        this.name = name;
        this.password = password;
    }

    public void addMessagesFromFriend(final String sender, final String message) {
        final Instant now = Instant.now();
        final Message messasge = new Message(this.name, sender, message, DbConstants.MSG_DATE_FORMAT.format(now));
        final List<Message> messages ;
        messages = this.friendMessagesMap.containsKey(sender) == true ? this.friendMessagesMap.get(sender) : new ArrayList();
        messages.add(messasge);
        this.friendMessagesMap.put(sender, messages);
    }

    public UserDao getUserDao() {
        return this.userDao;
    }
}
