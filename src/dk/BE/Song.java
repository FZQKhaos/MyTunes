package dk.BE;

public class Song {

    private String title;
    private String artist;
    private int year;
    private int songLength;
    private String genre;
    private int id;

    public Song(String title, String artist, int year, int songLength, String genre, int id) {
        this.title = title;
        this.artist = artist;
        this.year = year;
        this.songLength = songLength;
        this.genre = genre;
        this.id = id;
    }

    public Song(String title, String artist, int songLength, String genre, int id){
        this.title = title;
        this.artist = artist;
        this.songLength = songLength;
        this.genre = genre;
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

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSongLength() {
        return songLength;
    }

    public void setSongLength(int songLength) {
        this.songLength = songLength;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
