package main;

import fileio.input.PodcastInput;
import fileio.input.SongInput;

import java.util.ArrayList;

public final class Current {
    private  SongInput currentSong;
    private PodcastInput currentPodcast;
    private  Playlist currentPlaylist;

    private ArrayList<SongInput> matchingSongsSearch;
    private ArrayList<PodcastInput> matchingPodcastsSearch;
    private  ArrayList<Playlist> matchingPlaylistsSearch;
    private int whatIsOn;
    private boolean plays;
    private boolean isLoaded;
    private boolean shuffle;
    private int timestampAnt;
    private String antCommand;
    private int remainedTime;

    public Playlist getCurrentPlaylist() {
        return currentPlaylist;
    }

    public void setCurrentPlaylist(Playlist currentPlaylist) {
        this.currentPlaylist = currentPlaylist;
    }

    public boolean isLoaded() {
        return isLoaded;
    }

    public void setLoaded(boolean loaded) {
        isLoaded = loaded;
    }

    public int getRemainedTime() {
        return remainedTime;
    }

    public void setRemainedTime(int remainedTime) {
        this.remainedTime = remainedTime;
    }

    public String getAntCommand() {
        return antCommand;
    }

    public void setAntCommand(String antCommand) {
        this.antCommand = antCommand;
    }

    public ArrayList<SongInput> getMatchingSongsSearch() {
        return matchingSongsSearch;
    }

    public void setMatchingSongsSearch(ArrayList<SongInput> matchingSongsSearch) {
        this.matchingSongsSearch = matchingSongsSearch;
    }

    public ArrayList<PodcastInput> getMatchingPodcastsSearch() {
        return matchingPodcastsSearch;
    }

    public void setMatchingPodcastsSearch(ArrayList<PodcastInput> matchingPodcastsSearch) {
        this.matchingPodcastsSearch = matchingPodcastsSearch;
    }

    public ArrayList<Playlist> getMatchingPlaylistsSearch() {
        return matchingPlaylistsSearch;
    }

    public void setMatchingPlaylistsSearch(ArrayList<Playlist> matchingPlaylistsSearch) {
        this.matchingPlaylistsSearch = matchingPlaylistsSearch;
    }

    public int getTimestampAnt() {
        return timestampAnt;
    }

    public void setTimestampAnt(int timestampAnt) {
        this.timestampAnt = timestampAnt;
    }

    public SongInput getCurrentSong() {
        return currentSong;
    }

    public void setCurrentSong(SongInput currentSong) {
        this.currentSong = currentSong;
    }

    public PodcastInput getCurrentPodcast() {
        return currentPodcast;
    }

    public void setCurrentPodcast(PodcastInput currentPodcast) {
        this.currentPodcast = currentPodcast;
    }

    public int getWhatIsOn() {
        return whatIsOn;
    }

    public void setWhatIsOn(int whatIsOn) {
        this.whatIsOn = whatIsOn;
    }

    public boolean isPlays() {
        return plays;
    }

    public void setPlays(boolean plays) {
        this.plays = plays;
    }

    public boolean isShuffle() {
        return shuffle;
    }

    public void setShuffle(boolean shuffle) {
        this.shuffle = shuffle;
    }
}
