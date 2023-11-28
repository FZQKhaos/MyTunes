package dk.BE;

public class Song {

    private String name;
    private String artist;
    private int year;
    private int songLength;
    private String genre;
    private int id;

    public Song(String name, String artist, int year, int songLength, String genre, int id) {
        this.name = name;
        this.artist = artist;
        this.year = year;
        this.songLength = songLength;
        this.genre = genre;
        this.id = id;
    }

    public Song(String name, String artist, int year, int songLength, String genre){
        this.name = name;
        this.artist = artist;
        this.year = year;
        this.songLength = songLength;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
