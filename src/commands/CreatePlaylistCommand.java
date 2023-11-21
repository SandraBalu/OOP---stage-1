package commands;

public final class CreatePlaylistCommand extends Command {
    private String playlistName;
    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(final String playlistName) {
        this.playlistName = playlistName;
    }

}
