public class Movie {
    private String title;
    private String starring;
    private String director;
    private String genre;
    private String classification;
    private String duration;
    private int releaseData;
    private int copiesAvailable;
    private int timesRented;

    public Movie(String title, String starring, String director, String genre, String classification,
                 String duration, int releaseData, int copiesAvailable, int timesRented) {
        this.title = title;
        this.starring = starring;
        this.director = director;
        this.genre = genre;
        this.classification = classification;
        this.duration = duration;
        this.releaseData = releaseData;
        this.copiesAvailable = copiesAvailable;
        this.timesRented = timesRented;
    }
}

