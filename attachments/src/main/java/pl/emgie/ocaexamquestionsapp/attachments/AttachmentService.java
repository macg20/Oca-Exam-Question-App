package pl.emgie.ocaexamquestionsapp.attachments;


import java.nio.file.Path;

public interface AttachmentService extends BaseService {

    Path insert(String name, byte[] content);

    AttachmentDto read(String path);

    void delete(String path);

}
