
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.lang.InterruptedException;
import java.util.concurrent.TimeUnit;


public class Main {
    private static final int NOT_FOUND = -1;
    private static final int UNAVAILABLE = 0;
    private static final int SUCCESS = 1;
    private static final int ERROR = -2;


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

        Member staff = membersCollection.authenticateMember(username.trim(), password.trim());
        if(staff != null) { // check given username exists trim whitespace
            // staff menu - authentication succeeded.
            currentUser = staff;
            staffMenu();

        } else { // username doesn't exist. Authentication failed.
            System.out.println("\nCould not authenticate. Please go back to main menu...");
            currentUser = null;
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

        Member user = membersCollection.authenticateMember(username.trim(), password.trim());
        if(user != null) { // check given username exists trim whitespace
            // staff menu - authentication succeeded.
            currentUser = user;
            memberMenu();

        } else { // username doesn't exist. Authentication failed.
            System.out.println("\nCould not authenticate. Please go back to main menu...");
            currentUser = null;
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


        if(currentUser !=null) {
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
            switch (inputNum) {
                case 0:
                    mainMenu();
                 case 1:
                    addMovieMenu();
                 case 2:
                     System.out.println("Remove a movie DVD...");
                case 3:
                    registerMemberMenu();
                 case 4:
                    findMemberPhoneMenu();
             }
        }
    }

    public static void addMovieMenu() throws InterruptedException {
        String movieName;
        String actors;
        String directorName;
        int genreInt;
        String genre = null;
        int classificationInt;
        String classification = null;
        String duration;
        int releaseDate;
        int copiesAvailable;
        int timesRented = 0;

        System.out.println(" ");
        scanner.nextLine();
        System.out.print("Enter the movie title: ");
        movieName = scanner.nextLine().trim();

        System.out.print("Enter the starring actor(s): ");
        actors = scanner.nextLine();

        System.out.print("Enter the director(s): ");
        directorName = scanner.nextLine();

        System.out.println("Select the genre: ");
        System.out.println("1. Drama");
        System.out.println("2. Adventure");
        System.out.println("3. Family");
        System.out.println("4. Action");
        System.out.println("5. Sci-Fi");
        System.out.println("6. Comedy");
        System.out.println("7. Thriller");
        System.out.println("8. Other");
        System.out.print("Make selection(1-8): ");
        genreInt = scanner.nextInt();

        System.out.println("\nSelect the classification:");
        System.out.println("1. General (G)");
        System.out.println("2. Parental Guidance (PG)");
        System.out.println("3. Mature (MA15+)");
        System.out.println("4. Mature Accompanied (MA15+)");
        System.out.print("\nMake selection(1-4): ");
        classificationInt = scanner.nextInt();

        System.out.print("Enter the duration (minutes): ");
        duration = scanner.nextLine();

        System.out.print("Enter the release date (year): ");
        releaseDate = scanner.nextInt();

        System.out.print("Enter the number of copies available: ");
        copiesAvailable = scanner.nextInt();

        // assign classificationInt based on the int value given
            switch (classificationInt) {
                case 1:
                    classification = "General (G)";
                    break;
                case 2:
                    classification = "Parental Guidance (PG)";
                    break;
                case 3:
                    classification = "Mature (MA15+)";
                    break;
                case 4:
                    classification = "Mature Accompanied (MA15+)";
                    break;
            }

            // assign classification based on given user int value
            switch (genreInt) {
                case 1:
                    genre = "Drama";
                    break;
                case 2:
                    genre = "Adventure";
                    break;
                case 3:
                    genre = "Family";
                    break;
                case 4:
                    genre = "Action";
                    break;
                case 5:
                    genre = "Sci-Fi";
                    break;
                case 6:
                    genre = "Comedy";
                    break;
                case 7:
                    genre = "Thriller";
                    break;
                case 8:
                    genre = "Other";
                    break;
            }

            // create a new movie object
            Movie movie = new Movie(movieName, actors, directorName, genre, classification, duration, releaseDate,
                        copiesAvailable, timesRented);

            // add movie to collection
            moviesCollection.addMovie(movie);

            staffMenu();
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
            // create member id (which is the current index count of the member array
            int currentIndex = membersCollection.getCurrentMemberCountIndex();
            currentIndex += 1;  // next index where this member will be in array position
            // create a new member
            membersCollection.registerUser(currentIndex, fName, lName, address, phoneNumber, password, isAdmin);
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

    public static void memberMenu() throws InterruptedException {

        if(currentUser != null) {
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
                        mainMenu();
                    case 1:
                        displayAllMoviesMenu();
                    case 2:
                        borrowMovieMenu();
                    case 3:
                    case 4:
                        listBorrowedMoviesMenu();
                }
        }
    }

    /** MovieCollection Calls -- to be fixed after creating BST  **/
    // only called when user is authenticated    - fixed
    public static void displayAllMoviesMenu() throws InterruptedException {
        moviesCollection.displayAllMovies();
        memberMenu();  // display members menu again
    }

    public static void borrowMovieMenu() throws InterruptedException { // #TODO
        String movieName;

        scanner.nextLine();
        System.out.println("Enter movie title: ");
        inputLine = scanner.nextLine();
        movieName = inputLine.trim(); // remove extra whitespaces from string input

        int movieFound = moviesCollection.movieExist(movieName);

        if (movieFound == SUCCESS) {
            int flag;

            flag = moviesCollection.borrowMovie(movieName, currentUser.getUsername());

            if (flag == UNAVAILABLE) {
                System.out.println("Unavailable to borrow: " + movieName);
                memberMenu(); // return to menu
            } else if (flag == SUCCESS) {
                // add number of copies and movie loaned to users property...
                  currentUser.setOnLoan(1, movieName);

                System.out.println("You borrowed " + movieName);
                memberMenu(); // return to menu
            } else {
                System.out.println("Error. Try later");
                memberMenu();
            }
        } else {
            System.out.println("Movie not found in database");
            memberMenu(); // return menu
        }

    }

    public static void listBorrowedMoviesMenu() throws InterruptedException {
         System.out.println("List of currently borrowed movies: ");
        HashMap<String, Integer> onLoans = currentUser.getAllLoans();
        // print all the values
        onLoans.forEach((key, value) -> System.out.println("Title:" + key + ", " + value + " on loan."));

        memberMenu();
    }

}