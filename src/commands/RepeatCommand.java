package commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import main.Current;

public final class RepeatCommand extends Command {

    private static final int MAGIC_NUMBER = 3;

    /**
     * modify current repeat state
     */
    public void executeRepeat(final RepeatCommand repeatCommand, final Current current,
                               final ObjectMapper objectMapper, final ArrayNode outputs) {


        ObjectNode repeatdResult = objectMapper.createObjectNode();
        current.setTimestampAnt(repeatCommand.getTimestamp() - 1);

        if (current.getAntCommand().equals("search")){

            repeatdResult.put("command", "repeat");
            repeatdResult.put("user", repeatCommand.getUsername());
            repeatdResult.put("timestamp", repeatCommand.getTimestamp());
            repeatdResult.put("message", "Please load a source before setting the repeat status.");

            outputs.add(repeatdResult);
            return;
        }
        if (current.getRepeatMode() == 0) {
            current.setRepeatMode(current.getRepeatMode() + 1);
            current.setAntRepeatMode(0);

            if (current.getWhatIsOn() == MAGIC_NUMBER) {
                current.setRepeat("Repeat All");
            } else {
                current.setRepeat("Repeat Once");
            }
            repeatdResult.put("command", "repeat");
            repeatdResult.put("user", repeatCommand.getUsername());
            repeatdResult.put("timestamp", repeatCommand.getTimestamp());
            repeatdResult.put("message", "Repeat mode changed to "
                    + current.getRepeat().toLowerCase() + ".");

            outputs.add(repeatdResult);
            return;

        }

        if (current.getRepeatMode() == 1) {
            current.setRepeatMode(current.getRepeatMode() + 1);
            current.setAntRepeatMode(1);

            if (current.getWhatIsOn() == MAGIC_NUMBER) {
                current.setRepeat("Repeat Current Song");
            } else {
                current.setRepeat("Repeat Infinite");
            }
            repeatdResult.put("command", "repeat");
            repeatdResult.put("user", repeatCommand.getUsername());
            repeatdResult.put("timestamp", repeatCommand.getTimestamp());
            repeatdResult.put("message", "Repeat mode changed to "
                    + current.getRepeat().toLowerCase() + ".");

            outputs.add(repeatdResult);
            return;

        }

        if (current.getRepeatMode() == 2) {
            current.setRepeatMode(0);
            current.setAntRepeatMode(2);

            current.setRepeat("No Repeat");
            repeatdResult.put("command", "repeat");
            repeatdResult.put("user", repeatCommand.getUsername());
            repeatdResult.put("timestamp", repeatCommand.getTimestamp());
            repeatdResult.put("message", "Repeat mode changed to "
                    + current.getRepeat().toLowerCase() + ".");

            outputs.add(repeatdResult);
            return;

        }

        repeatdResult.put("command", "repeat");
        repeatdResult.put("user", repeatCommand.getUsername());
        repeatdResult.put("timestamp", repeatCommand.getTimestamp());
        repeatdResult.put("message", "Repeat mode changed to "
                + current.getRepeat().toLowerCase() + ".");

        outputs.add(repeatdResult);
    }

}
