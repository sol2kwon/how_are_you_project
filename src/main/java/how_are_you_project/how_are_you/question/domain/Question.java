package how_are_you_project.how_are_you.question.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "question")
@Getter
@NoArgsConstructor
@ToString
public class Question {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long questionId;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    public Question(Long questionId) {
        this.questionId = questionId;
    }
}
