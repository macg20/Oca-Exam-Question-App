package pl.emgie.ocaexamquestionsapp.questions.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Getter
@Setter
public class AttachmentDto {

    private BigInteger id;
    @NotNull
    private String name;
    @NotNull
    private byte[] content;
}
