package commands;

public final class SwitchVisibilityCommand extends Command {
    private int playlistId;

    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(final int playlistId) {
        this.playlistId = playlistId;
    }

}
