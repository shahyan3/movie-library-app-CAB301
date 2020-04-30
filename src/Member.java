public class Member {

    public String username; // accessible to other classes.
    private String fName;
    private String lName;
    private String password;

    public Member(String fName, String lName, String username, String password) {
        this.fName = fName;
        this.lName = lName;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }
    public String getPassword() {
        return this.password;
    }
    public String getfName() {
        return this.fName;
    }
    public String getlName() {
        return this.lName;
    }
}
