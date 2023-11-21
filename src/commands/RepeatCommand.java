package commands;

public class RepeatCommand extends Command{
    public void execute() {
        if (this instanceof RepeatCommand) {
            RepeatCommand repeatCommand = (RepeatCommand) this;
        }
    }
}
