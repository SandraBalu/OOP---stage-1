package commands;

public final class ShuffleCommand extends Command {
    private int seed;

    public int getSeed() {
        return seed;
    }

    public void setSeed(final int seed) {
        this.seed = seed;
    }

}
