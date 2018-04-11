package controllers.advice;

import exception.DataNotFoundException;
import exception.error.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ScriptControllerAdvice {

    @ExceptionHandler(DataNotFoundException.class)
    public ErrorMessage dataNotFoundHandler(){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setErrorCode(HttpStatus.NO_CONTENT.value());
        errorMessage.setMessage("No data found");
        return errorMessage;
    }
}
