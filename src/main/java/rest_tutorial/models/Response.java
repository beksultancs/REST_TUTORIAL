package rest_tutorial.models;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * @author Beksultan
 */
@Builder
@Getter @Setter
public class Response {
    private HttpStatus httpStatus;
    private String message;
}
