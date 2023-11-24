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
    public void executeRepeat(final RepeatCommand repeatCommand, final Current curent,
                               final ObjectMapper objectMapper, final ArrayNode outputs) {

        ObjectNode repeatdResult = objectMapper.createObjectNode();
        if (curent.getRepeatMode() == 0) {
            curent.setRepeatMode(curent.getRepeatMode() + 1);
            curent.setAntRepeatMode(0);

            if (curent.getWhatIsOn() == MAGIC_NUMBER) {
                curent.setRepeat("Repeat All");
            } else {
                curent.setRepeat("Repeat Once");
            }
            repeatdResult.put("command", "repeat");
            repeatdResult.put("user", repeatCommand.getUsername());
            repeatdResult.put("timestamp", repeatCommand.getTimestamp());
            repeatdResult.put("message", "Repeat mode changed to "
                    + curent.getRepeat().toLowerCase() + ".");

            outputs.add(repeatdResult);
            return;

        }

        if (curent.getRepeatMode() == 1) {
            curent.setRepeatMode(curent.getRepeatMode() + 1);
            curent.setAntRepeatMode(1);

            if (curent.getWhatIsOn() == MAGIC_NUMBER) {
                curent.setRepeat("Repeat Current Song");
            } else {
                curent.setRepeat("Repeat Infinite");
            }
            repeatdResult.put("command", "repeat");
            repeatdResult.put("user", repeatCommand.getUsername());
            repeatdResult.put("timestamp", repeatCommand.getTimestamp());
            repeatdResult.put("message", "Repeat mode changed to "
                    + curent.getRepeat().toLowerCase() + ".");

            outputs.add(repeatdResult);
            return;

        }


        if (curent.getRepeatMode() == 2) {
            curent.setRepeatMode(0);
            curent.setAntRepeatMode(2);
            curent.setRepeat("No Repeat");
            repeatdResult.put("command", "repeat");
            repeatdResult.put("user", repeatCommand.getUsername());
            repeatdResult.put("timestamp", repeatCommand.getTimestamp());
            repeatdResult.put("message", "Repeat mode changed to "
                    + curent.getRepeat().toLowerCase() + ".");

            outputs.add(repeatdResult);
            return;

        }

        repeatdResult.put("command", "repeat");
        repeatdResult.put("user", repeatCommand.getUsername());
        repeatdResult.put("timestamp", repeatCommand.getTimestamp());
        repeatdResult.put("message", "Repeat mode changed to "
                + curent.getRepeat().toLowerCase() + ".");

        outputs.add(repeatdResult);
    }

}
