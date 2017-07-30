package com.demo.task.chatup.datalayer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import static com.demo.task.chatup.datalayer.DbConstants.MSG_DATE_FORMAT;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * TODO: Add signup, addfriend, delete/block friend functionality laters.
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
        try {
            this.jdbcTemplate.update(DbConstants.INSERT_USER_QUERY, new Object[]{
                    "superman",
                    "ihatebatman"
            });
            this.jdbcTemplate.update(DbConstants.INSERT_USER_QUERY, new Object[]{
                    "wonderwoman",
                    "ihatebatmantoo"
            });
        } catch (DataAccessException ex) {
            logger.error("Unable to add predefined users, the app may not work as expected.");
        }

    }

    @Override
    public List<Message> fetchMessages(final Long to, final Long from) {
        try {
            final List<Message> messages = this.jdbcTemplate.query(
                    String.format(DbConstants.FETCH_MSGS_QRY, to, from),
                    (rs, rowNum) -> new Message(rs.getLong("to"), rs.getLong("from"), rs.getString("message"),
                            DbConstants.MSG_DATE_FORMAT.format(rs.getDate("timeStamp"))));

        } catch (DataAccessException ex) {
            logger.error("Unable to fetch chat history.", ex);
        }
        return null;

    }

    @Override
    public void insertMessage(final Long to, final Long from,
                              final String message) {
        try {
            final Date now = new Date();
            this.jdbcTemplate.update(DbConstants.INSERT_MSG_QRY, new Object[]{
                    to,
                    from,
                    message,
                    MSG_DATE_FORMAT.format(now)
            });

        } catch (DataAccessException ex) {
            logger.error("Error occured while inserting message.", ex);
        }
    }
}
