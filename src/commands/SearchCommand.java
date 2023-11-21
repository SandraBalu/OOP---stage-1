package commands;

import java.util.ArrayList;

public class SearchCommand extends Command{
    private String type;
    private Filter filters;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public Filter getFilters() {
        return filters;
    }


    public void setFilters(Filter filters) {
        this.filters = filters;
    }

    public void execute() {
        if (this instanceof SearchCommand) {
            SearchCommand searchCommand = (SearchCommand) this;
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", Type: " + type + ", Filters: " + filters;
    }

}
