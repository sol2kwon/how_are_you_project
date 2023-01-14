package how_are_you_project.how_are_you.question.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "question")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long questionId;

    @Column(name = "content")
    private String content;


}
