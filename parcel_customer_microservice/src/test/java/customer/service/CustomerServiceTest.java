package customer.service;

import customer.datalayer.Customer;
import customer.datalayer.CustomerDAO;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.Test;

import java.util.Date;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

/**
 * Created by aliilyas on 05/03/2017.
 */
// Only simple scenarios are tested, since the methods are void, no assertion could be made.
@SpringBootTest
public class CustomerServiceTest {

    private CustomerDAO mockCustomerDao;

    // Simple test for insertion
    @Test
    public void testCheckinCustomerIntoDBOk() {
        mockCustomerDao = mock(CustomerDAO.class);
        final CustomerService customerService = new CustomerService(mockCustomerDao);
        final Customer customer = mock(Customer.class);
        when(customer.getFirstName()).thenReturn("Test Customer");
        when(customer.getLastName()).thenReturn("Test Customer");
        when(customer.getPassportId()).thenReturn("pass");
        when(customer.getCheckInDate()).thenReturn(new Date());
        customerService.checkinCustomerIntoDB(customer);

    }

    //Test for Checkout
    @Test
    public void testCheckoutCustomerFromDBOk() throws Exception {
        mockCustomerDao = mock(CustomerDAO.class);
        final RestTemplate restTemplate = mock(RestTemplate.class);
        final CustomerService customerService = new CustomerService(mockCustomerDao);
        final Customer customer = mock(Customer.class);
        when(customer.getFirstName()).thenReturn("Test Customer");
        when(customer.getLastName()).thenReturn("Test Customer");
        when(customer.getPassportId()).thenReturn("pass");
        when(customer.getCheckInDate()).thenReturn(new Date());
        customerService.checkoutCustomerFromDB(customer);
    }

}