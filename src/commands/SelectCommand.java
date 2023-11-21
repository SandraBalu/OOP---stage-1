package commands;

public class SelectCommand extends Command {

    private int itemNumber;

    public int getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    public void execute() {
        if (this instanceof SelectCommand) {
            SelectCommand selectCommand= (SelectCommand) this;
        }
    }
    @Override
    public String toString() {
        return super.toString() + ", ItemNumber: " + itemNumber;
    }
}
