package pl.emgie.ocaexamquestionsapp.questions.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "questions")
@Getter
@Setter
class QuestionEntity {

    @Id
    @GeneratedValue(generator = "question_generator")
    @SequenceGenerator(name = "question_generator", sequenceName = "question_seq", allocationSize = 1)
    private BigInteger id;

    private String question;

    @ManyToMany
    @JoinTable(name = "questions_tags",
            joinColumns = {@JoinColumn(name = "question_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")}
    )
    private Set<TagEntity> tags;

    private String answerId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "question_id")
    private List<AnswerEntity> answers;

    @ElementCollection
    @JoinTable(name ="question_attachments")
    private List<QuestionAttachmentId> questionAttachments;

    private String description;
}
