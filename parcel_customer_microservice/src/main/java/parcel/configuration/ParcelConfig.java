package parcel.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import parcel.datalayer.ParcelDAO;
import parcel.service.ParcelService;
import parcel.web.ParcelController;

/**
 * Created by aliilyas on 05/03/2017.
 */
@Configuration
public class ParcelConfig {

    @Bean
    public ParcelController parcelController() {
        return new ParcelController(parcelService());
    }

    @Bean
    public ParcelService parcelService() {
        return new ParcelService(parcalDAO());
    }

    @Bean
    public ParcelDAO parcalDAO() {
        return new ParcelDAO(jdbcTemplate());
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate();
    }
}
