
import java.util.Scanner;
import java.lang.InterruptedException;
import java.util.concurrent.TimeUnit;

public class Main {
    public static int EXIT = 0;
    public static Scanner scanner;
    public static int inputNum;
    public static String inputLine;

    public static void main(String[] args) throws InterruptedException {
        // Setup Application by instantiating memberCollection
        MemberCollection membersCollection = new MemberCollection();  // static ???
        scanner = new Scanner(System.in);  // Create a Scanner object

        while(true) {
            // main menu - welcome
            mainMenu(membersCollection);
        }
    }

    public static void exitApp() throws InterruptedException {
        System.out.println("Exiting application...");
        TimeUnit.SECONDS.sleep(3);
        System.exit(0);
    }

    private static void staffLogin(MemberCollection membersCollection) throws InterruptedException {
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
            staffMenu(membersCollection);

        } else { // username doesn't exist. Authentication failed.
            System.out.println("\nCould not authenticate. Please go back to main menu...");
            exitApp();
        }
    }

    public static void mainMenu(MemberCollection membersCollection) throws InterruptedException {
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
                staffLogin(membersCollection);   // authenticate for access

                // exit
                inputNum = scanner.nextInt();  // Read user input
                if(inputNum == EXIT) break;

            case 2:
                memberMenu();
                inputNum = scanner.nextInt();  // Read user input
                if(inputNum == EXIT) break;
        }

    }

    public static void staffMenu(MemberCollection membersCollection) throws InterruptedException {
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
                mainMenu(membersCollection);
            case 1:
            case 2:
            case 3:
                registerMemberMenu(membersCollection);
            case 4:
        }
    }

    public static void registerMemberMenu(MemberCollection membersCollection) throws InterruptedException {
        String fName;
        String lName;
        inputLine = scanner.nextLine();
        System.out.print("Enter member's first name: ");
        inputLine = scanner.nextLine();
        fName = inputLine;

        System.out.print("Enter member's last name: ");
        inputLine = scanner.nextLine();
        lName = inputLine;

        if(membersCollection.checkMemberExists(fName, lName)) {
            System.out.println( ">> " + fName + " " + lName + " has already registered.");
            staffMenu(membersCollection); // recursively go back to staff menu
        } else {
            // register this guy
        }

    }

    public static void memberMenu() {
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
        }
}

//
//class Auth {
//
//     public static boolean authenticate(String username, String password, MemberCollection list) {
//
//        HashMap usersDatabase = new HashMap<String, User>();
//         // set user database with default root admin user
//         User root = new User("staff", "today123", true);
//
//        usersDatabase.put("staff", root);
//
//        if(usersDatabase.containsKey(username)) {
//           // check if password exists
//            return true;
//
//        } else {
//            // password does not match.
//            return false;
//        }
//    }
//
//    public static int registerUser(String username, String password, Boolean isAdmin) {
//         // check if username already exists
//            // if not create a new user object and save in usersDatabase
//        return 0;
//    }
//}
//
