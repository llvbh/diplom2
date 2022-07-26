package pojo;

import lombok.Data;

import java.util.List;

@Data
public class Ingredients {
    private List<String> ingredients;

    public Ingredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}
