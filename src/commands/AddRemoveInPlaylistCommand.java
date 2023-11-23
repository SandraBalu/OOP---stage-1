package commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.input.SongInput;
import main.Current;
import main.Playlist;

import java.awt.*;
import java.util.ArrayList;

public final class AddRemoveInPlaylistCommand extends Command {
    private int playlistId;

    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(final int playlistId) {
        this.playlistId = playlistId;
    }

    public final void executeAddRemoveInPlaylist(AddRemoveInPlaylistCommand addRemoveInPlaylistCommand,
                                                 Current current, ArrayList<Playlist> playlists,
                                                 ObjectMapper objectMapper, ArrayNode outputs) {

        //a source was loaded
        if (current.isLoaded()) {

            if (current.getWhatIsOn() == 1) {

                //current source is a song
                if (addRemoveInPlaylistCommand.playlistId > playlists.size()) {
                    // playlistId does not exists
                    ObjectNode addRemoveResult = objectMapper.createObjectNode();
                    addRemoveResult.put("command", "addRemoveInPlaylist");
                    addRemoveResult.put("user", addRemoveInPlaylistCommand.getUsername());
                    addRemoveResult.put("timestamp", addRemoveInPlaylistCommand.getTimestamp());
                    addRemoveResult.put("message", "The specified playlist does not exist.");
                    outputs.add(addRemoveResult);
                    return;

                } else {
                    // add in playlist
                    Playlist playlistToExecute = playlists.remove(addRemoveInPlaylistCommand.getPlaylistId() - 1);
                    SongInput song = current.getCurrentSong();

                    if (!playlistToExecute.isEmpty()) {

                        ArrayList<SongInput> playlistSongs = playlistToExecute.getPlaylistSongs();
                        if (playlistSongs.contains(song)) {
                            playlistSongs.remove(song);
                            playlistToExecute.setPlaylistSongs(playlistSongs);
                            playlists.add(addRemoveInPlaylistCommand.getPlaylistId() - 1, playlistToExecute);
                            ObjectNode addRemoveResult = objectMapper.createObjectNode();
                            addRemoveResult.put("command", "addRemoveInPlaylist");
                            addRemoveResult.put("user", addRemoveInPlaylistCommand.getUsername());
                            addRemoveResult.put("timestamp", addRemoveInPlaylistCommand.getTimestamp());
                            addRemoveResult.put("message", "Successfully removed from playlist.");
                            outputs.add(addRemoveResult);
                            return;
                        } else {
                            playlistSongs.add(song);
                            playlistToExecute.setPlaylistSongs(playlistSongs);
                            playlists.add(addRemoveInPlaylistCommand.getPlaylistId() - 1, playlistToExecute);
                            ObjectNode addRemoveResult = objectMapper.createObjectNode();
                            addRemoveResult.put("command", "addRemoveInPlaylist");
                            addRemoveResult.put("user", addRemoveInPlaylistCommand.getUsername());
                            addRemoveResult.put("timestamp", addRemoveInPlaylistCommand.getTimestamp());
                            addRemoveResult.put("message", "Successfully added to playlist.");
                            outputs.add(addRemoveResult);
                            return;
                        }

                    } else {
                        // add first song in playlist
                        ArrayList<SongInput> songs = new ArrayList<>();
                        songs.add(song);
                        playlistToExecute.setPlaylistSongs(songs);
                        playlistToExecute.setEmpty(false);
                        playlists.add(addRemoveInPlaylistCommand.getPlaylistId() - 1, playlistToExecute);
                        ObjectNode addRemoveResult = objectMapper.createObjectNode();
                        addRemoveResult.put("command", "addRemoveInPlaylist");
                        addRemoveResult.put("user", addRemoveInPlaylistCommand.getUsername());
                        addRemoveResult.put("timestamp", addRemoveInPlaylistCommand.getTimestamp());
                        addRemoveResult.put("message", "Successfully added to playlist.");
                        outputs.add(addRemoveResult);
                        return;
                    }
                }

            } else {
                ObjectNode addRemoveResult = objectMapper.createObjectNode();
                addRemoveResult.put("command", "addRemoveInPlaylist");
                addRemoveResult.put("user", addRemoveInPlaylistCommand.getUsername());
                addRemoveResult.put("timestamp", addRemoveInPlaylistCommand.getTimestamp());
                addRemoveResult.put("message", "The loaded source is not a song.");
                outputs.add(addRemoveResult);
            }

        } else {
            ObjectNode addRemoveResult = objectMapper.createObjectNode();
            addRemoveResult.put("command", "addRemoveInPlaylist");
            addRemoveResult.put("user", addRemoveInPlaylistCommand.getUsername());
            addRemoveResult.put("timestamp", addRemoveInPlaylistCommand.getTimestamp());
            addRemoveResult.put("message", "Please load a source before adding to or removing from the playlist.");

            outputs.add(addRemoveResult);
        }
    }

}
