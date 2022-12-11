package kdt.prgrms.devreading.user.dto;

import kdt.prgrms.devreading.user.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    private String email;
    private String password;
    private String userName;
    private String phoneNumber;
    private String authority; //jpa후 enum으로 변경예정.

    public UserRequest() {
    }

    public UserRequest(String email, String password, String userName, String phoneNumber, String authority) {
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.authority = authority;
    }

    public User createUser() {
        return new User(this.email, this.password, this.userName, this.phoneNumber, this.authority);
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", authority='" + authority + '\'' +
                '}';
    }
}
