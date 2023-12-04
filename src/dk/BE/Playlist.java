package dk.BE;

public class Playlist {

    private String name;
    private int time;
    private int songs;
    private int id;

    public Playlist(String name, int time, int songs, int id) {
        this.name = name;
        this.time = time;
        this.songs = songs;
        this.id = id;
    }

    public Playlist(String name, int time, int songs){
        this.name = name;
        this.time = time;
        this.songs = songs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getSongs() {
        return songs;
    }

    public void setSongs(int songs) {
        this.songs = songs;
    }

    public int getId() {
        return id;
    }
}
