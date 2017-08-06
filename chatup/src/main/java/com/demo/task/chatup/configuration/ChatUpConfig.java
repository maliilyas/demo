package com.demo.task.chatup.configuration;

import com.demo.task.chatup.datalayer.SimpleUserDao;
import com.demo.task.chatup.datalayer.UserDao;
import com.demo.task.chatup.service.UserService;
import com.demo.task.chatup.web.ChatController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class ChatUpConfig {

    @Bean
    public UserDao userDao() {
        return new SimpleUserDao(jdbcTemplate());
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate();
    }
}
