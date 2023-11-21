package commands;

public class BackwardCommand extends Command{

    public void execute() {
        if (this instanceof BackwardCommand) {
            BackwardCommand backwardCommand = (BackwardCommand) this;
        }
    }
}
