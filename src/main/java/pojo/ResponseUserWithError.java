package pojo;

import lombok.Data;

@Data
public class ResponseUserWithError {
    private boolean success;
    private String message;
}
