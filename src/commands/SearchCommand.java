package commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.input.LibraryInput;
import fileio.input.PodcastInput;
import fileio.input.SongInput;
import main.Current;
import main.Playlist;

import java.util.ArrayList;

public final class SearchCommand extends Command {
    private String type;
    private Filter filters;

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public Filter getFilters() {
        return filters;
    }

    public void setFilters(final Filter filters) {
        this.filters = filters;
    }

    private static final int MAGIC_NUMBER = 5;

    /**
     * method used to execute search command for song
     */

    public ArrayList<SongInput> searchSong(final SearchCommand searchCommand,
                                           final LibraryInput library) {

        ArrayList<SongInput> matching = new ArrayList<>();
        Filter filters = searchCommand.getFilters();
        int index = 0;

        ArrayList<SongInput> songs = library.getSongs();

        if (filters != null) {

                if (filters.getName() != null) {
                    index = 0;
                    for (SongInput song : songs) {
                        if (song.getName().startsWith(filters.getName())
                                && index < MAGIC_NUMBER) {
                            matching.add(song);
                            index++;
                        }
                    }
                    return matching;
                }

                if (filters.getAlbum() != null) {
                    index = 0;
                    for (SongInput song : songs) {
                        if (song.getAlbum().equalsIgnoreCase(filters.getAlbum())
                                && index < MAGIC_NUMBER) {
                            matching.add(song);
                            index++;
                        }
                    }
                    return matching;
                }

                if (filters.getArtist() != null) {
                    index = 0;
                    for (SongInput song : songs) {
                        if (song.getArtist().equalsIgnoreCase(filters.getArtist())
                                && index < MAGIC_NUMBER) {
                            matching.add(song);
                            index++;
                        }
                    }
                    return matching;
                }

                if (filters.getTags() != null && !filters.getTags().isEmpty()
                        && index < MAGIC_NUMBER) {
                    index = 0;
                    for (SongInput song : songs) {
                        if (song.getTags().containsAll(filters.getTags())
                                && index < MAGIC_NUMBER) {
                            matching.add(song);
                            index++;
                        }
                    }
                    return matching;
                }

                if (filters.getLyrics() != null) {
                    index = 0;
                    for (SongInput song : songs) {
                        if (song.getLyrics().contains(filters.getLyrics())
                                && index < MAGIC_NUMBER) {
                            matching.add(song);
                            index++;
                        }
                    }
                    return matching;
                }

                if (filters.getGenre() != null) {
                    index = 0;
                    for (SongInput song : songs) {
                        if (song.getGenre().equalsIgnoreCase(filters.getGenre())
                                && index < MAGIC_NUMBER) {
                            matching.add(song);
                            index++;
                        }
                    }
                    return matching;
                }

                if (filters.getReleaseYear() != null) {
                    index = 0;
                    String releaseYear = filters.getReleaseYear();
                    String sign = releaseYear.substring(0, 1);
                    String yearStr = releaseYear.substring(1);
                    //matching.add(yearStr);
                    int year = Integer.parseInt(yearStr);
                    for (SongInput song : songs) {
                        if (sign.equals("<")) {
                            if (song.getReleaseYear() < year && index < MAGIC_NUMBER) {
                                matching.add(song);
                                index++;
                            }
                        }
                        if (sign.equals(">")) {
                            if (song.getReleaseYear() > year && index < MAGIC_NUMBER) {
                                matching.add(song);
                                index++;
                            }
                        }
                    }
                    return matching;
                }

            }

        return matching;

    }

    /**
     * method used to execute search command for podcast
     */
    public ArrayList<PodcastInput> searchPodcast(final SearchCommand searchCommand,
                                                 final LibraryInput library) {

        ArrayList<PodcastInput> podcastArray = library.getPodcasts();
        ArrayList<PodcastInput> matchingPodcasts = new ArrayList<>();
        int index = 0;

        if (filters.getOwner() != null) {
            index = 0;
            for (PodcastInput podcast : podcastArray) {
                if (podcast.getOwner().equalsIgnoreCase(filters.getOwner())
                        && index < MAGIC_NUMBER) {
                    matchingPodcasts.add(podcast);
                    index++;
                }
            }
        }

        if (filters.getName() != null) {
            index = 0;
            for (PodcastInput podcast : podcastArray) {
                if (podcast.getName().startsWith(filters.getName()) && index < MAGIC_NUMBER) {
                    matchingPodcasts.add(podcast);
                    index++;
                }
            }
            return matchingPodcasts;
        }

        return matchingPodcasts;

    }

