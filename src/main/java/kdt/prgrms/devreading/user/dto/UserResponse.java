package kdt.prgrms.devreading.user.dto;

import kdt.prgrms.devreading.user.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private long userId;
    private String email;
    private String password;
    private String userName;
    private String phoneNumber;
    private String authority;

    public UserResponse() {
    }

    public UserResponse(User user) {
        this.userId = user.getUserId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.userName = user.getUserName();
        this.phoneNumber = user.getPhoneNumber();
        this.authority = user.getAuthority();
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", authority='" + authority + '\'' +
                '}';
    }
}
