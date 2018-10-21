package pl.emgie.ocaexamquestionsapp.questions.dto;

public class AttachmentDto {

    private String name;
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
