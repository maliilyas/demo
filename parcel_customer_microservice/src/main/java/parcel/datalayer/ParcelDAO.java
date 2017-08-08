package parcel.datalayer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import parcel.exception.ParcelAcceptanceException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by aliilyas on 03/03/2017.
 */
@Component
public class ParcelDAO {

    private static final Logger logger = LoggerFactory.getLogger(ParcelDAO.class);

    private final JdbcTemplate jdbcTemplate;
    private static  final  String ACCEPT_PARCEL_QUERY = "INSERT INTO PARCEL " +
            "(firstName, lastName, passportId, additionalAddress, acceptionDate, cleared)" +
            "values (?, ?, ?, ?, ?, ?)";
    private static final String CLEAR_OUSTANDING_PARCEL_QUERY = "UPDATE PARCEL " +
            "(cleared, clearanceDate) value (?, ?) WHERE passportId = ? AND additionalAddress = ?";

    final DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");

    public ParcelDAO(final JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = checkNotNull(jdbcTemplate);
        createParcelTable();

    }

    private final void createParcelTable() {
        try {
            this.jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS PARCEL" +
                    "(id BIGINT AUTO_INCREMENT, " +
                    "firstName VARCHAR(128) NOT NULL, " +
                    "lastName VARCHAR(128) NOT NULL, " +
                    "passportId VARCHAR(128), " +
                    "additionalAddress VARCHAR(128) NOT NULL, " +
                    "acceptionDate DATE NOT NULL," +
                    "cleared BOOLEAN NOT NULL)");
        } catch (DataAccessException ex) {
            logger.warn("The Parcel table can not be created. Service won't work as expected.", ex);
        }
    }

    public void acceptParcel(final Parcel parcel) {
        final Date date = new Date();
        try {
            jdbcTemplate.update(ACCEPT_PARCEL_QUERY, new Object[]{
                    parcel.getFirstName(),
                    parcel.getLastName(),
                    parcel.getPassportId(),
                    parcel.getAdditionalAddress(),
                    dateFormat.format(date),
                    "false"
            });
        } catch (DataAccessException ex) {
            throw new ParcelAcceptanceException("Can not Insert into Customer", ex);
        }
    }

    public void clearOutstandingParcels(final String roomNumber,
                                        final String passportId) {

        jdbcTemplate.update(CLEAR_OUSTANDING_PARCEL_QUERY, new Object[]{
                "true",
                System.currentTimeMillis(),
                passportId,
                roomNumber});

    }
}
