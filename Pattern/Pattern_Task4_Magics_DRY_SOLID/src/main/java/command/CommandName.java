package command;

public enum CommandName {
    INFO("info"),
    SHOW_STORED_ITEMS("1"),
    SHOW_FILTERED_ITEMS("2"),
    SHOW_SORTED_ITEMS("3"),
    ADD_ELEMENT_TO_CART("4"),
    REMOVE_ELEMENT_FROM_CART("5"),
    SHOW_ELEMENTS_IN_CART("6"),
    EXIT("7");


    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}
