package command;

import store_order.Store;

public class ShowStoredItemsCommand implements Command {
    private final Store store;

    public ShowStoredItemsCommand(Store store) {
        this.store = store;
    }

    @Override
    public void execute() {
        store.writeElementListToConsole(store.getElementList());
    }
}
