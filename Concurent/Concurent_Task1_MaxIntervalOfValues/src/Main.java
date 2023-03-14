import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    static class CountIntervalCallable implements Callable<String> {

        @Override
        public String call() throws Exception {
            String text = generateText("aab", 30_000);
            int maxSize = 0;
            for (int i = 0; i < text.length(); i++) {
                for (int j = 0; j < text.length(); j++) {
                    if (i >= j) {
                        continue;
                    }
                    boolean bFound = false;
                    for (int k = i; k < j; k++) {
                        if (text.charAt(k) == 'b') {
                            bFound = true;
                            break;
                        }
                    }
                    if (!bFound && maxSize < j - i) {
                        maxSize = j - i;
                    }
                }
            }
            return (text.substring(0, 100) + " -> " + maxSize);
        }
    }


    public static void main(String[] args) throws Exception {
        long startTs = System.currentTimeMillis(); // start time
        List<Future> futureList = new ArrayList<>();
        List<String> resultList = new ArrayList<>();

        // Создаём пул потоков фиксированного размера
        final ExecutorService threadPool = Executors.newFixedThreadPool(25);

        for (int i = 0; i < 25; i++) {
            Callable<String> countInterval = new CountIntervalCallable();
            // Отправляем задачу
            final Future<String> futureTask = threadPool.submit(countInterval);
            futureList.add(futureTask);
        }

        for (Future<String> future : futureList) {
            // Считываем строку и записываем в resultList
            final String text = future.get();
            resultList.add(text);
            System.out.println(text);
        }

        int maxSize = resultList.stream().mapToInt(str->Integer.parseInt(str.split("->")[1].trim())).max().getAsInt();
        System.out.println(String.format("Max interval equals %d",maxSize));

        long endTs = System.currentTimeMillis(); // end time

        System.out.println("Time: " + (endTs - startTs) + "ms");

        threadPool.shutdown();
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

}