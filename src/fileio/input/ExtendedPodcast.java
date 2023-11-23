package fileio.input;

import java.util.ArrayList;

public class ExtendedPodcast {
    PodcastInput podcast;
    private int lastEpisode;
    private int lastEpisodeSecond;
    private int remainingDuration;

    public int getRemainingDuration() {
        return remainingDuration;
    }

    public void setRemainingDuration(int remainingDuration) {
        this.remainingDuration = remainingDuration;
    }

    public PodcastInput getPodcast() {
        return podcast;
    }

    public void setPodcast(PodcastInput podcast) {
        this.podcast = podcast;
    }

    public int getLastEpisode() {
        return lastEpisode;
    }

    public void setLastEpisode(int lastEpisode) {
        this.lastEpisode = lastEpisode;
    }

    public int getLastEpisodeSecond() {
        return lastEpisodeSecond;
    }

    public void setLastEpisodeSecond(int lastEpisodeSecond) {
        this.lastEpisodeSecond = lastEpisodeSecond;
    }
}
