package commands;

public class LoadCommand extends Command{

    public void execute() {
        if (this instanceof LoadCommand) {
            LoadCommand loadCommand = (LoadCommand) this;
        }
    }

}
