package dk.BE;

import java.util.List;

public class Playlist {

    private String name;
   
    private int id;

    private List<Playlist> playlists;

    public Playlist(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public Playlist(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "name= " + name;
    }
}
