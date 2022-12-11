package kdt.prgrms.devreading.user.domain;

public class User {

    private long userId;
    private String email;
    private String password;
    private String userName;
    private String phoneNumber;
    private String authority; //jpa이후 enum으로 변경예정

    public User(long userId, String email, String password, String userName, String phoneNumber, String authority) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.authority = authority;
    }

    public User(String email, String password, String userName, String phoneNumber, String authority) {
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.authority = authority;
    }

    public void injectUserId(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAuthority() {
        return authority;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", authority='" + authority + '\'' +
                '}';
    }
}
