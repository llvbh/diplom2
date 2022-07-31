package pojo;

import lombok.Data;

@Data
public class ResponseEditUserWithError {
    private String message;
    private boolean success;
}
