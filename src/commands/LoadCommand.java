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


    private void loadPodcast(ArrayList<ExtendedPodcast> loadedPodcasts, Current current) {

        PodcastInput podcastToLoad = current.getCurrentPodcast();

        if (loadedPodcasts != null) {
           // at least a podcast was loaded before
            //verify if the selected podcast was loaded before
            for(ExtendedPodcast podcast : loadedPodcasts) {
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
        loadedPodcasts.add(firstPodcast);
    }

    /**
     * execute load
     */

    public void executeLoad(LoadCommand loadCommand, Current current, LibraryInput library,
                            ArrayList<ExtendedPodcast> loadedPodcasts,
                            ObjectMapper objectMapper, ArrayNode outputs) {


        loadCommand.loadDisplay(current.getCurrentSong(), loadCommand, current.getAntCommand(),
                objectMapper, outputs, current);

            if(current.getWhatIsOn() == 2 && current.getAntCommand().equals("select")) {
                loadCommand.loadPodcast(loadedPodcasts, current);
            }

            if (current.getWhatIsOn() == 3) {
               if (current.getCurrentPlaylist() != null) {
                   if (current.getCurrentPlaylist().getPlaylistSongs() != null) {
                       current.setMatchingSongsSearch(current.getCurrentPlaylist().getPlaylistSongs());
                       Playlist playlist = current.getCurrentPlaylist();
                       playlist.setContorSongs(0);
                       current.setCurrentPlaylist(playlist);
                   }
               }
            }

            current.setLoaded(true);
            current.setAntCommand("load");
            current.setTimestampAnt(loadCommand.getTimestamp());
            current.setPlays(false);

    }

}
