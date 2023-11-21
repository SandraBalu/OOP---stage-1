package commands;

public class StatusCommand extends Command{
    public void execute() {
        if (this instanceof StatusCommand) {
            StatusCommand statusCommand = (StatusCommand) this;
        }
    }
}
