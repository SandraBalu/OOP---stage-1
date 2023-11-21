package commands;

public class AddRemoveInPlaylistCommand extends Command {
    private int playlistId;

    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }
    public void execute() {
        if (this instanceof AddRemoveInPlaylistCommand) {
            AddRemoveInPlaylistCommand addRemoveInPlaylistCommand = (AddRemoveInPlaylistCommand) this;
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", PlaylisId: " + playlistId;
    }
}
