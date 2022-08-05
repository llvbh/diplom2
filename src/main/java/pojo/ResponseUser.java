package pojo;

import lombok.Data;

@Data
public class ResponseUser {

    private String accessToken;
    private String refreshToken;
    private ResponseUserInfo user;
    private boolean success;

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken.substring("Bearer".length() + 1);
    }
}
