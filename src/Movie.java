public class Movie {
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
}

