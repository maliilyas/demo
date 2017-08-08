package customer.web;

import com.fasterxml.jackson.core.JsonParseException;
import customer.exception.CustomerCheckInException;
import org.h2.jdbc.JdbcSQLException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by aliilyas on 04/03/2017.
 */
@RestControllerAdvice
public class ExceptionAddviceController {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity exceptionHandler(final RuntimeException cause) {

        final ResponseEntity response;
        if (JdbcSQLException.class.getSimpleName().equalsIgnoreCase(cause.getClass().getSimpleName())) {
            response = new ResponseEntity("Database Error:" + cause.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } else if (JsonParseException.class.getSimpleName().equalsIgnoreCase(cause.getClass().getSimpleName())) {
            response = new ResponseEntity("Json Parsing Exception:" + cause.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } else if (CustomerCheckInException.class.getSimpleName().equalsIgnoreCase(cause.getClass().getSimpleName())) {
            response = new ResponseEntity("Customer Can not be Checked In due to reason:" + cause.getCause(), HttpStatus.OK);
        } else if(EmptyResultDataAccessException.class.getSimpleName().equalsIgnoreCase(cause.getClass().getSimpleName())) {
            response = new ResponseEntity("Customer Not Found.", HttpStatus.NOT_FOUND);
        } else {
            response = new ResponseEntity("Default Internal Exception", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }
}
