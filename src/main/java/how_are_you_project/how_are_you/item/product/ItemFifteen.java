package how_are_you_project.how_are_you.item.product;

import how_are_you_project.how_are_you.item.domain.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
@Getter
@Setter
public class ItemFifteen extends Item {
    private String itemName;
    private String expiryDate;
}
