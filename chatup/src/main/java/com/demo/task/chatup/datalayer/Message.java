package com.demo.task.chatup.datalayer;

import static com.google.common.base.Preconditions.checkNotNull;

public class Message {
    private final Long to;
    private final Long from;
    private final String message;
    private final String sentTime;

    public Message(final Long to, final Long from, final String message,
                   final String sentTime) {
        this.to   = checkNotNull(to);
        this.from = checkNotNull(from);
        this.message = checkNotNull(message);
        this.sentTime = checkNotNull(sentTime);
    }

    public Long getTo() {
        return to;
    }

    public Long getFrom() {
        return from;
    }

    public String getMessage() {
        return message;
    }

    public String getSentTime() {
        return sentTime;
    }



}