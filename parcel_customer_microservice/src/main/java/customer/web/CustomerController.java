package customer.web;

import com.google.common.base.Preconditions;
import customer.datalayer.Customer;
import customer.service.CustomerService;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by aliilyas on 03/03/2017.
 */
@RestController
@RequestMapping("/customer")
@Api(basePath = "/customer", value = "Customer", description = "Api for Customer End point.", produces = "application/json")
public class CustomerController {

    private final CustomerService aService;

    public CustomerController(final CustomerService aService) {
        this.aService = Preconditions.checkNotNull(aService);
    }

    @RequestMapping(value = "/checkin", method = RequestMethod.POST,
            produces = {"application/json"}, consumes = {"application/json"})
    @ApiOperation(value = "Check in a Customer", notes = "Check in a Customer")
    public ResponseEntity checkinCustomer(@RequestBody final Customer customer) {
        this.aService.checkinCustomerIntoDB(customer);
        return new ResponseEntity("Service Created", HttpStatus.CREATED);
    }


    @RequestMapping(value = "/checkout", method = RequestMethod.POST,
            produces = {"application/json"}, consumes = {"application/json"})
    @ApiOperation(value = "Check out a Customer",
            notes = "Check out a Customer and also clear all outstanding parcels.")
    public ResponseEntity checkoutCustomer(@RequestBody final Customer customer) {
        this.aService.checkoutCustomerFromDB(customer);
        return new ResponseEntity("Something more to be done,,, clearing DB maybe???", HttpStatus.OK);
    }

    @RequestMapping(value = "/customercheckedin/{roomNumber}/{passportId}", method = RequestMethod.GET)
    @ApiOperation(value = "Check customer checkin status",
            notes = "Check where customer resides in, endpoint for parcel service usage")
    public ResponseEntity isCustomerCheckedIn(@PathVariable final String roomNumber,
                                              @PathVariable final String passportId) {
        final boolean isResiding = this.aService.isCustomerResiding(roomNumber, passportId);
        return isResiding == Boolean.TRUE ? new ResponseEntity("Customer is Residing.",
                HttpStatus.OK) : new ResponseEntity("Customer has checked Out", HttpStatus.NOT_FOUND);
    }

}
