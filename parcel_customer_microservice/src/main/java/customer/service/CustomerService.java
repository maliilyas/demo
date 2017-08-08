package customer.service;

import com.google.common.annotations.VisibleForTesting;
import customer.datalayer.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import customer.datalayer.CustomerDAO;

import javax.annotation.PostConstruct;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by aliilyas on 03/03/2017.
 */
@Service
public class CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    @Value("${parcel.service.baseurl}")
    private String parcelServiceBaseUrl;

    private final CustomerDAO customerDAO;
    private final RestTemplate restTemplate;

    public CustomerService(final CustomerDAO dao) {
        this.customerDAO = checkNotNull(dao);
        this.restTemplate = new RestTemplate();
    }

    @PostConstruct
    private final void prepareParcelBaseUrlForEndpoint() {
        parcelServiceBaseUrl = checkNotNull(parcelServiceBaseUrl) +"/clear";
        LOGGER.info("The api for parcel clearance:{}", parcelServiceBaseUrl);
    }

    public void checkinCustomerIntoDB(final Customer customer) {
        this.customerDAO.checkinCustomer(customer);
    }

    public void checkoutCustomerFromDB(final Customer customer) {
        final String url =  parcelServiceBaseUrl + "/" + customer.getRoomNumber() +
                "/" + customer.getPassportId();
        LOGGER.info("URL for parcel tracker service: {}", url);
        try {
            ResponseEntity<String> response = getRestTemplate().getForEntity(url
                    , String.class);
        } catch (Exception ex) {
            // do nothing
            LOGGER.info("Error occured while getting information from Parcel", ex);
        }
        this.customerDAO.checkoutCustomer(customer);
    }

    public boolean isCustomerResiding(final String roomNumber, final String passportID) {
        final Customer customer = this.customerDAO.isCustomerResiding(roomNumber, passportID);
        return customer.getCheckOutDate() == null ? true : false;
    }

    @VisibleForTesting
    RestTemplate getRestTemplate() {
        return this.restTemplate;
    }

}
