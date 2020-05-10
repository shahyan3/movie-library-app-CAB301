public class Movie {
    // static members
    private static final int SUCCESS = 1;
    private static final int ERROR = -2;
    private static final int UNAVAILABLE = 0;
    private static int ZERO = 0;
    private static final int DECREMENT_BY_ONE = -1;
    private static final int INCREMENT_BY_ONE = 1;

    // private members
//    private int movieID;
    private String title;
    private String starring;
    private String director;
    private String genre;
    private String classification;
    private String duration;
    private int releaseDate;
    private int copiesAvailable;
    private int timesRented;

    public Movie(String title, String starring, String director, String genre, String classification,
                 String duration, int releaseDate, int copiesAvailable, int timesRented) {
        this.title = title;
        this.starring = starring;
        this.director = director;
        this.genre = genre;
        this.classification = classification;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.copiesAvailable = copiesAvailable;
        this.timesRented = timesRented;
    }

    public String getTitle() {
        return title;
    }

    public String getStarring() {
        return starring;
    }

    public String getDirector() {
        return director;
    }

    public String getGenre() {
        return genre;
    }

    public String getClassification() {
        return classification;
    }

    public String getDuration() {
        return duration;
    }

    public int getReleaseDate() {
        return releaseDate;
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }

    public int getTimesRented() {
        return timesRented;
    }

    public int setLoanedTo(String username) {
        if (this.copiesAvailable > ZERO) {
            this.copiesAvailable = this.copiesAvailable + DECREMENT_BY_ONE;
            this.timesRented = this.timesRented + INCREMENT_BY_ONE;

            System.out.println("==>> copiesAvailable" + this.copiesAvailable + ". timesRented: " + this.timesRented);
            return SUCCESS;
        } else {
            return UNAVAILABLE;
        }
    }
}



