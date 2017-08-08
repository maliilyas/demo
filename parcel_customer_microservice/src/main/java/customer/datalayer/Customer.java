package customer.datalayer;

import java.util.Date;

/**
 * Created by aliilyas on 03/03/2017.
 */
public class Customer {

    private  String firstName;
    private  String lastName;
    private  String passportId;
    private  String roomNumber;
    private  Date checkOutDate;
    private  Date checkInDate;

    public Customer() {
        //For deserialization for the endpoint
    }
    public Customer(final String firstName,
                    final String lastName,
                    final String passportId,
                    final String roomNumber,
                    final Date checkInDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportId = passportId;
        this.roomNumber = roomNumber;
        this.checkInDate = checkInDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassportId() {
        return passportId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(final Date date) {
        this.checkOutDate = date;
    }

}
