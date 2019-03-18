package pl.emgie.ocaexamquestionsapp.questions.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "Tags")
@Getter
@Setter
public class TagEntity {

    @Id
    @GeneratedValue(generator = "tag_generator")
    @SequenceGenerator(name = "tag_generator", sequenceName = "tag_seq", allocationSize = 1)
    private BigInteger id;
    private String name;

}
