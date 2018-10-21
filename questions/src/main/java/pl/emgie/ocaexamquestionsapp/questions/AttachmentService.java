package pl.emgie.ocaexamquestionsapp.questions;

import pl.emgie.ocaexamquestionsapp.questions.dto.AttachmentDto;

import java.nio.file.Path;

interface AttachmentService extends BaseService{

    Path insert(byte[] content);

    AttachmentDto read(String path);

}
