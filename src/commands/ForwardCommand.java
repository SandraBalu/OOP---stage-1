package commands;

public class ForwardCommand extends Command{

    public void execute() {
        if (this instanceof ForwardCommand ) {
            ForwardCommand forwardCommand = (ForwardCommand) this;
        }
    }
}
