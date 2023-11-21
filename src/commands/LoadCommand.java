package commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.input.SongInput;

public final class LoadCommand extends Command {

    public void  loadExecute(final SongInput song, final LoadCommand loadCommand,
                             final String antCommand, final ObjectMapper objectMapper,
                             final  ArrayNode outputs) {

//        ObjectMapper objectMapper = new ObjectMapper();
//        ArrayNode outputs = objectMapper.createArrayNode();
        System.out.println(antCommand);

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

}
