package dk.BE;

public class Song {

    private String title;
    private String artistName;
    private int year;
    private int time;
    private String genreName;
    private int id;

    public Song(String title, String artistName, int year, int time, String genreName, int id) {
        this.title = title;
        this.artistName = artistName;
        this.year = year;
        this.time = time;
        this.genreName = genreName;
        this.id = id;
    }

    public Song(String title, String artistName, int time, String genreName, int id){
        this.title = title;
        this.artistName = artistName;
        this.time = time;
        this.genreName = genreName;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = title;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTime() {
        // duration = seconds from DB
        int duration = time;

        int minutes = (duration / 60) % 60;
        int seconds = duration % 60;

        String formatedTime = String.format("%d:%02d", minutes, seconds);
        return formatedTime;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }
}
