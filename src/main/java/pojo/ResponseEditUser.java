package pojo;

import lombok.Data;

@Data
public class ResponseEditUser {
    private ResponseUserInfo user;
    private boolean success;
}
