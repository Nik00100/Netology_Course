package store_order;

import console.ConsoleHelper;

import java.util.List;

public interface Function<T> {

    // Вывести в консоль список спортинвентаря
    default void writeElementListToConsole(List<T> list) {
        if (list.isEmpty()) {
            ConsoleHelper.writeMessage("Список пуст!");
            return;
        }
        int n = 1;
        for (T p : list) {
            ConsoleHelper.writeMessage(n++ + ". " + p);
        }
        ConsoleHelper.writeMessage("");
    }

    // Получить список спортинвентаря
    List<T> getElementList();


    // Добавить спортинвентарь
    void addElement(T equipment);

    // Удалить спортинвентарь
    void removeElement(T equipment);

    // Получить по индексу спортинвентарь
    T getElement(int number);
}
