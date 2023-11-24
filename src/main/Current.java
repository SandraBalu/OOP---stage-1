package main;

import fileio.input.ExtendedPodcast;
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

    private ExtendedPodcast currentExtendedPodcast;

    public ExtendedPodcast getCurrentExtendedPodcast() {
        return currentExtendedPodcast;
    }

    public void setCurrentExtendedPodcast(final ExtendedPodcast currentExtendedPodcast) {
        this.currentExtendedPodcast = currentExtendedPodcast;
    }

    public Playlist getCurrentPlaylist() {
        return currentPlaylist;
    }

    public void setCurrentPlaylist(final Playlist currentPlaylist) {
        this.currentPlaylist = currentPlaylist;
    }

    public boolean isLoaded() {
        return isLoaded;
    }

    public void setLoaded(final boolean loaded) {
        isLoaded = loaded;
    }

    public int getRemainedTime() {
        return remainedTime;
    }

    public void setRemainedTime(final int remainedTime) {
        this.remainedTime = remainedTime;
    }

    public String getAntCommand() {
        return antCommand;
    }

    public void setAntCommand(final String antCommand) {
        this.antCommand = antCommand;
    }

    public ArrayList<SongInput> getMatchingSongsSearch() {
        return matchingSongsSearch;
    }

    public void setMatchingSongsSearch(final ArrayList<SongInput> matchingSongsSearch) {
        this.matchingSongsSearch = matchingSongsSearch;
    }

    public ArrayList<PodcastInput> getMatchingPodcastsSearch() {
        return matchingPodcastsSearch;
    }

    public void setMatchingPodcastsSearch(final ArrayList<PodcastInput> matchingPodcastsSearch) {
        this.matchingPodcastsSearch = matchingPodcastsSearch;
    }

    public ArrayList<Playlist> getMatchingPlaylistsSearch() {
        return matchingPlaylistsSearch;
    }

    public void setMatchingPlaylistsSearch(final ArrayList<Playlist> matchingPlaylistsSearch) {
        this.matchingPlaylistsSearch = matchingPlaylistsSearch;
    }
    public int getTimestampAnt() {
        return timestampAnt;
    }

    public void setTimestampAnt(final int timestampAnt) {
        this.timestampAnt = timestampAnt;
    }

    public SongInput getCurrentSong() {
        return currentSong;
    }

    public void setCurrentSong(final SongInput currentSong) {
        this.currentSong = currentSong;
    }

    public PodcastInput getCurrentPodcast() {
        return currentPodcast;
    }

    public void setCurrentPodcast(final PodcastInput currentPodcast) {
        this.currentPodcast = currentPodcast;
    }

    public int getWhatIsOn() {
        return whatIsOn;
    }

    public void setWhatIsOn(final int whatIsOn) {
        this.whatIsOn = whatIsOn;
    }

    public boolean isPlays() {
        return plays;
    }

    public void setPlays(final boolean plays) {
        this.plays = plays;
    }

    public boolean isShuffle() {
        return shuffle;
    }

    public void setShuffle(final boolean shuffle) {
        this.shuffle = shuffle;
    }
}
