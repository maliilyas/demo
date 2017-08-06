package com.demo.task.chatup.datalayer;

import org.springframework.util.StringUtils;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class Message {
    private String to;
    private String from;
    private String message;
    private String sentTime;

    public Message() {
        // for serializing the object, default const needed.
    }

    public Message(final String to, final String from, final String message,
                   final String sentTime) {
        checkArgument(! StringUtils.isEmpty(to), "Receiver is emtpy or null!!!");
        checkArgument(! StringUtils.isEmpty(from), "Sender is empty or null!!!");
        this.to   = to;
        this.from = from;
        this.message = message;
        this.sentTime = checkNotNull(sentTime);
    }

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }

    public String getMessage() {
        return message;
    }

    public String getSentTime() {
        return sentTime;
    }



}