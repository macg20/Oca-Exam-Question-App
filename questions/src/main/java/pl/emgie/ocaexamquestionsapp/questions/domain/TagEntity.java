package pl.emgie.ocaexamquestionsapp.questions.domain;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "Tags")
public class TagEntity {

    @Id
    @GeneratedValue(generator = "tag_generator")
    @SequenceGenerator(name = "tag_generator", sequenceName = "tag_seq", allocationSize = 1)
    private BigInteger id;
    private String name;

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

}
