package rest_tutorial.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import rest_tutorial.models.Response;

import static org.springframework.http.HttpStatus.*;

/**
 * @author Beksultan
 */
//@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

//    @ExceptionHandler(BadRequestException.class)
//    @ResponseStatus(BAD_REQUEST)
//    public Response handleBadRequestException(BadRequestException badRequestException) {
//        return Response.builder()
//                .httpStatus(BAD_REQUEST)
//                .message(badRequestException.getMessage())
//                .build();
//    }
}
