package how_are_you_project.how_are_you.itemStore.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;

@Entity
@Getter
@Setter
//1일 , 15일 , 30일 , 1년
public class ItemStore {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "price")
    private int price;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_Date")
    private LocalDate endDate;






}
