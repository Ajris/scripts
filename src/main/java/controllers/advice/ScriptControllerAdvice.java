package controllers.advice;

import exception.DataNotFoundException;
import exception.error.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ScriptControllerAdvice {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ErrorMessage> dataNotFoundHandler(Exception ex){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setErrorCode(HttpStatus.NO_CONTENT.value());
        errorMessage.setMessage("No data found");
        return new ResponseEntity<>(errorMessage, HttpStatus.OK);
    }
}
