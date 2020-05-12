import java.util.HashMap;

public class Member {
    private int memberID;
    private String username; // accessible to other classes.
    private String password;
    private boolean isAdmin;
    private String fName;
    private String lName;
    private String phoneNumber;
    private HashMap<String, Integer> onLoan;    // keeps track of movies loaned value = loanedCopies and key = movie name

    public Member( int memberID, String fName, String lName, String username, String password, String phoneNumber, boolean isAdmin) {
        this.memberID = memberID;
        this.username = username;
        this.password = password;
        this.fName = fName;
        this.lName = lName;
        this.phoneNumber = phoneNumber;
        this.isAdmin = isAdmin;
        this.onLoan = new HashMap<String, Integer>();
    }
    public int getMemberID() {
        return memberID;
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
        if(currentlyBorrowedCopies != null && currentlyBorrowedCopies > 0) { // If a movie had been borrowed already by this user and borrowing more copies - find existing hashmap node and update the total # of copies borrowed
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

    public void returnMovie(String movieName, int returnCopies) {
        Integer currentlyBorrowedCopies = this.onLoan.get(movieName);

        if(currentlyBorrowedCopies != null && currentlyBorrowedCopies > 0) {
            Integer updatedNumberOfCopies = currentlyBorrowedCopies - returnCopies;
            System.out.println("Previously borrowed: " + currentlyBorrowedCopies);
            System.out.println("updated (returned): " + updatedNumberOfCopies);
            // update User's property: current borrow value from current value to updated for movieName (key)
            this.onLoan.replace(movieName, currentlyBorrowedCopies, updatedNumberOfCopies);

            // Remove the hashmap if the number of copies after removing is equal to 0
            if(this.onLoan.get(movieName) == 0) {
                this.onLoan.remove(movieName);
            }

            System.out.println("Movie DVD returned");
        } else {
            System.out.println("Invalid: Movie is not currently loan by this user.");
        }
    }

    // @returns - all the values loaned
    public HashMap<String, Integer> getAllLoans() {
        return this.onLoan;
    }


}
