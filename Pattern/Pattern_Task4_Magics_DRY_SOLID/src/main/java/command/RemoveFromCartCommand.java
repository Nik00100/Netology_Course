package command;

import console.ConsoleHelper;
import store_order.Order;

public class RemoveFromCartCommand implements Command {
    private final Order order;

    public RemoveFromCartCommand(Order order) {
        this.order = order;
    }

    @Override
    public void execute() {
        ConsoleHelper.writeMessage("Введите номер спортинвентаря для удаления из корзины. Сохраненные в корзине товары ниже.");
        order.writeElementListToConsole(order.getElementList());
        int number = ConsoleHelper.readInt();
        order.removeElement(order.getElement(number - 1));
    }
}
