package pl.emgie.ocaexamquestionsapp.attachments.domain;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.nio.file.Path;

public interface AttachmentService {

    default Logger getLogger() {
        return LoggerFactory.getLogger(getClass());
    }

    BigInteger insert(String name, byte[] content);

    AttachmentDto read(BigInteger id);

    void delete(BigInteger id);

}
