package dk.BLL.util;

import dk.BE.Song;

import java.util.ArrayList;
import java.util.List;

public class SongSearcher {


public List<Song> search(List<Song> searchBase, String query){
    List<Song> searchResult = new ArrayList<>();

    for (Song song: searchBase){
        if (compareSongTitle(query, song) || compareSongArtist(query, song)){
            searchResult.add(song);
        }
    }
    return searchResult;
}

private boolean compareSongTitle(String query, Song song){
    return song.getTitle().toLowerCase().contains(query.toLowerCase());
}

    private boolean compareSongArtist(String query, Song song){
    return song.getArtist().toLowerCase().contains(query.toLowerCase());
    }
}
