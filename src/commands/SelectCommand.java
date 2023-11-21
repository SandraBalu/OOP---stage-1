package commands;

import java.util.ArrayList;

public final class SelectCommand extends Command {

    private int itemNumber;

    public int getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(final int itemNumber) {
        this.itemNumber = itemNumber;
    }

    /**
     * methode used to execute select command
     */
    public String executeSelect(final ArrayList<String> matching,
                                final SelectCommand selectCommand) {
        if (matching == null) {
            return "Please conduct a search before making a selection.";
        }

        int item = selectCommand.getItemNumber();
        if (item > matching.size()) {
            return "The selected ID is too high.";
        }

        String selectedSuccessfully;
        selectedSuccessfully = "Successfully selected " + matching.get(--item) + ".";
        return selectedSuccessfully;
    }

}
