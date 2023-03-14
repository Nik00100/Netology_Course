import command.CommandContainer;
import command.CommandName;
import console.ConsoleHelper;
import equipment.*;
import store_order.Order;
import store_order.Store;

public class Main {
    public static Store store = new Store();
    public static Order order = new Order();
    public static CommandContainer commandContainer = new CommandContainer(store, order);

    public static void main(String[] args) {

        SportEquipment footballBall = new FootballEquipment(8, "Футбольный мяч", "PRO", "синтетическая кожа",
                "белый", 450, 2000, true, "MITRE");
        SportEquipment boots = new FootballEquipment(9, "Бутсы", "PRO", "синтетика", "черный",
                300, 5000, true, "PUMA");
        SportEquipment form = new FootballEquipment(5, "Форма", "MEDIUM", "синтетика", "зеленый",
                350, 2000, false, "KAPPA");
        SportEquipment skates = new HockeyEquipment(10, "Коньки", "MEDIUM", "пластик", "черный",
                800, 4500, "BAUER");
        SportEquipment stick = new HockeyEquipment(6, "Клюшка", "MEDIUM", "пластик", "черный",
                600, 1000, "CCM");
        SportEquipment racket = new TennisEquipment(9, "Ракетка", "JUNIOR", "алюминий", "белый",
                275, 1500, true, "HEAD");
        SportEquipment tennisBalls = new TennisEquipment(7, "Теннисные мячи", "JUNIOR", "резина,фетр",
                "желтый", 180, 1000, false, "BABOLAT");

        store.addElement(footballBall);
        store.addElement(boots);
        store.addElement(form);
        store.addElement(skates);
        store.addElement(stick);
        store.addElement(racket);
        store.addElement(tennisBalls);

        while (true) {
            ConsoleHelper.writeMessage("");
            commandContainer.retrieveCommand(CommandName.INFO.getCommandName()).execute();
            String command = ConsoleHelper.readString();
            if (CommandName.EXIT.getCommandName().equals(command)) break;
            commandContainer.retrieveCommand(command).execute();
        }
        ConsoleHelper.close();
    }
}
