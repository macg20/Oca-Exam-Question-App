package pl.emgie.ocaexamquestionsapp.attachments.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "attachments")
@Getter
@Setter
class AttachmentEntity {

    @Id
    @GeneratedValue(generator = "attachment_generator")
    @SequenceGenerator(name = "attachment_generator", sequenceName = "attachment_seq", allocationSize = 1)
    private BigInteger id;
    private String name;
    private String path;
}
