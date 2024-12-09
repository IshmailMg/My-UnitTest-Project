package za.co.test_project.unit_integration.student.exception;

import jakarta.validation.constraints.Email;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException  {
    public BadRequestException(@Email String msg) {
        super(msg);
    }
}
