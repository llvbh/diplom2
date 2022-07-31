package pojo;

import lombok.Data;

@Data
public class Order {
    private String name;
    private boolean success;
    private NewOrder order;
}
