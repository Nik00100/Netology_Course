import java.util.*;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class Main {
    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1, 2, 5, 16, -1, -2, 0, 32, 3, 5, 8, 23, 4);

        List<Integer> positiveEvenList = new ArrayList<>();
        for (Integer num : intList) {
            if (num > 0 && num % 2 == 0) {
                positiveEvenList.add(num);
            }
        }

        Collections.sort(positiveEvenList);

        for (Integer num : positiveEvenList) {
            System.out.println(num);
        }
    }
}
