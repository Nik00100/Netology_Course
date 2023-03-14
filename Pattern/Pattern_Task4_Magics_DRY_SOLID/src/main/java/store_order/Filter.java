package store_order;

import java.util.List;

public interface Filter<T> {
    // Отфильтровать по типу
    List<T> filterType(String type);

    // Отфильтровать по возрастанию рейтинга
    List<T> filterValue(String value);

}
