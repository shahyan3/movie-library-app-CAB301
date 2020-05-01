
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.InterruptedException;
import java.util.concurrent.TimeUnit;

public class Main {
    public static int EXIT = 0;
    public static Scanner scanner;
    public static int inputNum;
    public static String inputLine;
    public static  MoviesCollection moviesCollection;
    public static MemberCollection membersCollection;
    public static Member currentUser;

    public static void main(String[] args) throws InterruptedException {
        // Setup Application by instantiating memberCollection
        membersCollection = new MemberCollection();  // static ???
        moviesCollection = new MoviesCollection(); // static ???
        scanner = new Scanner(System.in);  // Create a Scanner object

        while(true) {
            // main menu - welcome
            mainMenu();
        }
    }

    public static void exitApp() throws InterruptedException {
        System.out.println("Exiting application...");
        TimeUnit.SECONDS.sleep(3);
        System.exit(0);
    }

    private static void staffLogin() throws InterruptedException {
        String username;
        String password;

        inputLine = scanner.nextLine();  // first next line consumes the "enter username" to enable next one to read user input

        System.out.print("Enter username: ");

        inputLine = scanner.nextLine();  // Read user input
        username = inputLine;

        System.out.print("Enter password: ");
        inputLine = scanner.nextLine();  // Read user input
        password = inputLine;

        if(membersCollection.authenticateMember(username.trim(), password.trim())) { // check given username exists trim whitespace
            // staff menu - authentication succeeded.
            staffMenu();

        } else { // username doesn't exist. Authentication failed.
            System.out.println("\nCould not authenticate. Please go back to main menu...");
            exitApp();
        }
    }

    private static void userLogin() throws InterruptedException {
        String username;
        String password;

        inputLine = scanner.nextLine();  // first next line consumes the "enter username" to enable next one to read user input

        System.out.print("Enter username:(LastnameFirstname) ");

        inputLine = scanner.nextLine();  // Read user input
        username = inputLine;

        System.out.print("Enter password: ");
        inputLine = scanner.nextLine();  // Read user input
        password = inputLine;
            System.out.println(" ");

        if(membersCollection.authenticateMember(username.trim(), password.trim())) { // check given username exists trim whitespace
            // staff menu - authentication succeeded.
            memberMenu();

        } else { // username doesn't exist. Authentication failed.
            System.out.println("\nCould not authenticate. Please go back to main menu...");
            exitApp();
        }
    }

    public static void mainMenu() throws InterruptedException {
        System.out.println(" ");
        System.out.println("Welcome to the Community Library");
        System.out.println("========== Main Menu =============");
        System.out.println("1. Staff Login");
        System.out.println("2. Member Login");
        System.out.println("0. Exit");
        System.out.println("=================================");
        System.out.print("Please make selection (1-2, or 0 to exit): ");

        inputNum = scanner.nextInt();  // Read user input

        if(inputNum == EXIT) {
            exitApp();
        }

        switch(inputNum) {
            case 1: // enter staff menu
                staffLogin();   // authenticate for access

                // exit
                inputNum = scanner.nextInt();  // Read user input
                if(inputNum == EXIT) break;

            case 2:
                userLogin();
                inputNum = scanner.nextInt();  // Read user input
                if(inputNum == EXIT) break;
        }

    }

    public static void staffMenu() throws InterruptedException {
        System.out.println(" ");
        System.out.println("========== Staff Menu =============");
        System.out.println("1. Add a new movie DVD");
        System.out.println("2. Remove a movie DVD");
        System.out.println("3. Register a new Member");
        System.out.println("4. Find a registered member's phone number");
        System.out.println("0. Return to main menu");
        System.out.println("=================================");
        System.out.print("Please make selection (1-4, or 0 to return to main menu): ");

        inputNum = scanner.nextInt();  // Sub options for staff menu
        switch(inputNum) {
            case 0:
                mainMenu();
            case 1:
            case 2:
            case 3:
                registerMemberMenu();
            case 4:
                findMemberPhoneMenu();
        }
    }

