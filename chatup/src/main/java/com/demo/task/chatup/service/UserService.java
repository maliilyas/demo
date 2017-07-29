package com.demo.task.chatup.service;

import com.demo.task.chatup.datalayer.Message;
import com.demo.task.chatup.datalayer.SimpleUserDao;
import com.demo.task.chatup.datalayer.UserDao;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

@Service
public class UserService {

    private final UserDao simpleUserDao;

    public UserService(final UserDao simpleUserDao) {
        this.simpleUserDao = checkNotNull(simpleUserDao);
    }


    public void sendMessage(final Long to, final Long from,
                            final String message) {
        checkArgument(! StringUtils.isEmpty(message), "Won't store Empty Message!");
        this.simpleUserDao.insertMessage(to, from, message);
    }

    public List<Message> getChatHistory(final Long to, final Long from) {
        checkArgument(! to.equals(from), "Cant send yourself something, it's not facebook!");
        return this.simpleUserDao.fetchMessages(to, from);
    }
}
