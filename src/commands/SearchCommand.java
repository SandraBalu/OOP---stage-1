package commands;

import fileio.input.LibraryInput;
import fileio.input.PodcastInput;
import fileio.input.SongInput;

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
     * method used to execute search command
     */
    public ArrayList<String> executeSearch(final SearchCommand searchCommand,
                                                 final LibraryInput library) {
        ArrayList<String> matching = new ArrayList<>();
        Filter filters = searchCommand.getFilters();
        int index = 0;

        if (searchCommand.getType().equals("song")) {
            ArrayList<SongInput> songs = library.getSongs();

            if (filters != null) {

                if (filters.getName() != null) {
                    index = 0;
                    for (SongInput song : songs) {
                        if (song.getName().startsWith(filters.getName())
                                && index < MAGIC_NUMBER) {
                            matching.add(song.getName());
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
                            matching.add(song.getName());
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
                            matching.add(song.getName());
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
                            matching.add(song.getName());
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
                            matching.add(song.getName());
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
                            matching.add(song.getName());
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
                                matching.add(song.getName());
                                index++;
                            }
                        }
                        if (sign.equals(">")) {
                            if (song.getReleaseYear() > year && index < MAGIC_NUMBER) {
                                matching.add(song.getName());
                                index++;
                            }
                        }
                    }
                        return matching;
                }

            }

        } else if (searchCommand.getType().equals("playlist")) {
            return matching;

        } else if (searchCommand.getType().equals("podcast")) {
            ArrayList<PodcastInput> podcastArray = library.getPodcasts();

            if (filters.getOwner() != null) {
                index = 0;
                for (PodcastInput podcast : podcastArray) {
                    if (podcast.getOwner().equalsIgnoreCase(filters.getOwner())
                            && index < MAGIC_NUMBER) {
                        matching.add(podcast.getName());
                        index++;
                    }
                }
            }

            if (filters.getName() != null) {
                index = 0;
                for (PodcastInput podcast : podcastArray) {
                    if (podcast.getName().startsWith(filters.getName()) && index < MAGIC_NUMBER) {
                        matching.add(podcast.getName());
                        index++;
                    }
                }
                return matching;
            }
        }
        return matching;

    }

}
