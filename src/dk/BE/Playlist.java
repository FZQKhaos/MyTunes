package dk.BE;

public class Playlist {

    private String name;
    private int playlistLength;
    private int songs;
    private int id;

    public Playlist(String name, int playlistLength, int songs, int id) {
        this.name = name;
        this.playlistLength = playlistLength;
        this.songs = songs;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlaylistLength() {
        return playlistLength;
    }

    public void setPlaylistLength(int playlistLength) {
        this.playlistLength = playlistLength;
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
