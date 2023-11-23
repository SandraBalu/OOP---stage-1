package commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.input.ExtendedUser;
import fileio.input.SongInput;
import main.Current;

import java.util.ArrayList;

public final class LikeCommand extends Command {

    public final void executeLike(LikeCommand likeCommand, Current current,
                                  ArrayList<ExtendedUser> extendedUsers, ObjectMapper objectMapper,
                                  ArrayNode outputs) {

        if (current.getAntCommand().equals("load")) {
            for (ExtendedUser user : extendedUsers) {


                if (user.getUserName().equals(likeCommand.getUsername())) {
                    if (user.getLikedSongs() != null) {

                        ArrayList<SongInput> songs = user.getLikedSongs();
                        if (songs.contains(current.getCurrentSong())) {
                            //the song is removed from liked songs
                            songs.remove(current.getCurrentSong());
                            user.setLikedSongs(songs);
                            ObjectNode likeResult = objectMapper.createObjectNode();
                            likeResult.put("command", "like");
                            likeResult.put("user", likeCommand.getUsername());
                            likeResult.put("timestamp", likeCommand.getTimestamp());
                            likeResult.put("message", "Unlike registered successfully.");
                            outputs.add(likeResult);
                            return;
                        } else {
                            songs.add(current.getCurrentSong());
                            user.setLikedSongs(songs);
                            ObjectNode likeResult = objectMapper.createObjectNode();
                            likeResult.put("command", "like");
                            likeResult.put("user", likeCommand.getUsername());
                            likeResult.put("timestamp", likeCommand.getTimestamp());
                            likeResult.put("message", "Like registered successfully.");
                            outputs.add(likeResult);
                            return;
                        }
                    } else {
                        ArrayList<SongInput> songs = new ArrayList<>();
                        songs.add(current.getCurrentSong());
                        user.setLikedSongs(songs);
                    }
                    //the song is liked and added to liked song
                    ObjectNode likeResult = objectMapper.createObjectNode();
                    likeResult.put("command", "like");
                    likeResult.put("user", likeCommand.getUsername());
                    likeResult.put("timestamp", likeCommand.getTimestamp());
                    likeResult.put("message", "Like registered successfully.");

                    outputs.add(likeResult);
                    return;
                }


            }

        } else {
            ObjectNode likeResult = objectMapper.createObjectNode();
            likeResult.put("command", "like");
            likeResult.put("user", likeCommand.getUsername());
            likeResult.put("timestamp", likeCommand.getTimestamp());
            likeResult.put("message", "Please load a source before liking or unliking.");

            outputs.add(likeResult);
            return;
        }

    }
}
