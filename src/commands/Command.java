package commands;

public class Command {
    private String command;
    private String username;
    private int timestamp;

    /**
     * return command name
     */
    public String getCommand() {
        return command;
    }
    /**
     * set command name
     */
    public void setCommand(final String command) {
        this.command = command;
    }

    /**
     * return the username who types the command
     */
    public String getUsername() {
        return username;
    }

    /**
     * set the username who types the command
     */
    public void setUsername(final String username) {
        this.username = username;
    }

    /**
     * return the timestamp of the command
     */
    public int getTimestamp() {
        return timestamp;
    }

    /**
     * set  the timestamp of the command
     */
    public void setTimestamp(final int timestamp) {
        this.timestamp = timestamp;
    }

}
