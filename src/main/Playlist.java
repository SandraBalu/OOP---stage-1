package main;

import fileio.input.SongInput;
import fileio.input.UserInput;

import java.util.ArrayList;

public class Playlist {

    private String  username;

    private String playlistName;

    private ArrayList<SongInput> playlistSongs;
    private  int playlistId;
    private  boolean empty;

    private boolean visibility;

    private int followers;
    private int contorSongs;

    public int getContorSongs() {
        return contorSongs;
    }

    public void setContorSongs(int contorSongs) {
        this.contorSongs = contorSongs;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public ArrayList<SongInput> getPlaylistSongs() {
        return playlistSongs;
    }

    public void setPlaylistSongs(ArrayList<SongInput> playlistSongs) {
        this.playlistSongs = playlistSongs;
    }
}
