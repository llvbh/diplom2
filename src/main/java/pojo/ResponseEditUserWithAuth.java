package pojo;

import lombok.Data;

@Data
public class ResponseEditUserWithAuth {
    private ResponseUserInfo user;
    private boolean success;
}
