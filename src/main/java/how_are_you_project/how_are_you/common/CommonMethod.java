package how_are_you_project.how_are_you.common;

import lombok.Data;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@Data
@Lazy

public class CommonMethod <K,V>{
    private final HashMap<K,V> map;
    public CommonMethod() {
        this.map = new HashMap<>();
    }
    public V get(String id){return map.get(id);}

}
