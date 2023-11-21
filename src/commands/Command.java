package commands;

import java.util.ArrayList;

public class Command {
    private String command;
    private String username;
    private int timestamp;
//    private String playlistName;
//    private int playlistId;
//    private String type;
//    private Filter filters;
//    private int itemNumber;
//    private int speed;

//
//    public String getPlaylistName() {
//        return playlistName;
//    }
//
//    public void setPlaylistName(String playlistName) {
//        this.playlistName = playlistName;
//    }
//
//    public int getPlaylistId() {
//        return playlistId;
//    }
//
//    public void setPlaylistId(int playlistId) {
//        this.playlistId = playlistId;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//
//    public Filter getFilters() {
//        return filters;
//    }
//
//    public void setFilters(Filter filters) {
//        this.filters = filters;
//    }
//
//    public int getItemNumber() {
//        return itemNumber;
//    }
//
//    public void setItemNumber(int itemNumber) {
//        this.itemNumber = itemNumber;
//    }
//
//    public int getSpeed() {
//        return speed;
//    }
//
//    public void setSpeed(int speed) {
//        this.speed = speed;
//    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }


//    public void execute();
    @Override
    public String toString() {
        return "Command: " + command + ", Username: " + username + ", Timestamp: " + timestamp;
    }
}
