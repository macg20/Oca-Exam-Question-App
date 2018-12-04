package pl.emgie.ocaexamquestionsapp.questions;

import pl.emgie.ocaexamquestionsapp.questions.dto.AttachmentDto;

import java.nio.file.Path;

interface AttachmentService extends BaseService{

    Path insert(String name, byte[] content);

    AttachmentDto read(String path);

    void delete(String path);

}
