package pl.emgie.ocaexamquestionsapp.attachments.domain;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;

public interface AttachmentService {

    default Logger getLogger() {
        return LoggerFactory.getLogger(getClass());
    }

    Path insert(String name, byte[] content);

    AttachmentDto read(String path);

    void delete(String path);

}
