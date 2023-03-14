package command;

import com.google.common.collect.ImmutableMap;
import store_order.Order;
import store_order.Store;

import static command.CommandName.*;

public class CommandContainer {
    private final ImmutableMap<String, Command> commandMap;
    private final Command unknownCommand;

    public CommandContainer(Store store, Order order) {

        commandMap = ImmutableMap.<String, Command>builder()
                .put(INFO.getCommandName(), new InfoCommand())
                .put(SHOW_STORED_ITEMS.getCommandName(), new ShowStoredItemsCommand(store))
                .put(SHOW_FILTERED_ITEMS.getCommandName(), new ShowFilteredItemsCommand(store))
                .put(SHOW_SORTED_ITEMS.getCommandName(), new ShowSortedItemsCommand(store))
                .put(ADD_ELEMENT_TO_CART.getCommandName(), new AddToCartCommand(store, order))
                .put(REMOVE_ELEMENT_FROM_CART.getCommandName(), new RemoveFromCartCommand(order))
                .put(SHOW_ELEMENTS_IN_CART.getCommandName(), new ShowInCartCommand(order))
                .build();

        unknownCommand = new UnknownCommand();
    }

    public Command retrieveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }
}
