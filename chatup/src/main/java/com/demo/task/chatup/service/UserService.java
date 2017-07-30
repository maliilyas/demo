package com.demo.task.chatup.service;

import com.demo.task.chatup.datalayer.Message;
import com.demo.task.chatup.datalayer.SimpleUserDao;
import com.demo.task.chatup.datalayer.User;
import com.demo.task.chatup.datalayer.UserDao;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

@Service
public class UserService {

    private final User user;

    public UserService(final User user) {
        this.user = checkNotNull(user);
    }


    public void persistMessage(final String to, final String from,
                               final String message) {
        checkArgument(! StringUtils.isEmpty(message), "Won't store Empty Message!");
        this.user.getUserDao().insertMessage(to, from, message);
    }

    public List<Message> getChatHistory(final String to, final String from) {
        checkArgument(! to.equals(from), "Cant send yourself something, it's not facebook!");
        return this.user.getUserDao().fetchMessages(to, from);
    }
}
