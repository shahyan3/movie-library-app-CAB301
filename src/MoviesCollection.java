import java.util.ArrayList;

public class MoviesCollection {
    // static members
    private static final int NOT_FOUND = -1;
    private static final int UNAVAILABLE = 0;
    private static final int SUCCESS = 1;
    private static final int ERROR = -2;
    private static final int INCREMENT_BY_ONE = 1;
    private static final int DECREMENT_BY_ONE = -1;


    // private members
    private BinarySearchTree moviesList;
    private int MAX_TITLES = 100;


    public MoviesCollection() {
        // init movies list
         this.moviesList = new BinarySearchTree();
        // creates a default set of movies in list
        addDefaultMovies();
    }

    public void addDefaultMovies() {
        Movie movie1 = new Movie(1,"Aang 1", "Actor 1 and Actress 1",
                "Director 1", "Thriller", "MA",
                "60 minutes", 2012, 5, 0);

        Movie movie2 = new Movie(2, "Bob's World", "Actress 1",
                "Director 2", "Animated", "MA",
                "120 minutes", 1980, 2, 0);


        Movie movie3 = new Movie( 3, "Aaab", "Actor 3",
                "Director 3", "Comedy", "MA",
                "180 minutes", 2000, 3, 0);

        Movie movie4 = new Movie(4, "Cat's World", "Actor 1 and Actress 1",
                "Director 1", "Drama", "Parental Guidance (PG)",
                "90 minutes", 2000, 5, 0);

        Movie movie5 = new Movie(5, "Z", "Actor 1 and Actress 1",
                "Director 1", "Drama", "Parental Guidance (PG)",
                "90 minutes", 2000, 5, 0);

        Movie movie6 = new Movie(6, "Abbys", "Actor 1 and Actress 1",
                "Director 1", "Drama", "Parental Guidance (PG)",
                "90 minutes", 2000, 5, 0);


        // add movie (node) and key in BST
        moviesList.addNode(movie6.getTitle(), movie6);
        moviesList.addNode(movie1.getTitle(), movie1);
        moviesList.addNode(movie2.getTitle(), movie2);
        moviesList.addNode(movie3.getTitle(), movie3);
        moviesList.addNode(movie4.getTitle(), movie4);
        moviesList.addNode(movie5.getTitle(), movie5);
    }

   public void displayAllMovies() {
        this.moviesList.inOrderTraverseTree(this.moviesList.root);
    }

    public int movieExist(String movieName) {
        // parse string movie name to int ascii value to get the key
//        int key = this.moviesList.parseStringToASCIIValue(movieName);
       Node node =  this.moviesList.findNode(movieName);

       if(node != null && node.movie.getTitle().equalsIgnoreCase(movieName)) {
           return SUCCESS;
       } else {
           return NOT_FOUND;
       }
    }

    public int borrowMovie(String movieName, String username) {
            // parse string movie name to int ascii value to get the key
//            int key = this.moviesList.parseStringToASCIIValue(movieName);
            Node node =  this.moviesList.findNode(movieName);

            if(node != null && node.movie.getTitle().equalsIgnoreCase(movieName)) { // movie in bst
                // try to borrow it
                int flag = node.movie.setLoanedTo(username);

                if (flag == SUCCESS) {
                    return SUCCESS; // borrowed!
                }

                if(flag == UNAVAILABLE) {
                     return UNAVAILABLE;    // no copies available
                }

            }
            return ERROR;   // movie doesn't exist in bst
    }
}

