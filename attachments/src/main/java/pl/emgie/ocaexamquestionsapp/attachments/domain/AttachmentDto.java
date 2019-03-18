package pl.emgie.ocaexamquestionsapp.attachments.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AttachmentDto {

    @NotNull
    private String name;
    @NotNull
    private byte[] content;
}