    public  final ArrayList<Playlist> searchPlaylist(final SearchCommand searchCommand,
                                                     final  ArrayList<Playlist> playlists) {

        ArrayList<Playlist> matching = new ArrayList<>();
        Filter filter = searchCommand.getFilters();
        int index;

        if (filters.getOwner() != null) {
            index = 0;
            for (Playlist playlist : playlists) {
                if (playlist.getUsername().equalsIgnoreCase(filters.getOwner())
                        && index < MAGIC_NUMBER) {
                    matching.add(playlist);
                    index++;
                }
            }
        }

        if (filters.getName() != null) {
            index = 0;
            for (Playlist playlist : playlists) {
                if (playlist.getPlaylistName().startsWith(filters.getName()) && index < MAGIC_NUMBER) {
                    matching.add(playlist);
                    ;
                    index++;
                }
            }
            return matching;
        }
        return matching;
    }

    /**
     * display json with search message
     */
    public void displaySearch(final SearchCommand searchCommand, final LibraryInput library,
                              final ArrayNode outputs, final ObjectMapper objectMapper,
                              final ArrayList<SongInput> matchingSongs,
                              final ArrayList<Playlist> matchingPlaylists,
                              final ArrayList<PodcastInput> matchingPodcasts) {

        if (searchCommand.getType().equals("song")) {
            ObjectNode matchingResults = objectMapper.createObjectNode();
            matchingResults.put("command", "search");
            matchingResults.put("user", searchCommand.getUsername());
            matchingResults.put("timestamp", searchCommand.getTimestamp());
            matchingResults.put("message", "Search returned "
                    + matchingSongs.size() + " results");

            ArrayNode matchingArrayNode = matchingResults.putArray("results");
            for (SongInput song : matchingSongs) {
                matchingArrayNode.add(song.getName());
            }
            outputs.add(matchingResults);

        }
        if (searchCommand.getType().equals("playlist")) {
            ObjectNode matchingResults = objectMapper.createObjectNode();
            matchingResults.put("command", "search");
            matchingResults.put("user", searchCommand.getUsername());
            matchingResults.put("timestamp", searchCommand.getTimestamp());
            matchingResults.put("message", "Search returned "
                    + matchingPlaylists.size() + " results");

            ArrayNode matchingArrayNode = matchingResults.putArray("results");
            for (Playlist playlist : matchingPlaylists) {
                matchingArrayNode.add(playlist.getPlaylistName());
            }
            outputs.add(matchingResults);

        }

        if (searchCommand.getType().equals("podcast")) {
            ObjectNode matchingResults = objectMapper.createObjectNode();
            matchingResults.put("command", "search");
            matchingResults.put("user", searchCommand.getUsername());
            matchingResults.put("timestamp", searchCommand.getTimestamp());
            matchingResults.put("message", "Search returned "
                    + matchingPodcasts.size() + " results");

            ArrayNode matchingArrayNode = matchingResults.putArray("results");
            for (PodcastInput podcast : matchingPodcasts) {
                matchingArrayNode.add(podcast.getName());
            }
            outputs.add(matchingResults);

        }
    }



    /**
     * execute search
     */
    public void executeSearch(SearchCommand searchCommand, Current current ,LibraryInput library,
                              ArrayList<Playlist> playlists,
                              ObjectMapper objectMapper, ArrayNode outputs) {

        if (searchCommand.getType().equals("song")) {
            current.setMatchingSongsSearch(searchCommand.searchSong(searchCommand, library));
            current.setWhatIsOn(1);

        }
        if (searchCommand.getType().equals("podcast")) {
            current.setMatchingPodcastsSearch(searchCommand.searchPodcast(searchCommand, library));
            current.setWhatIsOn(2);
        }
        if (searchCommand.getType().equals("playlist")) {
            current.setMatchingPlaylistsSearch(searchCommand.searchPlaylist(searchCommand, playlists));
            current.setWhatIsOn(3);
        }
        current.setLoaded(false);
        searchCommand.displaySearch(searchCommand, library, outputs,
                objectMapper, current.getMatchingSongsSearch(),current.getMatchingPlaylistsSearch(),
                        current.getMatchingPodcastsSearch());

        current.setAntCommand("search");
        current.setTimestampAnt(searchCommand.getTimestamp());
        current.setLoaded(false);
        current.setPlays(false);

    }

}

