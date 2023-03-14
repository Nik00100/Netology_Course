package store_order;


import console.ConsoleHelper;
import properties.FilterProperties;
import properties.SportType;
import equipment.*;

import java.util.*;

public class Store implements Filter<SportEquipment>, Function<SportEquipment> {
    private List<SportEquipment> equipmentList;

    public Store() {
        this.equipmentList = new ArrayList<>();
    }

    // Получить список спортинвентаря
    @Override
    public List<SportEquipment> getElementList() {
        return equipmentList;
    }


    // Добавить спортинвентарь
    @Override
    public void addElement(SportEquipment equipment) {
        equipmentList.add(equipment);
    }

    // Удалить спортинвентарь
    @Override
    public void removeElement(SportEquipment equipment) {
        if (equipmentList.isEmpty()) {
            ConsoleHelper.writeMessage("Магазин пуст!");
            return;
        }
        equipmentList.remove(equipment);
    }

    // Получить по индексу спортинвентарь
    @Override
    public SportEquipment getElement(int number) {
        return equipmentList.get(number);
    }

    // Отфильтровать по виду спорта
    @Override
    public List<SportEquipment> filterType(String type) {
        if (!Arrays.stream(SportType.values()).anyMatch(sportType -> sportType.getDescription().equals(type))) {
            throw new IllegalArgumentException();
        }
        List<SportEquipment> result = new ArrayList<>();
        for (SportType t : SportType.values()) {
            if (type.equals(t.getDescription())) {
                result = equipmentList.stream()
                        .filter(x -> x.getSportType().getDescription().equals(type)).toList();
            }
        }
        return result;
    }

    // Отсортировать по значению (рейтинг, название инвентаря, цена)
    @Override
    public List<SportEquipment> filterValue(String value) {
        if (!Arrays.stream(FilterProperties.values()).anyMatch(v -> v.getDescription().equals(value))) {
            throw new IllegalArgumentException();
        }
        List<SportEquipment> result = equipmentList;
        if (FilterProperties.RATING.getDescription().equals(value)) {
            result.sort(Comparator.comparing(SportEquipment::getRating));
        } else if (FilterProperties.EQUIPMENT.getDescription().equals(value)) {
            result.sort(Comparator.comparing(SportEquipment::getEquipment));
        } else result.sort(Comparator.comparing(SportEquipment::getPrice));
        return result;
    }

}
