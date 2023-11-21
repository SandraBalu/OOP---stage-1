package commands;

public final class SearchCommand extends Command {
    private String type;
    private Filter filters;

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public Filter getFilters() {
        return filters;
    }

    public void setFilters(final Filter filters) {
        this.filters = filters;
    }

}
