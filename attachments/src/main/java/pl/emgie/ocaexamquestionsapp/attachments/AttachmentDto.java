package pl.emgie.ocaexamquestionsapp.attachments;

import javax.validation.constraints.NotNull;

public class AttachmentDto {

    @NotNull
    private String name;
    @NotNull
    private byte[] content;

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
