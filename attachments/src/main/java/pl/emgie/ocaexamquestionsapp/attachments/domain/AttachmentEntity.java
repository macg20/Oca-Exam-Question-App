package pl.emgie.ocaexamquestionsapp.attachments.domain;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "attachments")
class AttachmentEntity {

    @Id
    @GeneratedValue(generator = "attachment_generator")
    @SequenceGenerator(name = "attachment_generator", sequenceName = "attachment_seq", allocationSize = 1)
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
