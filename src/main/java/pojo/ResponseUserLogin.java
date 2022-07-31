package pojo;

import lombok.Data;

@Data
public class ResponseUserLogin {
    private String accessToken;
    private String refreshToken;
    private boolean success;
    private ResponseUserInfo user;
}
