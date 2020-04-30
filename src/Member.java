public class Member {

    public String username; // accessible to other classes.
    private String password;
    private boolean isAdmin;
    private String fName;
    private String lName;
    private String phoneNumber;

    public Member(String fName, String lName, String username, String password, String phoneNumber, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.fName = fName;
        this.lName = lName;
        this.phoneNumber = phoneNumber;
        this.isAdmin = isAdmin;
    }
    public String getFName() {
        return this.fName;
    }
    public String getLName() {
        return this.lName;
    }

    public String getUsername() {
        return this.username;
    }
    public String getPassword() {
        return this.password;
    }
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    public boolean isAdmin() {
        return this.isAdmin;
    }
}
