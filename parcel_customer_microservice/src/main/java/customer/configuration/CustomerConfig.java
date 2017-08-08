package customer.configuration;

import customer.datalayer.CustomerDAO;
import customer.service.CustomerService;
import customer.web.CustomerController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by aliilyas on 03/03/2017.
 */
@Configuration
public class CustomerConfig {

    @Bean
    public CustomerController customerController() {
        return new CustomerController(customerService());
    }

    @Bean
    public CustomerService customerService() {
        return new CustomerService(customerDAO());
    }

    @Bean
    public CustomerDAO customerDAO() {
        return new CustomerDAO(jdbcTemplate());
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate();
    }


}
