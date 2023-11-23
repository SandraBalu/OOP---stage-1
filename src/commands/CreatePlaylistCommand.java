package commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import main.Current;
import main.Playlist;

import java.util.ArrayList;

public final class CreatePlaylistCommand extends Command {
    private String playlistName;
    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(final String playlistName) {
        this.playlistName = playlistName;
    }


    public void executeCreatePlaylist(CreatePlaylistCommand createPlaylistCommand,
                                      ArrayList<Playlist> playlists,
                                      Current current, ObjectMapper objectMapper,
                                      ArrayNode outputs) {


        if (playlists.size() == 0) {
            //first playlis
            Playlist newPlaylist = new Playlist();
            newPlaylist.setPlaylistName(createPlaylistCommand.getPlaylistName());
            newPlaylist.setUsername(createPlaylistCommand.getUsername());
            newPlaylist.setPlaylistId(1);
            newPlaylist.setVisibility(true);
            newPlaylist.setEmpty(true);
            newPlaylist.setFollowers(0);
            playlists.add(newPlaylist);
        } else {
            for (Playlist playlist: playlists) {
                if (playlist.getPlaylistName().equals(createPlaylistCommand.playlistName)) {
                    ObjectNode createPlaylistResult = objectMapper.createObjectNode();
                    createPlaylistResult.put("command", "createPlaylist");
                    createPlaylistResult.put("user", createPlaylistCommand.getUsername());
                    createPlaylistResult.put("timestamp", createPlaylistCommand.getTimestamp());
                    createPlaylistResult.put("message", "A playlist with the same name already exists.");

                    outputs.add(createPlaylistResult);
                    return;
                }
            }


                Playlist newPlaylist = new Playlist();
                newPlaylist.setPlaylistName(createPlaylistCommand.getPlaylistName());
                newPlaylist.setUsername(createPlaylistCommand.getUsername());
                newPlaylist.setPlaylistId(playlists.size());
                newPlaylist.setVisibility(true);
                newPlaylist.setEmpty(true);
                newPlaylist.setFollowers(0);
                playlists.add(newPlaylist);

        }

        ObjectNode createPlaylistResult = objectMapper.createObjectNode();
        createPlaylistResult.put("command", "createPlaylist");
        createPlaylistResult.put("user", createPlaylistCommand.getUsername());
        createPlaylistResult.put("timestamp", createPlaylistCommand.getTimestamp());
        createPlaylistResult.put("message", "Playlist created successfully.");

        outputs.add(createPlaylistResult);

        current.setAntCommand("createPlaylist");
        current.setTimestampAnt(createPlaylistCommand.getTimestamp());

    }

}
