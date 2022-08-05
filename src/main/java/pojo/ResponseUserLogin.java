package pojo;

import lombok.Data;

@Data
public class ResponseUserLogin {
    private String accessToken;
    private String refreshToken;
    private boolean success;
    private ResponseUserInfo user;

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken.substring("Bearer".length() + 1);
    }
}
