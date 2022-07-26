package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Order {
    private String name;
    private boolean success;
    private NewOrder order;



}
