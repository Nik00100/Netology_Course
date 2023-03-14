package command;

import console.ConsoleHelper;
import store_order.Store;

public class ShowSortedItemsCommand implements Command {
    private final Store store;

    public ShowSortedItemsCommand(Store store) {
        this.store = store;
    }

    @Override
    public void execute() {
        ConsoleHelper.writeMessage("Введите значение, по которому происходит сортировка инвентаря (рейтинг, название или цена)");
        String filter = ConsoleHelper.readString();
        try {
            store.writeElementListToConsole(store.filterValue(filter));
        } catch (IllegalArgumentException e) {
            ConsoleHelper.writeMessage("Введено неверное значение");
        }
    }
}
