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
    public void displayStatus(final StatusCommand statusCommand,
                              final Current current,
                              final ObjectMapper objectMapper, final ArrayNode outputs) {

        ObjectNode statusResults = objectMapper.createObjectNode();
        statusResults.put("command", "status");
        statusResults.put("user", statusCommand.getUsername());
        statusResults.put("timestamp", statusCommand.getTimestamp());

        if (current.getWhatIsOn() == 1) {
            ObjectNode statusFields = JsonNodeFactory.instance.objectNode();
         //statusFields.put("remainedTime ant", current.getRemainedTime());

            SongInput currentSong = current.getCurrentSong();
            if (!current.isPlays()) {
                current.setRemainedTime(current.getRemainedTime()
                        - statusCommand.getTimestamp() + current.getTimestampAnt());
            }

            if (current.getRemainedTime() > 0) {

                statusFields.put("name", currentSong.getName());
                statusFields.put("remainedTime", current.getRemainedTime());
                statusFields.put("repeat", current.getRepeat());
                statusFields.put("shuffle", current.isShuffle());
                statusFields.put("paused", current.isPlays());
                current.setCurrentSong(currentSong);
            } else {
                if (current.getRepeatMode() == 1) {
                    if (currentSong != null) {

                        current.setRemainedTime(currentSong.getDuration() + current.getRemainedTime());
                        current.setRepeat("No Repeat");
                        current.setRepeatMode(0);
                        statusFields.put("name", currentSong.getName());
                        statusFields.put("remainedTime", current.getRemainedTime());
                        statusFields.put("repeat", current.getRepeat());
                        statusFields.put("shuffle", current.isShuffle());
                        statusFields.put("paused", current.isPlays());
                    }
                } else if (current.getRepeatMode() == 2) {


                    while (current.getRemainedTime() < 0) {
                        current.setRemainedTime(currentSong.getDuration()
                                + current.getRemainedTime());
                    }


                    statusFields.put("name", currentSong.getName());
                    statusFields.put("remainedTime", current.getRemainedTime());
                    statusFields.put("repeat", current.getRepeat());
                    statusFields.put("shuffle", current.isShuffle());
                    statusFields.put("paused", current.isPlays());

                } else  if (current.getRepeatMode() == 0) {

                    if (current.getRemainedTime() > 0) {

                        statusFields.put("name", currentSong.getName());
                        statusFields.put("remainedTime", current.getRemainedTime());
                        statusFields.put("repeat", current.getRepeat());
                        statusFields.put("shuffle", current.isShuffle());
                        statusFields.put("paused", current.isPlays());

                        return;
                    }
                    current.setRemainedTime(0);
                    current.setPlays(true);
                    current.setCurrentSong(null);
                    statusFields.put("name", "");
                    statusFields.put("remainedTime", current.getRemainedTime());
                    statusFields.put("repeat", current.getRepeat());
                    statusFields.put("shuffle", current.isShuffle());
                    statusFields.put("paused", current.isPlays());
                }

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
                statusFields.put("repeat", current.getRepeat());
                statusFields.put("shuffle", current.isShuffle());
                statusFields.put("paused", current.isPlays());
            } else {
                current.setRemainedTime(0);
                current.setPlays(true);
                current.setCurrentSong(null);
                statusFields.put("name", "");
                statusFields.put("remainedTime", current.getRemainedTime());
                statusFields.put("repeat", current.getRepeat());
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
                    if (nowPlaylist.getContorSongs() == 0 && !current.
                            getAntCommand().equals("repeat")) {

                        if (songs.get(0) != null && current.getAntRepeatMode() != 2) {
                            current.setRemainedTime(songs.get(0).getDuration());
                            current.setCurrentSong(songs.get(0));
                            nowPlaylist.setTotalSongs(nowPlaylist.
                                    getPlaylistSongs().size());
                        }
                        if (current.getAntRepeatMode() == 2) {
                            current.setRemainedTime(current.getRemainedTime());
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
                        statusFields.put("repeat", current.getRepeat());
                        statusFields.put("shuffle", current.isShuffle());
                        statusFields.put("paused", current.isPlays());
                        nowPlaylist.setContorSongs(nowPlaylist.getContorSongs() + 1);
                        current.setCurrentPlaylist(nowPlaylist);
                    } else {

                        if (current.getRepeatMode() == 2) {

                            nowPlaylist.setContorSongs(nowPlaylist.getContorSongs() - 2);
                            statusFields.put("name", nowPlaylist.getPlaylistSongs().
                                    get(nowPlaylist.getContorSongs()).getName());
                            while (current.getRemainedTime() < 0) {
                                current.setRemainedTime(current.getRemainedTime()
                                        + nowPlaylist.getPlaylistSongs().
                                            get(nowPlaylist.getContorSongs()).getDuration());
                            }
                            statusFields.put("remainedTime", current.getRemainedTime());
                            statusFields.put("repeat", current.getRepeat());
                            statusFields.put("shuffle", current.isShuffle());
                            statusFields.put("paused", current.isPlays());
                        }


                        if (nowPlaylist.getContorSongs() <= nowPlaylist.getTotalSongs() - 1) {
                            if (songs.get(nowPlaylist.getContorSongs()) != null) {

                                if (current.getRepeatMode() == 0) {
                                    current.setRemainedTime(songs.get(nowPlaylist.getContorSongs()).
                                            getDuration() + current.getRemainedTime());

                                    if (current.getRemainedTime() > 0) {
                                        statusFields.put("name", songs.get(nowPlaylist.
                                                getContorSongs()).getName());
                                        statusFields.put("remainedTime", current.getRemainedTime());
                                        statusFields.put("repeat", current.getRepeat());
                                        statusFields.put("shuffle", current.isShuffle());
                                        statusFields.put("paused", current.isPlays());
                                        nowPlaylist.setContorSongs(nowPlaylist.
                                                                        getContorSongs() + 1);
                                        current.setCurrentPlaylist(nowPlaylist);
                                    } else {

                                        statusFields.put("name", "");
                                        statusFields.put("remainedTime", 0);
                                        statusFields.put("repeat", "No Repeat");
                                        statusFields.put("shuffle", false);
                                        statusFields.put("paused", true);
                                    }

                                } else if (current.getRepeatMode() == 1) {

//                                    statusFields.put("remained ant", current.getRemainedTime());
                                    nowPlaylist.setContorSongs(nowPlaylist.getContorSongs() - 1);

                                    while (nowPlaylist.getContorSongs()
                                            < nowPlaylist.getTotalSongs() - 1) {

                                        current.setRemainedTime(current.getRemainedTime()
                                                + nowPlaylist.getPlaylistSongs().get(nowPlaylist.
                                                    getContorSongs()).getDuration());
                                        nowPlaylist.setContorSongs(nowPlaylist.
                                                    getContorSongs() + 1);
                                    }

                                    if (current.getRemainedTime() < 0) {
                                        current.setRemainedTime(current.getRemainedTime()
                                                + songs.get(songs.size() - 1).getDuration());
                                        nowPlaylist.setContorSongs(0);
                                        int max = nowPlaylist.getTotalSongs() - 1;
                                        while (nowPlaylist.getContorSongs() < max
                                                && current.getRemainedTime() < 0) {

                                            current.setRemainedTime(current.getRemainedTime()
                                                    + nowPlaylist.getPlaylistSongs().get(nowPlaylist.
                                                        getContorSongs()).getDuration());
                                            nowPlaylist.setContorSongs(nowPlaylist.getContorSongs() + 1);
                                        }
                                    }
                                    current.setRemainedTime(current.getRemainedTime());
                                    nowPlaylist.setContorSongs(nowPlaylist.getContorSongs() - 1);

                                    statusFields.put("name", songs.get(nowPlaylist.
                                            getContorSongs()).getName());
                                    statusFields.put("remainedTime", current.getRemainedTime());
                                    statusFields.put("repeat", current.getRepeat());
                                    statusFields.put("shuffle", current.isShuffle());
                                    statusFields.put("paused", current.isPlays());
                                    nowPlaylist.setContorSongs(nowPlaylist.getContorSongs() + 1);
                                    current.setCurrentPlaylist(nowPlaylist);

                                }

                            }
                        } else {
                            if (current.getRemainedTime() < 0) {
                                current.setRemainedTime(0);
                                current.setPlays(true);
                                current.setCurrentSong(null);
                                statusFields.put("name", "");
                                statusFields.put("remainedTime", current.getRemainedTime());
                                statusFields.put("repeat", current.getRepeat());
                                statusFields.put("shuffle", current.isShuffle());
                                statusFields.put("paused", current.isPlays());
                            }
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

