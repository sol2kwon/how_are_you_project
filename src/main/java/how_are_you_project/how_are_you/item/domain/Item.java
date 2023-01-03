package how_are_you_project.how_are_you.item.domain;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "item")
@Getter
public class Item {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "member_id")
    private long id;

    @NotEmpty
    @Column(nullable = false, length = 20)
    private String itemName;

    @NotEmpty
    @Column
    private int price;

    @NotEmpty
    @Column
    private int stockQuantity;



}
