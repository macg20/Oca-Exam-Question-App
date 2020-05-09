package pl.emgie.ocaexamquestionsapp.questions.infrastructure.errors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.emgie.ocaexamquestionsapp.questions.infrastructure.exceptions.InvalidParameterException;

@ControllerAdvice
class QuestionControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = InvalidParameterException.class)
    protected ResponseEntity<Object> handleInvalidParametr(InvalidParameterException ex, WebRequest request) {
        return handleException(ex, request, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> handleException(Exception ex, WebRequest request, HttpStatus status) {
        String exceptionMessage = ex.getMessage();
        return handleExceptionInternal(ex, exceptionMessage, new HttpHeaders(), status, request);
    }

}
