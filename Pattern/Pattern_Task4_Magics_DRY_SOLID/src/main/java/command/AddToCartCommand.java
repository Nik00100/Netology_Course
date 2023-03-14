package command;

import console.ConsoleHelper;
import store_order.Order;
import store_order.Store;

public class AddToCartCommand implements Command {
    private final Store store;
    private final Order order;

    public AddToCartCommand(Store store, Order order) {
        this.store = store;
        this.order = order;
    }


    @Override
    public void execute() {
        ConsoleHelper.writeMessage("Введите номер спортинвентаря для добавления в корзину. Список доступного инвентаря магазина ниже.");
        order.writeElementListToConsole(store.getElementList());
        int number = ConsoleHelper.readInt();
        order.addElement(store.getElement(number - 1));
    }
}
