package commands;

public class SwitchVisibilityCommand extends Command{
    private int playlistId;

    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    public void execute() {
        if (this instanceof SwitchVisibilityCommand) {
            SwitchVisibilityCommand switchVisibilityCommand = (SwitchVisibilityCommand) this;
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", PlaylisId: " + playlistId;
    }
}
