import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static final Map<Integer, Integer> sizeToFreq = new HashMap<>();
    static int totalAmountOfR = 0;
    public static String generateRoute(String letters, int length) {
        Random random = new Random();
        StringBuilder route = new StringBuilder();
        for (int i = 0; i < length; i++) {
            route.append(letters.charAt(random.nextInt(letters.length())));
        }
        return route.toString();
    }

    public static void updateCountInMap(int size, Map<Integer,Integer> map) {
        if (size>0) {
            if (map.containsKey(size)) {
                int count = map.get(size);
                count++;
                map.put(size,count);
            } else map.put(size,1);
        }
    }

    public static void writeToConsoleSortedSizeAndFreqOfR(Map<Integer,Integer> map) {

        List<String> sortedListSizeFreq = map.entrySet().stream()
                .sorted(Collections.reverseOrder(Comparator.comparing(Map.Entry::getValue)))
                .map(entry->String.format("- %d (%d раз)",entry.getKey(),entry.getValue()))
                .collect(Collectors.toList());
        System.out.println(String.format("Самое частое количество повторений идущих подряд символов 'R' %s",sortedListSizeFreq.get(0)));
        System.out.println("Другие размеры:");
        sortedListSizeFreq.stream().filter(s -> !s.equals(sortedListSizeFreq.get(0))).forEach(System.out::println);
    }

    public synchronized static void generateOneStepRouteAndCountRIntervals() {
        String str =  generateRoute("RLRFR",100);
        System.out.print(str);

        int i=0;
        int size=0;
        while (i<str.length()) {
            while (i<str.length() && str.charAt(i++) == 'R') {
                size++;
                totalAmountOfR++;
            }
            updateCountInMap(size,sizeToFreq);
            size=0;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable charGenerator = ()->generateOneStepRouteAndCountRIntervals();
        List<Thread> threads = new ArrayList<>();
        for (int i=0; i<1000; i++) {
            Thread thread = new Thread(charGenerator);
            thread.start();
            threads.add(thread);
        }

        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println();
        System.out.println(String.format("Общее количество символов 'R' в получившейся строке равно %d",totalAmountOfR));
        writeToConsoleSortedSizeAndFreqOfR(sizeToFreq);
    }
}
