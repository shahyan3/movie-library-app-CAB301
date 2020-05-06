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

    // Borrow a movie - adds a hashmap data structure with movie name as key and # borrowed as value
    public void setOnLoan(int copiesLoaned, String movieName) {
        Integer currentlyBorrowedCopies = this.onLoan.get(movieName);
        if(currentlyBorrowedCopies != null && currentlyBorrowedCopies > 0) { // If a movie had been borrowed already and more copies are being borrowed find the hashmapping and update the total # of copies borrowed
            Integer updatedNumberOfCopies = currentlyBorrowedCopies + copiesLoaned;
            System.out.println("Previously borrowed: " + currentlyBorrowedCopies);
            System.out.println("updated: " + updatedNumberOfCopies);
            // update current borrow value from current value to updated for movieName (key)
            this.onLoan.replace(movieName, currentlyBorrowedCopies, updatedNumberOfCopies);
        } else {
            // if its first time borrow add the entry into hashmap
            // create a new hashmap with the movie and # copies borrowed
            this.onLoan.put(movieName, copiesLoaned);
        }

    }

    // @returns - all the values loaned
    public HashMap<String, Integer> getAllLoans() {
        return this.onLoan;
    }
}
