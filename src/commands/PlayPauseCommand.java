package commands;

public class PlayPauseCommand extends Command{
    public void execute() {
        if (this instanceof PlayPauseCommand) {
            PlayPauseCommand playPauseCommand = (PlayPauseCommand) this;
        }
    }
}
