import java.util.HashMap;

public class Member {
    public String username; // accessible to other classes.
    private String password;
    private boolean isAdmin;
    private String fName;
    private String lName;
    private String phoneNumber;
    private HashMap<String, Integer> onLoan;    // keeps track of movies loaned value = loanedCopies and key = movie name

    public Member(String fName, String lName, String username, String password, String phoneNumber, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.fName = fName;
        this.lName = lName;
        this.phoneNumber = phoneNumber;
        this.isAdmin = isAdmin;
        this.onLoan = new HashMap<String, Integer>();
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

    public void setOnLoan(int copiesLoaned, String movieName) {

        Integer currentlyBorrowedCopies = this.onLoan.get(movieName); // #TODO doesn't update copies if statement.
        if(currentlyBorrowedCopies != null && currentlyBorrowedCopies > 1) { // If a movie had been borrowed already and more copies are being borrowed find the hashmapping and update the total # of copies borrowed
            Integer updatedNumberOfCopies = currentlyBorrowedCopies + copiesLoaned;
            this.onLoan.put(movieName, updatedNumberOfCopies);
        }
        // create a new hashmap with the movie and # copies borrowed
        this.onLoan.put(movieName, copiesLoaned);
    }

    public HashMap<String, Integer> getAllLoans() {
        return this.onLoan;
    }
}
