package parcel.service;

import com.google.common.annotations.VisibleForTesting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import parcel.datalayer.Parcel;
import parcel.datalayer.ParcelDAO;
import parcel.exception.ParcelAcceptanceException;

import javax.annotation.PostConstruct;

import static com.google.common.base.Preconditions.checkNotNull;

@Service
public class ParcelService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParcelService.class);

    @Value("${customer.service.baseurl}")
    private String customerServiceCheckInCustomerUrl;

    private final ParcelDAO parcelDAO;
    private final RestTemplate restTemplate;

    public ParcelService(final ParcelDAO parcelDAO) {
        this.parcelDAO = checkNotNull(parcelDAO);
        restTemplate = new RestTemplate();
    }

    @PostConstruct
    private final void prepareCustomerBaseUrlForEndpoint() {
        customerServiceCheckInCustomerUrl = checkNotNull(customerServiceCheckInCustomerUrl) + "/customercheckedin";
        LOGGER.info("The url for Checking customer is residing: {} ", customerServiceCheckInCustomerUrl);
    }


    public ResponseEntity acceptParcel(final Parcel parcel) {
        final String url =
                customerServiceCheckInCustomerUrl + "/" + parcel.getAdditionalAddress() +
                        "/" + parcel.getPassportId();
        LOGGER.info("The url for customer check in :{}", url);
        try {
            ResponseEntity<String> response = getRestTemplate().getForEntity(url, String.class);
            if (response.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                throw new ParcelAcceptanceException("Person residing at " +
                        parcel.getAdditionalAddress() + " not found.");
            } else {
                this.parcelDAO.acceptParcel(parcel);
                return new ResponseEntity("Parcel Accepted for Person residing: "+
                        parcel.getAdditionalAddress(), HttpStatus.OK);
            }
        } catch (Exception ex){
            return new ResponseEntity("Can not Accept Parcel, Customer not residing.", HttpStatus.OK);
        }
    }

    public ResponseEntity clearAllOutstandingParcels(final String roomNumber,
                                                     final String passportId) {
        try {
            this.parcelDAO.clearOutstandingParcels(roomNumber, passportId);
        } catch (DataAccessException ex) {
            LOGGER.info("Error occured while clearing the Parcels", ex);
        }
        return new ResponseEntity("Cleared parcels if existed", HttpStatus.OK);
    }

    @VisibleForTesting
    RestTemplate getRestTemplate() {
        return this.restTemplate;
    }

}
