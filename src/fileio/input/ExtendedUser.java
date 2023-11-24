package fileio.input;

import main.Playlist;

import java.util.ArrayList;

public final class ExtendedUser {

    private String userName;
    private ArrayList<SongInput>  likedSongs;

    private ArrayList<Playlist> likedPlaylists;

    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public ArrayList<SongInput> getLikedSongs() {
        return likedSongs;
    }

    public void setLikedSongs(final ArrayList<SongInput> likedSongs) {
        this.likedSongs = likedSongs;
    }

    public ArrayList<Playlist> getLikedPlaylists() {
        return likedPlaylists;
    }

    public void setLikedPlaylists(final ArrayList<Playlist> likedPlaylists) {
        this.likedPlaylists = likedPlaylists;
    }

    /**
     * make a new array with the existing useers
     */
    public void loadUsers(final ArrayList<ExtendedUser> extendedUsers,
                                  final LibraryInput libraryInput) {

        ArrayList<UserInput> users = libraryInput.getUsers();
        for (UserInput userInput : users) {
            ExtendedUser newUser = new ExtendedUser();
            newUser.setUserName(userInput.getUsername());
            extendedUsers.add(newUser);

        }
    }
}
