package pojo;

import lombok.Data;

import java.util.List;

@Data
public class ResponseUserWithAuthOrders {
    private boolean success;
    private List<Orders> orders;
    private int total;
    private int totalToday;
}
