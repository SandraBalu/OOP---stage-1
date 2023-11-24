package main;

import fileio.input.SongInput;

import java.util.ArrayList;

public final class Playlist {

    private String  username;
    private String playlistName;
    private ArrayList<SongInput> playlistSongs;
    private  int playlistId;
    private  boolean empty;
    private boolean visibility;
    private int followers;
    private int contorSongs;
    private int totalSongs;

    public  int getTotalSongs() {
        return totalSongs;
    }

    public void setTotalSongs(final int totalSongs) {
        this.totalSongs = totalSongs;
    }

    public int getContorSongs() {
        return contorSongs;
    }

    public void setContorSongs(final int contorSongs) {
        this.contorSongs = contorSongs;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(final int followers) {
        this.followers = followers;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(final boolean empty) {
        this.empty = empty;
    }

    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(final int playlistId) {
        this.playlistId = playlistId;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(final boolean visibility) {
        this.visibility = visibility;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(final String playlistName) {
        this.playlistName = playlistName;
    }

    public ArrayList<SongInput> getPlaylistSongs() {
        return playlistSongs;
    }

    public void setPlaylistSongs(final ArrayList<SongInput> playlistSongs) {
        this.playlistSongs = playlistSongs;
    }
}
