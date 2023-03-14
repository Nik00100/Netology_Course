import java.util.*;

public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        logger.log("Запускаем программу");
        logger.log("Просим пользователя ввести входные данные для списка");

        System.out.println("Введите размер списка:");
        int length = intValueFromScannerInput();

        System.out.println("Введите верхнюю границу для значений:");
        int maxValue = intValueFromScannerInput();

        logger.log("Создаём и наполняем список");
        List<Integer> source = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            source.add(new Random().nextInt(maxValue));
        }
        System.out.println("Вот случайный список: " + source.toString());

        logger.log("Просим пользователя ввести входные данные для фильтрации");
        System.out.println("Введите порог для фильтра:");
        int treshold = intValueFromScannerInput();

        Filter filter = new Filter(treshold);
        List<Integer> result = filter.filterOut(source);
        logger.log("Выводим результат на экран");
        System.out.println("Отфильтрованный список: " + result.toString());
        logger.log("Завершаем программу");
    }

    protected static int intValueFromScannerInput() {
        while (true) {
            Logger logger = Logger.getInstance();
            Scanner scanner = new Scanner(System.in);
            boolean flag = scanner.hasNextInt();
            //проверяем, что введено число
            if (!flag) {
                logger.log("Неверный формат ввода");
                System.out.println("Извините, вы ввели не число. Введите число");
            } else {
                return scanner.nextInt();
            }
        }
    }

}