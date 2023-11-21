package commands;

public class LikeCommand extends Command{
    public void execute() {
        if (this instanceof LikeCommand) {
            LikeCommand likeCommand = (LikeCommand) this;
        }
    }
}
