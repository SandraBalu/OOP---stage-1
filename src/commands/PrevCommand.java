package commands;

public class PrevCommand extends Command{
    public void execute() {
        if (this instanceof PrevCommand) {
            PrevCommand prevCommand = (PrevCommand) this;
        }
    }
}
