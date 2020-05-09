package pl.emgie.ocaexamquestionsapp.attachments.infrastructures.errors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.emgie.ocaexamquestionsapp.attachments.domain.AttachmentRepositoryException;

@ControllerAdvice
public class AttachmentControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = AttachmentRepositoryException.class)
    protected ResponseEntity<Object> handleAttachmentRepositoryExceptions(AttachmentRepositoryException ex, WebRequest request){
        return handleException(ex, request, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Object> handleException(Exception ex, WebRequest request, HttpStatus status) {
        String exceptionMessage = ex.getMessage();
        return handleExceptionInternal(ex, exceptionMessage, new HttpHeaders(), status, request);
    }
}
