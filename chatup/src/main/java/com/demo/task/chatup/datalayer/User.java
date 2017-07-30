package com.demo.task.chatup.datalayer;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

public class User {

    private long id;
    private String name;
    private String password;
    private Map<Long, List<Message>> friendMessagesMap = new HashMap();

    @Autowired
    private UserDao userDao;

    public User(final Long id, final String name, final String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public void addMessagesFromFriend(final Long friendId, final String message) {
        final Instant now = Instant.now();
        final Message messasge = new Message(this.id, friendId, message, DbConstants.MSG_DATE_FORMAT.format(now));
        final List<Message> messages ;
        messages = this.friendMessagesMap.containsKey(friendId) == true ? this.friendMessagesMap.get(friendId) : new ArrayList();
        messages.add(messasge);
        this.friendMessagesMap.put(friendId, messages);
    }

    public UserDao getUserDao() {
        return this.userDao;
    }
}
