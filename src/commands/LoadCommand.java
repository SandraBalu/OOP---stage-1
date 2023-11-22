package commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.input.LibraryInput;
import fileio.input.SongInput;
import main.Current;

public final class LoadCommand extends Command {


    public void  loadDisplay(final SongInput song, final LoadCommand loadCommand,
                             final String antCommand, final ObjectMapper objectMapper,
                             final  ArrayNode outputs, Current current) {

        if (antCommand.equals("select")) {

            ObjectNode selectedResult = objectMapper.createObjectNode();
            selectedResult.put("command", "load");
            selectedResult.put("user", loadCommand.getUsername());
            selectedResult.put("timestamp", loadCommand.getTimestamp());
            selectedResult.put("message", "Playback loaded successfully.");
            outputs.add(selectedResult);

        } else {
            String error = "Please select a source before attempting to load.";
            ObjectNode selectedResult = objectMapper.createObjectNode();
            selectedResult.put("command", "load");
            selectedResult.put("user", loadCommand.getUsername());
            selectedResult.put("timestamp", loadCommand.getTimestamp());
            selectedResult.put("message", error);
            outputs.add(selectedResult);
        }
    }

    /**
     * execute load
     */

    public void executeLoad(LoadCommand loadCommand, Current current, LibraryInput library,
                            ObjectMapper objectMapper, ArrayNode outputs) {

        if (current.getWhatIsOn() == 1) {
            loadCommand.loadDisplay(current.getCurrentSong(), loadCommand, current.getAntCommand(),
                    objectMapper, outputs, current);
        }

            current.setAntCommand("load");
            current.setTimestampAnt(loadCommand.getTimestamp());
            current.setPlays(false);

    }

}
