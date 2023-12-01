package use_case.logout;

public class LogoutData {

    final private String username;
    final private String password;

    public LogoutData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

}

