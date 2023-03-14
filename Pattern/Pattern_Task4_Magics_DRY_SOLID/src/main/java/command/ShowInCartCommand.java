package command;

import store_order.Order;

public class ShowInCartCommand implements Command {
    private final Order order;

    public ShowInCartCommand(Order order) {
        this.order = order;
    }

    @Override
    public void execute() {
        order.writeElementListToConsole(order.getElementList());
    }
}