    public static void registerMemberMenu() throws InterruptedException {
        String fName;
        String lName;
        String address;
        String phoneNumber;
        String password;
        boolean isAdmin;

        inputLine = scanner.nextLine();
        System.out.print("Enter member's first name: ");
        inputLine = scanner.nextLine();
        fName = inputLine;

        System.out.print("Enter member's last name: ");
        inputLine = scanner.nextLine();
        lName = inputLine;

        if(membersCollection.checkMemberExists(fName, lName)) {
            System.out.println( ">> " + fName + " " + lName + " has already registered.");
            staffMenu(); // recursively go back to staff menu
        } else {
            // new user: register as a member
            System.out.print("Enter member's address: ");
            inputLine = scanner.nextLine();
            address = inputLine;

            System.out.print("Enter member's phone number: ");
            inputLine = scanner.nextLine();
            phoneNumber = inputLine;

            System.out.print("Enter member's password (4 digits): ");      // #TODO enforce 4 digits
            inputLine = scanner.nextLine();
            password = inputLine;

            // isAdmin equals false - this is a normal user
            isAdmin = false;
            // create a new member
            membersCollection.registerUser(fName, lName, address, phoneNumber, password, isAdmin);
            // success. go back to main
            staffMenu();
        }

    }

    public static void findMemberPhoneMenu() throws InterruptedException {
        String fName, lName;

        scanner.nextLine();     // consume a line, don't save it. So scanner saves the next input correctly.
        System.out.print("Enter member's first name: ");
        inputLine = scanner.nextLine();
        fName = inputLine;

        System.out.print("Enter member's last name: ");
        inputLine = scanner.nextLine();
        lName = inputLine;

        // check if member with given creds exist in collection
        Member member = membersCollection.getMember(fName, lName);
        if(member != null) {
            String phoneNumber = member.getPhoneNumber();
            System.out.println(">>" +fName + " " + lName + "'s phone number is: " + phoneNumber);
        }
        // return to staff menu
        staffMenu();
    }

    public static void memberMenu() {

        if(user) {
            System.out.println(" ");
            System.out.println("========== Member Menu =============");
            System.out.println("1. Display all movies");
            System.out.println("2. Borrow a movie DVD");
            System.out.println("3. Return a movie DVD");
            System.out.println("4. List current borrowed movie DVDs");
            System.out.println("5. Display top 10 most popular movies");
            System.out.println("0. Return to main menu");
            System.out.println("=================================");

            System.out.print("Please make selection (1-5, or 0 to return to main menu): ");

            inputNum = scanner.nextInt();  // Sub options for user menu
            switch (inputNum) {
                case 0:
                case 1:
                    displayAllMoviesMenu();
                case 2:
                    borrowMovieMenu();
                case 3:
                case 4:
            }
        }

    }

    /** MovieCollection Calls -- to be fixed after creating BST  **/
    // only called when user is authenticated
    public static void displayAllMoviesMenu() {
        ArrayList<Movie> list = moviesCollection.getAllMovies(); // shouldn't be a bst

        for (Movie movie: list) {
            System.out.println(" ");
            System.out.println("Title: " + movie.getTitle());
            System.out.println("Starring: " + movie.getStarring());
            System.out.println("Director: " + movie.getDirector());
            System.out.println("Genre: " + movie.getGenre());
            System.out.println("Classification: " + movie.getClassification());
            System.out.println("Duration: " + movie.getDuration());
            System.out.println("Release Date: " + movie.getReleaseDate());
            System.out.println("Copies Available: " + movie.getCopiesAvailable());
            System.out.println("Times Rented: " + movie.getTimesRented());

        }
        memberMenu();  // display members menu again
    }

    public void borrowMovieMenu() { // #TODO
        String movieName;
        System.out.println("Enter movie title: ");
        inputLine = scanner.nextLine();
        movieName = inputLine.trim(); // remove extra whitespaces from string input

        if(moviesCollection.movieExist(movieName)) {
            boolean flag;

            flag = moviesCollection.borrowMovie(movieName);

            if(flag == NOT_FOUND){
                System.out.println("Movie not found in database");
                memberMenu(); // return menu
            } else if(flag == UNAVAILABLE) {
                System.out.println("Unavailable to borrow: " + movieName);
                memberMenu(); // return to menu
              } else if(flag == SUCCESS) {
                currentUser.setLoaned(MovieName, numberOfCopies); // update user's loaned property
                  System.out.println("You borrowed " + movieName);
                  memberMenu(); // return to menu
              } else {
                System.out.println("Error. Try later");
                memberMenu();
            }
        }
    }

}