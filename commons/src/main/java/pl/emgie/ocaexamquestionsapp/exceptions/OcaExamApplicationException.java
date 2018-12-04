package pl.emgie.ocaexamquestionsapp.exceptions;

public class OcaExamApplicationException extends RuntimeException {

    public OcaExamApplicationException() {
    }

    public OcaExamApplicationException(String message) {
        super(message);
    }

    public OcaExamApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public OcaExamApplicationException(Throwable cause) {
        super(cause);
    }

    public OcaExamApplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
