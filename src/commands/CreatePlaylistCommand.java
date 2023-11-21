package commands;

import java.util.ArrayList;

public class CreatePlaylistCommand extends Command{
    private String playlistName;


    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }
    public void execute() {
        if (this instanceof CreatePlaylistCommand) {
            CreatePlaylistCommand createPlaylistCommand = (CreatePlaylistCommand) this;
        }
    }
    @Override
    public String toString() {
        return super.toString() + ", PlaylistName: " + playlistName;
    }
}
