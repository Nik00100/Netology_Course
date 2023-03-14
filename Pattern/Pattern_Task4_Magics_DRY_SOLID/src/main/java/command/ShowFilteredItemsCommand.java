package command;

import console.ConsoleHelper;
import store_order.Store;

public class ShowFilteredItemsCommand implements Command {

    private final Store store;

    public ShowFilteredItemsCommand(Store store) {
        this.store = store;
    }

    @Override
    public void execute() {
        ConsoleHelper.writeMessage("Введите вид спорта, по которому происходит фильтрация инвентаря (футбол, хоккей или теннис)");
        String filter = ConsoleHelper.readString();
        try {
            store.writeElementListToConsole(store.filterType(filter));
        } catch (IllegalArgumentException e) {
            ConsoleHelper.writeMessage("Введено неверное значение");
        }
    }
}
