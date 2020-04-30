public class Member {

    public String username; // accessible to other classes.
    private String password;
    private boolean isAdmin;
    private String fName;
    private String lName;

    public Member(String fName, String lName, String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.fName = fName;
        this.lName = lName;
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
    public boolean isAdmin() {
        return this.isAdmin;
    }
}
