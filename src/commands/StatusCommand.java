package commands;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.input.SongInput;
import main.Current;

public final class StatusCommand extends Command {


    public void displayStatus(StatusCommand statusCommand, Current current,
                              ObjectMapper objectMapper, ArrayNode outputs) {

        ObjectNode statusResults = objectMapper.createObjectNode();
        statusResults .put("command", "status");
        statusResults .put("user", statusCommand.getUsername());
        statusResults .put("timestamp", statusCommand.getTimestamp());

        if (current.getWhatIsOn() == 1) {

            SongInput currentSong = current.getCurrentSong();

            if (!current.isPlays()) {
                current.setRemainedTime(current.getRemainedTime() - statusCommand.getTimestamp() + current.getTimestampAnt());
            }

            ObjectNode statusFields = JsonNodeFactory.instance.objectNode();

            if (current.getRemainedTime() > 0) {
                statusFields.put("name", currentSong.getName());
                statusFields.put("remainedTime", current.getRemainedTime());
                statusFields.put("repeat", "No Repeat");
                statusFields.put("shuffle", current.isShuffle());
                statusFields.put("paused", current.isPlays());
            } else {
                current.setRemainedTime(0);
                current.setPlays(true);
                current.setCurrentSong(null);
                statusFields.put("name", "");
                statusFields.put("remainedTime", current.getRemainedTime());
                statusFields.put("repeat", "No Repeat");
                statusFields.put("shuffle", current.isShuffle());
                statusFields.put("paused", current.isPlays());
            }

            statusResults.set("stats", statusFields);

        }
        outputs.add(statusResults);
        current.setAntCommand("status");
        current.setTimestampAnt(statusCommand.getTimestamp());

    }

}
