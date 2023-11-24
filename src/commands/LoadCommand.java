package commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.input.ExtendedPodcast;
import fileio.input.LibraryInput;
import fileio.input.PodcastInput;
import fileio.input.SongInput;
import main.Current;
import main.Playlist;

import java.util.ArrayList;


public final class LoadCommand extends Command {
    private static final int MAGIC_NUMBER = 3;


    /**
     * display message for load
     */
    public void  loadDisplay(final SongInput song, final LoadCommand loadCommand,
                             final String antCommand, final ObjectMapper objectMapper,
                             final  ArrayNode outputs, final Current current) {

        if (antCommand.equals("select")) {

            ObjectNode selectedResult = objectMapper.createObjectNode();
            selectedResult.put("command", "load");
            selectedResult.put("user", loadCommand.getUsername());
            selectedResult.put("timestamp", loadCommand.getTimestamp());
            selectedResult.put("message", "Playback loaded successfully.");
            outputs.add(selectedResult);
            current.setTimestampAnt(loadCommand.getTimestamp());
            current.setRepeat("No Repeat");

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
     * load podcast
     */

    private void loadPodcast(final ArrayList<ExtendedPodcast> loadedPodcasts,
                             final Current current) {

        PodcastInput podcastToLoad = current.getCurrentPodcast();

        if (loadedPodcasts != null) {
           // at least a podcast was loaded before
            //verify if the selected podcast was loaded before
            for (ExtendedPodcast podcast : loadedPodcasts) {
                if (podcast.getPodcast().getName().equals(current.getCurrentPodcast().getName())) {
                    //the current podcast was loaded before
                    current.setCurrentExtendedPodcast(podcast);
                    return;
                }
            }
        }
        // load the first podcast or the current podcast wasn't loaded
        ExtendedPodcast firstPodcast = new ExtendedPodcast();
        firstPodcast.setPodcast(podcastToLoad);
        firstPodcast.setRemainingDuration(podcastToLoad.getEpisodes().get(0).getDuration());
        firstPodcast.setLastEpisode(0);
        firstPodcast.setLastEpisodeSecond(0);

        current.setCurrentExtendedPodcast(firstPodcast);
        current.setRepeatMode(0);
        loadedPodcasts.add(firstPodcast);
    }

    /**
     * execute load
     */

    public void executeLoad(final LoadCommand loadCommand, final Current current,
                            final LibraryInput library,
                            final ArrayList<ExtendedPodcast> loadedPodcasts,
                            final ObjectMapper objectMapper, final ArrayNode outputs) {


        loadCommand.loadDisplay(current.getCurrentSong(), loadCommand, current.getAntCommand(),
                objectMapper, outputs, current);

            if (current.getWhatIsOn() == 2 && current.getAntCommand().equals("select")) {
                loadCommand.loadPodcast(loadedPodcasts, current);
            }

            if (current.getWhatIsOn() == MAGIC_NUMBER) {
               if (current.getCurrentPlaylist() != null) {
                   if (current.getCurrentPlaylist().getPlaylistSongs() != null) {
                       current.setMatchingSongsSearch(current.
                                getCurrentPlaylist().getPlaylistSongs());
                       Playlist playlist = current.getCurrentPlaylist();
                       playlist.setContorSongs(0);
                       current.setCurrentPlaylist(playlist);
                       current.setRepeatMode(0);
                   }
               }
            }

            current.setLoaded(true);
            current.setAntCommand("load");
//            current.setRepeatMode(0);
//            current.setTimestampAnt(loadCommand.getTimestamp());
            current.setPlays(false);

    }

}
