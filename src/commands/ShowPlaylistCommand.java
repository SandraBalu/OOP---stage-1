package commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.input.SongInput;
import main.Playlist;

import java.util.ArrayList;

public final class ShowPlaylistCommand extends Command {

    /**
     * execute showPlaylistsCommand
     */
    public void displayPlaylists(final ShowPlaylistCommand showPlaylistCommand,
                                 final ArrayList<Playlist> playlists,
                                 final  ObjectMapper objectMapper, final ArrayNode outputs) {

        ObjectNode showPlaylistsResults = objectMapper.createObjectNode();
        showPlaylistsResults.put("command", "showPlaylists");
        showPlaylistsResults.put("user", showPlaylistCommand.getUsername());
        showPlaylistsResults.put("timestamp", showPlaylistCommand.getTimestamp());

        if (playlists != null) {
            ArrayNode playlistArrayNode = JsonNodeFactory.instance.arrayNode();

            for (Playlist playlist : playlists) {
                if (playlist.getUsername().equals(showPlaylistCommand.getUsername())) {

                    ObjectNode playlistNode = JsonNodeFactory.instance.objectNode();
                    playlistNode.put("name", playlist.getPlaylistName());

                    ArrayNode songArrayNode = playlistNode.putArray("songs");
                    if (playlist.getPlaylistSongs() != null) {
                        ArrayList<SongInput> songs = playlist.getPlaylistSongs();
                        for (SongInput song : songs) {
                            if (song != null) {
                                songArrayNode.add(song.getName());
                            }
                        }
                    }

                    if (playlist.isVisibility()) {
                        playlistNode.put("visibility", "public");
                    } else {
                        playlistNode.put("visibility", "private");
                    }

                    playlistNode.put("followers", playlist.getFollowers());
                    playlistArrayNode.add(playlistNode);
                }
            }

            showPlaylistsResults.set("result", playlistArrayNode);
        }

        outputs.add(showPlaylistsResults);
    }


}
