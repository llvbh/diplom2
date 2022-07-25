package pojo;

import lombok.Data;

@Data
public class Orders {
    private Ingredients ingredients;
    private int id;
    private String status;
    private int number;
    private String createdAt;
    private String updatedAt;


}
