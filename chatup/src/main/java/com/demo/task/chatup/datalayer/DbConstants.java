package com.demo.task.chatup.datalayer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DbConstants {

    public static final DateFormat MSG_DATE_FORMAT = new SimpleDateFormat("YYYY-MM-DD HH:MM");
    public static final String CREATE_USER_TABLE_QRY = "CREATE TABLE IF NOT EXISTS USER"
            + " name VARCHAR(30) NOT NULL,"
            + " pass VARCHAR(20) NOT NULL,"
            + " ADD PRIMARY KEY (name)";
    public static final String CREATE_MESSAGE_TABLE_QRY = "CREATE TABLE IF NOT EXISTS MESSAGES"
            + " to VARCHAR(30) NOT NULL,"
            + " from VARCHAR(30) NOT NULL,"
            + " message VARCHAR(300) NOT NULL,"
            + " timeStamp VARCHAR(20) NOT NULL"
            + " msgId INTEGER IDENTITY PRIMARY KEY";

    public static final String INSERT_USER_QUERY     = "INSERT INTO USER (name, pass) values (? , ?)";
    public static final String INSERT_MSG_QRY = "INSERT INTO MESSAGES (to, from, message, timeStamp)"
            + " values (?, ?, ?, ?)";
    public static final String FETCH_MSGS_QRY = "SELECT * FROM MESSAGES WHERE to = '%s' AND from = '%s'";
    private DbConstants() {
        // prohibiting the initalizing outside
    }
}
