package commands;

public class ShuffleCommand extends Command{
    private int seed;

    public int getSeed() {
        return seed;
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }

    public void execute() {
        if (this instanceof ShuffleCommand) {
            ShuffleCommand shuffleCommand = (ShuffleCommand) this;
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", Speed: " + seed;
    }
}
