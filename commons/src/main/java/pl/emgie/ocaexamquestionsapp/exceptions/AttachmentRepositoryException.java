package pl.emgie.ocaexamquestionsapp.exceptions;

public class AttachmentRepositoryException extends OcaExamApplicationException{

    public AttachmentRepositoryException() {
    }

    public AttachmentRepositoryException(String message) {
        super(message);
    }

    public AttachmentRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public AttachmentRepositoryException(Throwable cause) {
        super(cause);
    }

    public AttachmentRepositoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
