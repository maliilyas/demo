package customer.exception;

/**
 * Created by aliilyas on 03/03/2017.
 */
public class CustomerCheckInException extends RuntimeException {

    public CustomerCheckInException(final String msg, final Throwable cause) {
        super(msg, cause);
    }
}
