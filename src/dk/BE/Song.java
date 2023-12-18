package dk.BE;

public class Song {

    private String title, artist, filePath, genre;
    private int time, id;

    public Song(String title, int id, String artist, int time, String genre, String filePath) {
        this.title = title;
        this.artist = artist;
        this.time = time;
        this.genre = genre;
        this.id = id;
        this.filePath = filePath;
    }

    public Song(String title, String artist, int time, String filePath, String genre){
        this.title = title;
        this.artist = artist;
        this.time = time;
        this.genre = genre;
        this.filePath = filePath;
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

    public String getFilePath() {
        return filePath;
    }

    /**
     *  Changes time from seconds to minutes:seconds
     *  Ex: 02:35
     */

    public String getTimeString() {

        int minutes = (time / 60) % 60;
        int seconds = time % 60;

        String formatedTime = String.format("%d:%02d", minutes, seconds);
        return formatedTime;
    }

    public int getTime(){
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
