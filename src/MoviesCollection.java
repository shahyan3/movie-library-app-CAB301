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
    private ArrayList<Movie> moviesList;
    private int MAX_TITLES = 100;

    public MoviesCollection() {
        // init movies list
        moviesList = new ArrayList<Movie>(); // #TODO convert int BST
        // creates a default set of movies in list
        addDefaultMovies();
    }

    public void addDefaultMovies() {
        Movie movie1 = new Movie("Movie 1", "Actor 1 and Actress 1",
                "Director 1", "Thriller", "MA",
                "60 minutes", 2012, 5, 0);

        Movie movie2 = new Movie("Movie 2", "Actress 1",
                "Director 2", "Animated", "MA",
                "120 minutes", 1980, 2, 0);


        Movie movie3 = new Movie("Movie 3", "Actor 3",
                "Director 3", "Comedy", "MA",
                "180 minutes", 2000, 3, 0);

        Movie movie5 = new Movie("Movie 5", "Actor 1 and Actress 1",
                "Director 1", "Drama", "Parental Guidance (PG)",
                "90 minutes", 2000, 5, 0);

        moviesList.add(movie1);
        moviesList.add(movie2);
        moviesList.add(movie3);
        moviesList.add(movie5);
    }

    public ArrayList<Movie> getAllMovies() {
        return this.moviesList;
    }

    // #TODO FIX TO BST LATER
    public int movieExist(String movieName) {
        for (Movie movie : this.moviesList) {
            if(movie.getTitle().equalsIgnoreCase(movieName)) {
                return SUCCESS;
            }
        }
        return NOT_FOUND;
    }

    public int borrowMovie(String movieName, String username) {
        // find movie
        for (Movie movie : this.moviesList) {
            if(movie.getTitle().equalsIgnoreCase((movieName))) {
                // Movie available! borrow it
                int flag = movie.setLoanedTo(username);

                if (flag == SUCCESS) {
                    return SUCCESS;
                }

                if(flag == UNAVAILABLE) {
                     return UNAVAILABLE;
                }
            }
        }
        return ERROR;
    }
}

