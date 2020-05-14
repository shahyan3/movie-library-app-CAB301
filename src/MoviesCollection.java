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
    private int moviesCount =0;


    public MoviesCollection() {
        // init movies list
         this.moviesList = new BinarySearchTree();
        // creates a default set of movies in list
        addDefaultMovies();
    }

    public void addDefaultMovies() {
        Movie movie1 = new Movie("Aang 1", "Actor 1 and Actress 1",
                "Director 1", "Thriller", "MA",
                "60 minutes", 2012, 5, 0);

        Movie movie2 = new Movie("Bob's World", "Actress 1",
                "Director 2", "Animated", "MA",
                "120 minutes", 1980, 2, 0);


        Movie movie3 = new Movie( "Aaab", "Actor 3",
                "Director 3", "Comedy", "MA",
                "180 minutes", 2000, 3, 0);

        Movie movie4 = new Movie("Cat's World", "Actor 1 and Actress 1",
                "Director 1", "Drama", "Parental Guidance (PG)",
                "90 minutes", 2000, 5, 0);

        Movie movie5 = new Movie("Z", "Actor 1 and Actress 1",
                "Director 1", "Drama", "Parental Guidance (PG)",
                "90 minutes", 2000, 5, 0);

        Movie movie6 = new Movie("Abbys", "Actor 1 and Actress 1",
                "Director 1", "Drama", "Parental Guidance (PG)",
                "90 minutes", 2000, 5, 0);


        // add movie (node) and key in BST
        moviesList.addNode(movie1.getTitle(), movie1);
        this.setMoviesCount(INCREMENT_BY_ONE);
        moviesList.addNode(movie2.getTitle(), movie2);
        this.setMoviesCount(INCREMENT_BY_ONE);
        moviesList.addNode(movie3.getTitle(), movie3);
        this.setMoviesCount(INCREMENT_BY_ONE);
        moviesList.addNode(movie4.getTitle(), movie4);
        this.setMoviesCount(INCREMENT_BY_ONE);
        moviesList.addNode(movie5.getTitle(), movie5);
        this.setMoviesCount(INCREMENT_BY_ONE);
        moviesList.addNode(movie6.getTitle(), movie6);
        this.setMoviesCount(INCREMENT_BY_ONE);

    }

    public void setMoviesCount(int count) {
        this.moviesCount += count;
    }
    public int getMoviesCount() {
        return this.moviesCount;
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

    // adds movie to the bst
    public void addMovie(Movie movie) {
        String movieName = movie.getTitle();
        this.moviesList.addNode(movieName, movie);

        // increment total movies in library
        this.setMoviesCount(INCREMENT_BY_ONE);
    }

    public void deleteMovie(String movieName) {
       this.moviesList.remove(movieName);
       // decrement total movies in library
       this.setMoviesCount(DECREMENT_BY_ONE);

       System.out.println("Movie: " +movieName +" deleted." );
    }

    public void returnMovie(String movieName, int returnedCopies) {
       Node node = this.moviesList.findNode(movieName);
       node.movie.setCopiesAvailable(returnedCopies);
     }

     public void getTopTen() {
       // returns a bst to an array
        Movie[] unsortedList = this.moviesList.returnBSTAsArray(this.getMoviesCount() ); // create a property total movies in libary here
         // sort them based on times rented
         Movie[] sortedList = mergeSort(unsortedList);

         // if th ele
         if(sortedList.length < 10) {   // if there is less than 10 movies in the library just display the total #
             // display top ten
             for(int i = 0; i < this.getMoviesCount(); i++) {
                 Movie movie = sortedList[i];
                 if(movie !=null) {
                     System.out.println(movie.getTitle() + " times rented " + movie.getTimesRented());
                 }
             }
         } else { // only display 10 movies from the library
             // display top ten
             for(int i = 0; i < 10; i++) {
                 Movie movie = sortedList[i];
                 if(movie !=null) {
                     System.out.println(movie.getTitle() + " times rented: " + movie.getTimesRented());
                 }
             }
         }
     }

    private static Movie[] mergeSort(Movie[] array) {
        // array only has one value or need to sort
        if(array.length <= 1) {
            return array;
        }

        // midpoint index of the initial array passed
        int midpoint = array.length / 2;

        // subarray #1 of array: total legnth is: 0 to middle nth elements
        Movie[] left = new Movie[midpoint];
        Movie[] right;

        // check if array has even elements or odd - that determines the midpoint index for right
        // create sub array #2 of array
        if(array.length % 2 == 0) {
            right = new Movie[midpoint]; // say array[4] then right[2] and left[2] evenly.
        } else {
            right = new Movie[midpoint + 1]; // odd elements so e.g say 5 array[5] then left[2] and right[3] of lengths
        }

        // populate the left array with - to middle nth elmements of array (i.e. we're sorting)
        for(int i = 0; i < midpoint; i++) {
            left[i] = array[i]; // so adds [5, 4] of [5, 4, 3, 2, 1]
        }

        // populate the right array with - middle (+1 for odd parent elements) to last element in array (parent)
        for(int j = 0; j < right.length; j++) {
            right[j] = array[midpoint+j]; // so adds [3, 2, 1] of [5, 4, 3, 2, 1]
        }

        Movie[] result;

        // recursive part of the mergeSort
        // recursion over once line 13 of function is true, and returns array.
        left = mergeSort(left);
        right = mergeSort(right);

        result = merge(left, right);

        return result;

    }

    private static Movie[] merge(Movie[] left, Movie[] right) {
        Movie[] result = new Movie[left.length + right.length];

        int leftPointer, rightPointer, resultPointer;

        leftPointer = rightPointer = resultPointer = 0;

        while(leftPointer < left.length || rightPointer < right.length) {

            // checking if both left AND right arrays have elements to be sorted/tested - sort final as follows:
            if(leftPointer < left.length && rightPointer < right.length) {

                // check which element in greater in the right/left arrays (if greater add it to the list first so sorting is in largest to smallest times rented order
                if(left[leftPointer].getTimesRented() > right[rightPointer].getTimesRented()) {
                    result[resultPointer++] = left[leftPointer++];
                } else { // right pointer is less than left
                    result[resultPointer++] = right[rightPointer++];

                }
            } else if(leftPointer < left.length) { // only left array has elements to be sorted/tested
                result[resultPointer++] = left[leftPointer++];
            }

            else if(rightPointer < right.length) { // only right array has elements to be sorted/tested
                result[resultPointer++] = right[rightPointer++];
            }
        }

        return result;
    }


}

