package com.demo.task.chatup.datalayer;

import java.util.List;

/**
 * Interface for User Data Access Object, so we could have any type of implementation for User Access.
 */
public interface UserDao {

    /**
     * Persisting the chat history.
     * @param to, id of the receiver.
     * @param from, id of the sender.
     * @param message, message
     */
    public void insertMessage(final String to, final String from, final String message);

    /**
     *
     * @param to, id of the receiver.
     * @param from, id of the sender.
     * @return The List of messages.
     */
    public List<Message> fetchMessages(final String to, final String from);
}

