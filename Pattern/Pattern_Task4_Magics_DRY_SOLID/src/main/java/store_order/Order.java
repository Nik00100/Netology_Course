package store_order;

import console.ConsoleHelper;
import equipment.SportEquipment;
import store_order.Function;

import java.util.ArrayList;
import java.util.List;

public class Order implements Function<SportEquipment> {

    private List<SportEquipment> orderList;

    public Order() {
        this.orderList = new ArrayList<>();
    }

    // Получить список спортинвентаря в заказе
    @Override
    public List<SportEquipment> getElementList() {
        return orderList;
    }


    @Override
    public void addElement(SportEquipment equipment) {
        orderList.add(equipment);
        ConsoleHelper.writeMessage("Инвентарь добавлен в заказ");
    }

    @Override
    public void removeElement(SportEquipment equipment) {
        try {
            orderList.remove(equipment);
            ConsoleHelper.writeMessage("Инвентарь удален из заказа");
        } catch (Exception e) {
            ConsoleHelper.writeMessage("Инвентарь с таким номером не найден!");
        }
    }

    @Override
    public SportEquipment getElement(int number) {
        return orderList.get(number);
    }
}
