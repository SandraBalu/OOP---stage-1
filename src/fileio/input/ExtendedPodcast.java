package fileio.input;

public final class ExtendedPodcast {
    private PodcastInput podcast;
    private int lastEpisode;
    private int lastEpisodeSecond;
    private int remainingDuration;

    public int getRemainingDuration() {
        return remainingDuration;
    }

    public void setRemainingDuration(final int remainingDuration) {
        this.remainingDuration = remainingDuration;
    }

    public PodcastInput getPodcast() {
        return podcast;
    }

    public void setPodcast(final PodcastInput podcast) {
        this.podcast = podcast;
    }

    public int getLastEpisode() {
        return lastEpisode;
    }

    public void setLastEpisode(final int lastEpisode) {
        this.lastEpisode = lastEpisode;
    }

    public int getLastEpisodeSecond() {
        return lastEpisodeSecond;
    }

    public void setLastEpisodeSecond(final int lastEpisodeSecond) {
        this.lastEpisodeSecond = lastEpisodeSecond;
    }
}
