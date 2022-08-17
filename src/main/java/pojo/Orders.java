package pojo;

import lombok.Data;

import java.util.List;

@Data
public class Orders {
    private String _id;
    private List<String> ingredients;
    private String status;
    private String name;
    private int number;
    private String createdAt;
    private String updatedAt;
}
