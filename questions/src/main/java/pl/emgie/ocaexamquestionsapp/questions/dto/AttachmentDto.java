package pl.emgie.ocaexamquestionsapp.questions.dto;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;

public class AttachmentDto {

    BigInteger id;

    @NotNull
    private String name;
    @NotNull
    private byte[] content;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
