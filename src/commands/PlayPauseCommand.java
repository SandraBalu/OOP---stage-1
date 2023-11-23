package commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.input.ExtendedPodcast;
import fileio.input.LibraryInput;
import main.Current;

public final class PlayPauseCommand extends Command {

    public void executePlayPause (PlayPauseCommand playPauseCommand, Current current, LibraryInput library,
                                  ObjectMapper objectMapper, ArrayNode outputs) {

        if (current.getCurrentSong() != null || current.getCurrentPodcast() != null) {

            ObjectNode playPauseResults = objectMapper.createObjectNode();
            playPauseResults .put("command", "playPause");
            playPauseResults .put("user", playPauseCommand.getUsername());
            playPauseResults .put("timestamp", playPauseCommand.getTimestamp());

            if (!current.isPlays()) {
                playPauseResults .put("timestamp", playPauseCommand.getTimestamp());
                playPauseResults .put("message", "Playback paused successfully.");
                current.setRemainedTime(current.getRemainedTime() - playPauseCommand.getTimestamp() + current.getTimestampAnt());

                if (current.getWhatIsOn() == 2) {
                    if (current.getRemainedTime() <= 0) {
                        ExtendedPodcast update = current.getCurrentExtendedPodcast();
                        if (update.getLastEpisode() <= update.getPodcast().getEpisodes().size()) {
                            update.setLastEpisode(update.getLastEpisode() + 1);
                            update.setRemainingDuration(update.getPodcast().getEpisodes().get(update.getLastEpisode()).getDuration() + current.getRemainedTime());
                            update.setLastEpisodeSecond(-current.getRemainedTime());
                            current.setCurrentExtendedPodcast(update);
                            current.setRemainedTime(update.getRemainingDuration());
                        }

                    } else {
                        ExtendedPodcast update = current.getCurrentExtendedPodcast();
                        update.setRemainingDuration(current.getRemainedTime());
                        current.setCurrentExtendedPodcast(update);
                    }
                }

            } else {
                playPauseResults .put("message", "Playback resumed successfully.");

            }

            outputs.add(playPauseResults);
            current.setPlays(!current.isPlays());
            current.setTimestampAnt(playPauseCommand.getTimestamp());
            current.setAntCommand(playPauseCommand.getCommand());

        } else {
            ObjectNode playPauseResults = objectMapper.createObjectNode();
            playPauseResults .put("command", "playPause");
            playPauseResults .put("user", playPauseCommand.getUsername());
            playPauseResults .put("timestamp", playPauseCommand.getTimestamp());
            playPauseResults .put("message", "Please load a source before attempting to pause or resume playback");
            outputs.add(playPauseResults );
        }

    }

}
