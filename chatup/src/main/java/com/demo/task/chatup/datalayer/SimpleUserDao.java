package com.demo.task.chatup.datalayer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

import static com.demo.task.chatup.datalayer.DbConstants.MSG_DATE_FORMAT;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Dao for User for adding messages etc.
 */
@Component
public class SimpleUserDao implements UserDao {

    private static final Logger logger = LoggerFactory.getLogger(SimpleUserDao.class);
    private final JdbcTemplate jdbcTemplate;

    public SimpleUserDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = checkNotNull(jdbcTemplate);
        createDbSchema();
        hookUpTwoUsers();
    }

    /**
     * Creating the schema of the Database.
     */
    private void createDbSchema() {
        try {
            this.jdbcTemplate.execute(DbConstants.CREATE_USER_TABLE_QRY);
            this.jdbcTemplate.execute(DbConstants.CREATE_MESSAGE_TABLE_QRY);
        } catch (DataAccessException ex) {
            logger.error("Unable to initialize the db schema, the app won't work as expected.", ex);
        }
    }

    /**
     * Adding predefined two users for Demo.
     */
    private void hookUpTwoUsers() {

        this.jdbcTemplate.update(DbConstants.INSERT_USER_QUERY, new Object[]{
                "superman",
                "ihatebatman"
        });
        this.jdbcTemplate.update(DbConstants.INSERT_USER_QUERY, new Object[]{
                "wonderwoman",
                "ihatebatmantoo"
        });


    }

    @Override
    public List<Message> fetchMessages(final String to) {
        checkArgument(!StringUtils.isEmpty(to), "Sender can not be Empty");
        return this.jdbcTemplate.query(
                String.format(DbConstants.FETCH_MSGS_QRY, to),
                (rs, rowNum) -> new Message(rs.getString("to"), rs.getString("from"), rs.getString("message"),
                        DbConstants.MSG_DATE_FORMAT.format(rs.getDate("timeStamp"))));
    }

    @Override
    public void insertMessage(final String to, final String from,
                              final String message) {
        checkArgument(!StringUtils.isEmpty(to), "Sender can not be Empty");
        checkArgument(!StringUtils.isEmpty(from), "Receiver can not be Empty");
        checkArgument(!StringUtils.isEmpty(message), "Message can not be Empty");

        final Date now = new Date();
        this.jdbcTemplate.update(DbConstants.INSERT_MSG_QRY, new Object[]{
                to,
                from,
                message,
                MSG_DATE_FORMAT.format(now)
        });
    }
}
