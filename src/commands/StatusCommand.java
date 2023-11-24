package commands;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.input.ExtendedPodcast;
import fileio.input.SongInput;
import main.Current;
import main.Playlist;

import java.util.ArrayList;

public final class StatusCommand extends Command {

    private static final int MAGIC_NUMBER = 3;
    /**
     * execute status command
     */
    public void displayStatus(final StatusCommand statusCommand, final Current current,
                              final ObjectMapper objectMapper, final ArrayNode outputs) {

        ObjectNode statusResults = objectMapper.createObjectNode();
        statusResults.put("command", "status");
        statusResults.put("user", statusCommand.getUsername());
        statusResults.put("timestamp", statusCommand.getTimestamp());

        if (current.getWhatIsOn() == 1) {

            SongInput currentSong = current.getCurrentSong();

            if (!current.isPlays()) {
                current.setRemainedTime(current.getRemainedTime()
                        - statusCommand.getTimestamp() + current.getTimestampAnt());
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
                current.setRemainedTime(current.getRemainedTime()
                        - statusCommand.getTimestamp() + current.getTimestampAnt());
                ExtendedPodcast update = current.getCurrentExtendedPodcast();
                update.setRemainingDuration(current.getRemainedTime());
                current.setCurrentExtendedPodcast(update);
            }

            if (current.getRemainedTime() > 0) {

                statusFields.put("name", current.
                        getCurrentExtendedPodcast().getPodcast().getEpisodes().
                        get(current.getCurrentExtendedPodcast().getLastEpisode()).getName());
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

        } else if (current.getWhatIsOn() == MAGIC_NUMBER) {

            ObjectNode statusFields = JsonNodeFactory.instance.objectNode();
            if (current.getCurrentPlaylist() != null) {

                Playlist nowPlaylist = current.getCurrentPlaylist();
                if (nowPlaylist.getPlaylistSongs() != null) {

                    ArrayList<SongInput> songs = nowPlaylist.getPlaylistSongs();
                    if (nowPlaylist.getContorSongs() == 0) {

                        if (songs.get(0) != null) {
                            current.setRemainedTime(songs.get(0).getDuration());
                            current.setCurrentSong(songs.get(0));
                            nowPlaylist.setNumberOfSongs(nowPlaylist.
                                    getPlaylistSongs().size());
                        }
                        current.setCurrentPlaylist(nowPlaylist);
                    }

                    if (!current.isPlays()) {
                        current.setRemainedTime(current.getRemainedTime()
                                - statusCommand.getTimestamp() + current.getTimestampAnt());
                    }

                    if (current.getRemainedTime() > 0) {

                        statusFields.put("name", current.getCurrentSong().getName());
                        statusFields.put("remainedTime", current.getRemainedTime());
                        statusFields.put("repeat", "No Repeat");
                        statusFields.put("shuffle", current.isShuffle());
                        statusFields.put("paused", current.isPlays());
                        nowPlaylist.setContorSongs(nowPlaylist.getContorSongs() + 1);
                        current.setCurrentPlaylist(nowPlaylist);
                    } else {

                        if (nowPlaylist.getContorSongs() <= nowPlaylist.getNumberOfSongs() - 1) {


                            if (songs.get(nowPlaylist.getContorSongs()) != null) {

                                current.setRemainedTime(songs.get(nowPlaylist.getContorSongs()).
                                        getDuration() + current.getRemainedTime());
                                statusFields.put("name", songs.get(nowPlaylist.
                                        getContorSongs()).getName());
                                statusFields.put("remainedTime", current.getRemainedTime());
                                statusFields.put("repeat", "No Repeat");
                                statusFields.put("shuffle", current.isShuffle());
                                statusFields.put("paused", current.isPlays());
                                nowPlaylist.setContorSongs(nowPlaylist.getContorSongs() + 1);
                                current.setCurrentPlaylist(nowPlaylist);
                            }
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

                    }

                    statusResults.set("stats", statusFields);
                }
            }
        }
        outputs.add(statusResults);
        current.setAntCommand("status");
        current.setTimestampAnt(statusCommand.getTimestamp());

        }

    }

