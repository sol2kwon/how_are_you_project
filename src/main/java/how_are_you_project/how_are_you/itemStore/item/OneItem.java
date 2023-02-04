package how_are_you_project.how_are_you.itemStore.item;

import how_are_you_project.how_are_you.itemStore.domain.ItemStore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A") //싱글 테이블이라 구분 타입
@Getter
@Setter
public class OneItem extends ItemStore {
    private int count;

}
