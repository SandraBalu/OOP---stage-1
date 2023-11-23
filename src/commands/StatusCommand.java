package commands;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.input.EpisodeInput;
import fileio.input.ExtendedPodcast;
import fileio.input.PodcastInput;
import fileio.input.SongInput;
import main.Current;

public final class StatusCommand extends Command {


    public void displayStatus(StatusCommand statusCommand, Current current,
                              ObjectMapper objectMapper, ArrayNode outputs) {

        ObjectNode statusResults = objectMapper.createObjectNode();
        statusResults.put("command", "status");
        statusResults.put("user", statusCommand.getUsername());
        statusResults.put("timestamp", statusCommand.getTimestamp());

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

        } else if (current.getWhatIsOn() == 2) {


            ObjectNode statusFields = JsonNodeFactory.instance.objectNode();
            current.setRemainedTime(current.getCurrentExtendedPodcast().getRemainingDuration());

            if (!current.isPlays()) {
                current.setRemainedTime(current.getRemainedTime() - statusCommand.getTimestamp() + current.getTimestampAnt());
                ExtendedPodcast update = current.getCurrentExtendedPodcast();
                update.setRemainingDuration(current.getRemainedTime());
                current.setCurrentExtendedPodcast(update);
            }

            if (current.getRemainedTime() > 0) {

                statusFields.put("name", current.getCurrentExtendedPodcast().getPodcast().getEpisodes()
                                .get(current.getCurrentExtendedPodcast().getLastEpisode()).getName());
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

        } else if (current.getWhatIsOn() == 3 ) {

            ObjectNode statusFields = JsonNodeFactory.instance.objectNode();

            if (current.getMatchingSongsSearch() != null) {

                if (!current.isPlays()) {
                    current.setRemainedTime(current.getRemainedTime() - statusCommand.getTimestamp() + current.getTimestampAnt());
                }

                if (current.getRemainedTime() > 0) {

                    statusFields.put("name", current.getMatchingSongsSearch().size());
                    statusFields.put("remainedTime", current.getRemainedTime());
                    statusFields.put("repeat", "No Repeat");
                    statusFields.put("shuffle", current.isShuffle());
                    statusFields.put("paused", current.isPlays());
                } else {

                    //statusFields.put("name", current.getMatchingSongsSearch().get(0).getName());
                    statusFields.put("remainedTime", current.getRemainedTime());
                    statusFields.put("repeat", "No Repeat");
                    statusFields.put("shuffle", current.isShuffle());
                    statusFields.put("paused", current.isPlays());
                }

                statusResults.set("stats", statusFields);
            }
        }
        outputs.add(statusResults);
        current.setAntCommand("status");
        current.setTimestampAnt(statusCommand.getTimestamp());

        }

    }

