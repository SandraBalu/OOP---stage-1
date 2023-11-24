package commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.input.ExtendedUser;
import fileio.input.SongInput;

import java.util.ArrayList;

public final class ShowPreferredSongsCommand extends Command {

    /**
     * for current user, show liked audios
     */
    public void executeShowLikedSongs(final ShowPreferredSongsCommand showPreferredSongsCommand,
                                       final ArrayList<ExtendedUser> users,
                                       final ObjectMapper objectMapper, final ArrayNode outputs) {

        for (ExtendedUser user : users) {

            if (user.getUserName().equals(showPreferredSongsCommand.getUsername())) {

                ObjectNode preferredResult = objectMapper.createObjectNode();
                preferredResult.put("command", "showPreferredSongs");
                preferredResult.put("user", showPreferredSongsCommand.getUsername());
                preferredResult.put("timestamp", showPreferredSongsCommand.getTimestamp());
                ObjectNode likedSongs = JsonNodeFactory.instance.objectNode();

                if (user.getLikedSongs() != null) {
                    ArrayNode songArrayNode = likedSongs.putArray("songs");
                    for (SongInput song : user.getLikedSongs()) {
                        if (song != null) {
                            songArrayNode.add(song.getName());
                        }
                    }
                    preferredResult.set("result", songArrayNode);
                }
                outputs.add(preferredResult);
            }
        }

    }

}
