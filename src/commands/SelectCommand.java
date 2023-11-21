package commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.input.PodcastInput;
import fileio.input.SongInput;

import java.util.ArrayList;

public final class SelectCommand extends Command {

    private int itemNumber;

    public int getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(final int itemNumber) {
        this.itemNumber = itemNumber;
    }

    /**
     * methode used to execute select command
     */
    public String generateSelect(final ArrayList<SongInput> matchingSongs,
                                final ArrayList<PodcastInput> matchingPodcasts,
                                final SelectCommand selectCommand, final int lastSearch) {
        if (matchingSongs == null && matchingPodcasts == null) {
            return "Please conduct a search before making a selection.";
        }

        if (matchingSongs != null && lastSearch == 1) {
            int item = selectCommand.getItemNumber();
            if (item > matchingSongs.size()) {
                return "The selected ID is too high.";
            }

            String selectedSuccessfully;
            SongInput song = matchingSongs.get(--item);
            selectedSuccessfully = "Successfully selected " + song.getName() + ".";
            return selectedSuccessfully;
        }

        if (matchingPodcasts != null && lastSearch == 2) {
            int item = selectCommand.getItemNumber();
            if (item > matchingPodcasts.size()) {
                return "The selected ID is too high.";
            }

            String selectedSuccessfully;
            PodcastInput podcast = matchingPodcasts.get(--item);
            selectedSuccessfully = "Successfully selected " + podcast.getName() + ".";
            return selectedSuccessfully;
        }

        return null;
    }

    /**
     * return selected song
     */
    public SongInput songSel(final ArrayList<SongInput> matchingSongs,
                             final SelectCommand selectCommand) {
        if (matchingSongs == null) {
            return null;
        }

        if (matchingSongs != null) {
            int item = selectCommand.getItemNumber();
            if (item > matchingSongs.size()) {
                return null;
            }

            String selectedSuccessfully;
            SongInput song = matchingSongs.get(--item);
            return song;
        }
        return null;
    }

    /**
     * return selected podcast
     */
    public PodcastInput podcastSel(final ArrayList<PodcastInput> matchingPodcasts,
                                   final SelectCommand selectCommand) {
        if (matchingPodcasts == null) {
            return null;
        }


        if (matchingPodcasts != null) {
            int item = selectCommand.getItemNumber();
            if (item > matchingPodcasts.size()) {
                return null;
            }

            String selectedSuccessfully;
            PodcastInput podcast = matchingPodcasts.get(--item);
            return podcast;
        }

        return null;
    }

    /**
     * display selection message
     */

    public void displaySelecet(final SelectCommand selectCommand,
                               final ArrayList<SongInput> matchingSongs,
                               final ArrayList<PodcastInput> matchingPodcasts,
                               final int lastSearch,
                               final ObjectMapper objectMapper, final ArrayNode outputs) {

        String select = selectCommand.generateSelect(matchingSongs, matchingPodcasts,
                selectCommand, lastSearch);
        ObjectNode selectedResult = objectMapper.createObjectNode();
        selectedResult.put("command", "select");
        selectedResult.put("user", selectCommand.getUsername());
        selectedResult.put("timestamp", selectCommand.getTimestamp());
        selectedResult.put("message", select);
        outputs.add(selectedResult);
    }

}
