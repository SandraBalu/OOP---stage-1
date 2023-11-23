package fileio.input;

import java.util.ArrayList;

public class ExtendedUser {

    private String userName;
    private ArrayList<SongInput>  likedSongs;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ArrayList<SongInput> getLikedSongs() {
        return likedSongs;
    }

    public void setLikedSongs(ArrayList<SongInput> likedSongs) {
        this.likedSongs = likedSongs;
    }

    public  final  void loadUsers(ArrayList<ExtendedUser> extendedUsers, LibraryInput libraryInput) {
        ArrayList<UserInput> users = libraryInput.getUsers();
        for (UserInput userInput : users) {
            ExtendedUser newUser = new ExtendedUser();
            newUser.setUserName(userInput.getUsername());
            extendedUsers.add(newUser);

        }
    }
}
