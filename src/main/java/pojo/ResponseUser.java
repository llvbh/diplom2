package pojo;

import lombok.Data;

@Data
public class ResponseUser {

    private String accessToken;
    private String refreshToken;
    private ResponseUserInfo user;
    private boolean success;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public ResponseUserInfo getUser() {
        return user;
    }

    public void setUser(ResponseUserInfo user) {
        this.user = user;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
