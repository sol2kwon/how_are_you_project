package how_are_you_project.how_are_you.itemStore.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //한 테이블에 다 때려박음
@DiscriminatorColumn(name = "dtype") //싱글 테이블이라 구분하겠다.
public abstract class ItemStore {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "price")
    private int price;

    @Column(name = "stock_quantity")
    private int stockQuantity;


}
