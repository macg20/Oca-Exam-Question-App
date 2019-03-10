package pl.emgie.ocaexamquestionsapp.attachments.domain;

import java.math.BigInteger;

class AttachmentEntity {

    private BigInteger id;
    private String name;
    private String path;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }
}
