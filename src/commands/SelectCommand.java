package commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.input.LibraryInput;
import fileio.input.PodcastInput;
import fileio.input.SongInput;
import main.Current;

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
                                Current current, final SelectCommand selectCommand) {
        if (matchingSongs == null && matchingPodcasts == null) {
            return "Please conduct a search before making a selection.";
        }

        if (matchingSongs != null && current.getWhatIsOn() == 1) {
            int item = selectCommand.getItemNumber();
            if (item > matchingSongs.size()) {
                return "The selected ID is too high.";
            }

            String selectedSuccessfully;
            SongInput song = matchingSongs.get(--item);
            current.setCurrentSong(song);
            current.setRemainedTime(song.getDuration());
            selectedSuccessfully = "Successfully selected " + song.getName() + ".";
            return selectedSuccessfully;
        }

        if (matchingPodcasts != null && current.getWhatIsOn() == 2) {
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
     * display selection message
     */

    public void displaySelecet(final SelectCommand selectCommand,
                               final ArrayList<SongInput> matchingSongs,
                               final ArrayList<PodcastInput> matchingPodcasts,
                               Current current,
                               final ObjectMapper objectMapper, final ArrayNode outputs) {

        String select = selectCommand.generateSelect(matchingSongs, matchingPodcasts, current, selectCommand);
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
    public void executeSelect(SelectCommand selectCommand, Current current, LibraryInput library,
                              ObjectMapper objectMapper, ArrayNode outputs) {

        selectCommand.displaySelecet(selectCommand, current.getMatchingSongsSearch(),
                current.getMatchingPodcastsSearch(),current , objectMapper, outputs);


        current.setTimestampAnt(selectCommand.getTimestamp());
        current.setAntCommand("select");


    }

}
