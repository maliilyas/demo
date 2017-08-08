import com.google.common.base.Preconditions;
import customer.application.CustomerApp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import parcel.application.ParcelApp;
import parcel.datalayer.ParcelDAO;

/**
 * Created by aliilyas on 03/03/2017.
 */
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        if(args.length <= 0) {
            commands();
        } else {
            final String serviceToRun = Preconditions.checkNotNull(args[0], "No Service Specified !!!");

            if (serviceToRun.equalsIgnoreCase("customer-service") || serviceToRun.equals("1")) {
                CustomerApp.main(args);
            } else if (serviceToRun.equalsIgnoreCase("parcel-service") || serviceToRun.equals("2")) {
                ParcelApp.main(args);
            } else {
                logger.info("None of Known Service given: {} ", serviceToRun);
                commands();
            }
        }
    }

    private final static void commands() {
        logger.info("To run parcel tracker please use the following command : $ java -jar parceltracker.jar <Service Name> [option --spring arguments]" +
                "\n where arguments are:\n1. customer-service or 1\n" +
                "2. parcel-service or 2");
    }
}
