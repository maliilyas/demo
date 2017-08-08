package parcel.exception;

import parcel.datalayer.Parcel;

/**
 * Created by aliilyas on 03/03/2017.
 */
public class ParcelAcceptanceException extends RuntimeException {

    public ParcelAcceptanceException(final String msg, final Throwable cause) {
        super(msg, cause);
    }

    public ParcelAcceptanceException(final String msg) {
        super(msg);
    }
}
