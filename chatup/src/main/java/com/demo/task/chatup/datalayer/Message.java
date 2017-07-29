package com.demo.task.chatup.datalayer;

import static com.google.common.base.Preconditions.checkNotNull;

public class Message {
    private final Long from;
    private final String message;
    private final String sentTime;

    public Message(final Long from, final String message,
                   final String sentTime) {
        this.from = checkNotNull(from);
        this.message = checkNotNull(message);
        this.sentTime = checkNotNull(sentTime);

    }
}