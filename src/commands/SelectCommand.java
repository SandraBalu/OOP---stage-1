package commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.input.LibraryInput;
import fileio.input.PodcastInput;
import fileio.input.SongInput;
import main.Current;
import main.Playlist;

import java.util.ArrayList;

public final class SelectCommand extends Command {
    private int itemNumber;
    private static final int MAGIC_NUMBER = 3;
    public int getItemNumber() {
        return itemNumber;
    }
    public void setItemNumber(final int itemNumber) {
        this.itemNumber = itemNumber;
    }

    /**
     * methode used to execute select command
     */
    public String generateSelect(final Current current, final SelectCommand selectCommand) {
        if (current.getMatchingSongsSearch() == null && current.getWhatIsOn() == 1) {
            return "Please conduct a search before making a selection.";
        }
        if (current.getMatchingPodcastsSearch() == null && current.getWhatIsOn() == 2) {
            return "Please conduct a search before making a selection.";
        }

        if (current.getMatchingPlaylistsSearch() == null && current.getWhatIsOn() == MAGIC_NUMBER) {
            return "Please conduct a search before making a selection.";
        }

        //select a song
        if (current.getMatchingSongsSearch() != null && current.getWhatIsOn() == 1) {
            int item = selectCommand.getItemNumber();
            if (item > current.getMatchingSongsSearch().size()) {
                return "The selected ID is too high.";

            }

            String selectedSuccessfully;
            SongInput song = current.getMatchingSongsSearch().get(--item);
            current.setCurrentSong(song);
            current.setRemainedTime(song.getDuration());
            selectedSuccessfully = "Successfully selected " + song.getName() + ".";
            return selectedSuccessfully;
        }

        //select a playlist
        if (current.getMatchingPlaylistsSearch() != null && current.getWhatIsOn() == MAGIC_NUMBER) {
            int item = selectCommand.getItemNumber();
            if (item > current.getMatchingPlaylistsSearch().size()) {
                return "The selected ID is too high.";
            }
            String selectedSuccessfully;
            Playlist playlist = current.getMatchingPlaylistsSearch().get(--item);
            current.setCurrentPlaylist(playlist);
            current.setRemainedTime(0);
            selectedSuccessfully = "Successfully selected " + playlist.getPlaylistName() + ".";
            return selectedSuccessfully;
        }

        // select a podcast
        if (current.getMatchingPodcastsSearch() != null && current.getWhatIsOn() == 2) {
            int item = selectCommand.getItemNumber();
            if (item > current.getMatchingPodcastsSearch().size()) {
                return "The selected ID is too high.";
            }

            String selectedSuccessfully;
            PodcastInput podcast = current.getMatchingPodcastsSearch().get(--item);
            current.setCurrentPodcast(podcast);
            selectedSuccessfully = "Successfully selected " + podcast.getName() + ".";
            return selectedSuccessfully;
        }

        return null;
    }

    /**
     * display selection message
     */

    public void displaySelect(final SelectCommand selectCommand,
                               final ArrayList<SongInput> matchingSongs,
                               final ArrayList<PodcastInput> matchingPodcasts,
                                final Current current,
                               final ObjectMapper objectMapper, final ArrayNode outputs) {

        String select = selectCommand.generateSelect(current, selectCommand);
        ObjectNode selectedResult = objectMapper.createObjectNode();

        selectedResult.put("command", "select");
        selectedResult.put("user", selectCommand.getUsername());
        selectedResult.put("timestamp", selectCommand.getTimestamp());
        selectedResult.put("message", select);

        outputs.add(selectedResult);
    }

    /**
     * execute select
     */
    public void executeSelect(final SelectCommand selectCommand, final Current current,
                              final LibraryInput library,
                              final ObjectMapper objectMapper, final ArrayNode outputs) {

        selectCommand.displaySelect(selectCommand, current.getMatchingSongsSearch(),
                current.getMatchingPodcastsSearch(), current, objectMapper, outputs);

        current.setTimestampAnt(selectCommand.getTimestamp());
        current.setAntCommand("select");
//        current.setRepeatMode(0);
    }

}
