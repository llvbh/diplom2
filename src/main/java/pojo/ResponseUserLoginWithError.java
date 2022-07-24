package pojo;

import lombok.Data;

@Data
public class ResponseUserLoginWithError {
    private String message;
    private boolean success;

}
