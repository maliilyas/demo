package customer.datalayer;

import customer.exception.CustomerCheckInException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import parcel.datalayer.ParcelDAO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by aliilyas on 03/03/2017.
 */
@Component
public class CustomerDAO {

    private static final Logger logger = LoggerFactory.getLogger(ParcelDAO.class);
    private final JdbcTemplate jdbcTemplate;
    private static final String CHECKIN_QUERY = "INSERT INTO CUSTOMER " +
            "(firstName, lastName, roomNumber, passportId, checkinDate) " +
            "values (?, ?, ?, ?, ?) ";
    private static final String CHECKOUT_QUERY =
            "UPDATE CUSTOMER SET checkoutDate = ? " +
                    "WHERE roomNumber = ? AND passportId = ?";

    private static final String IS_CUSTOMER_RESIDING_QUERY = "SELECT CHECKOUTDATE FROM CUSTOMER WHERE" +
            " roomNumber = ? AND passportId = ?";

    final DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");


    public CustomerDAO(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = checkNotNull(jdbcTemplate);
        createCustomerTable();
    }

    private final void createCustomerTable() {
        try {
            this.jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS CUSTOMER" +
                    "(firstName VARCHAR(128) NOT NULL, " +
                    "lastName VARCHAR(128) NOT NULL, " +
                    "passportId VARCHAR(128) NOT NULL, " +
                    "roomNumber VARCHAR(128) NOT NULL, " +
                    "checkinDate DATE NOT NULL," +
                    "checkoutDate DATE," +
                    "PRIMARY KEY(passportId, roomNumber, checkinDate))");
        } catch (DataAccessException ex) {
            logger.warn("The Parcel table can not be created. Service won't work as expected.", ex);
        }
    }

    public void checkinCustomer(final Customer customer) {
        checkNotNull(customer, "Customer's object can not be null");
        try {
            jdbcTemplate.update(CHECKIN_QUERY, new Object[]{
                    customer.getFirstName(),
                    customer.getLastName(),
                    customer.getRoomNumber(),
                    customer.getPassportId(),
                    customer.getCheckInDate()});
        } catch (DataAccessException ex) {
            throw new CustomerCheckInException("Can not Insert into Customer", ex);
        }
    }

    public void checkoutCustomer(final Customer customer) {
        final Date date = new Date();
        try {
            jdbcTemplate.update(CHECKOUT_QUERY,
                    dateFormat.format(date),
                    customer.getRoomNumber(),
                    customer.getPassportId());
        } catch (DataAccessException ex) {
            throw new CustomerCheckInException("Can not Check Out the Customer with ID" + customer.getPassportId(), ex);
        }
    }

    public Customer isCustomerResiding(final String roomNumber, final String passportID) {
        return (Customer) jdbcTemplate.queryForObject(
                IS_CUSTOMER_RESIDING_QUERY, new Object[]{roomNumber, passportID},
                new BeanPropertyRowMapper(Customer.class));
    }

}
