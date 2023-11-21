package main;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import commands.SearchCommand;
import fileio.input.*;

import java.util.ArrayList;

public class Display {
    public static void displayLibraryData(LibraryInput library) {
        if (library != null) {
            // Display songs
            System.out.println("Songs:");
            displaySongs(library.getSongs());

            // Display podcasts
            System.out.println("\nPodcasts:");
            displayPodcasts(library.getPodcasts());

            // Display users
            System.out.println("\nUsers:");
            displayUsers(library.getUsers());
        } else {
            System.out.println("Library is null.");
        }
    }

    public static void displaySongs(ArrayList<SongInput> songs) {
        if (songs != null) {
            for (SongInput song : songs) {
                System.out.println("Name: " + song.getName());
                System.out.println("Duration: " + song.getDuration());
                System.out.println("Album: " + song.getAlbum());
                System.out.println("Tags: " + song.getTags());
                System.out.println("Lyrics: " + song.getLyrics());
                System.out.println("Genre: " + song.getGenre());
                System.out.println("Release Year: " + song.getReleaseYear());
                System.out.println("Artist: " + song.getArtist());
                System.out.println("-------------------------");
            }
        } else {
            System.out.println("Songs list is null.");
        }
    }

    private static void displayPodcasts(ArrayList<PodcastInput> podcasts) {
        if (podcasts != null) {
            for (PodcastInput podcast : podcasts) {
                System.out.println("Name: " + podcast.getName());
                System.out.println("Owner: " + podcast.getOwner());

                ArrayList<EpisodeInput> episodes = podcast.getEpisodes();
                if (episodes != null) {
                    System.out.println("Episodes:");
                    displayEpisodes(episodes);
                } else {
                    System.out.println("Episodes list is null.");
                }

                System.out.println("-------------------------");
            }
        } else {
            System.out.println("Podcasts list is null.");
        }
    }

    public static void displayEpisodes(ArrayList<EpisodeInput> episodes) {
        if (episodes != null) {
            for (EpisodeInput episode : episodes) {
                System.out.println("Name: " + episode.getName());
                System.out.println("Duration: " + episode.getDuration());
                System.out.println("Description: " + episode.getDescription());
                System.out.println("-------------------------");
            }
        } else {
            System.out.println("Episodes list is null.");
        }
    }

    public static void displayUsers(ArrayList<UserInput> users) {
        if (users != null) {
            for (UserInput user : users) {
                System.out.println("Username: " + user.getUsername());
                System.out.println("Age: " + user.getAge());
                System.out.println("City: " + user.getCity());
                System.out.println("-------------------------");
            }
        } else {
            System.out.println("Users list is null.");
        }
    }
}
