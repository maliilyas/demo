package parcel.datalayer;

/**
 * Created by aliilyas on 03/03/2017.
 */
public class Parcel {

    private  String firstName;
    private  String lastName;
    private  String passportId;
    private  String additionalAddress;


    public Parcel() {
        // default constructor for serilization
    }
    public Parcel(final String firstName,
                  final String lastName,
                  final String passportId,
                  final String additionalAddress) {
        this.firstName  = firstName;
        this.lastName   = lastName;
        this.passportId = passportId;
        this.additionalAddress = additionalAddress;
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

    public String getAdditionalAddress() {
        return additionalAddress;
    }



}
