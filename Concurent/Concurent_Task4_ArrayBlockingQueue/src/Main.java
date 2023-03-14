import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    final static int MAX_NUMBER_OF_STRINGS_IN_QUEUE=100;
    final static int LETTER_LENGTH = 100_000;
    final static int MAX_NUMBER_OF_STRINGS = 10_000;

    static class CharCounter {
        // очередь для вычислений количества символов
        BlockingQueue<String> queueForCounter = new ArrayBlockingQueue<>(MAX_NUMBER_OF_STRINGS_IN_QUEUE);
        // счетчик максимального количества символов
        AtomicInteger maxNumber;
        // найденная строка с максимальным количеством символов
        volatile String maxString;
        char ch;

        public CharCounter(char ch) {
            this.maxNumber = new AtomicInteger(0);
            this.maxString = null;
            this.ch=ch;
        }

        public BlockingQueue<String> getQueueForCounter() {return queueForCounter;}
        public AtomicInteger getMaxNumber() {return maxNumber;}
        public String getMaxString() {return maxString;}

        public void countChars(String str) {
            int count=0;
            for (int i=0; i<str.length(); i++) {
                if (str.charAt(i)==ch) count++;
            }
            if (count > maxNumber.get()) {
                maxNumber.set(count);
                maxString=str;
            }
        }

        public void count() {
            int i=0;
            while (i<MAX_NUMBER_OF_STRINGS) {
                try {
                    String str = queueForCounter.take();
                    countChars(str);
                    i++;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long startTs = System.currentTimeMillis(); // start time
        CharCounter charCounterA = new CharCounter('a');
        CharCounter charCounterB = new CharCounter('b');
        CharCounter charCounterC = new CharCounter('c');

        Thread generator = new Thread(()->{
            int i=0;
            while (i<MAX_NUMBER_OF_STRINGS) {
                String str = generateText("abc",LETTER_LENGTH);
                i++;
                try {
                    charCounterA.getQueueForCounter().put(str);
                    charCounterB.getQueueForCounter().put(str);
                    charCounterC.getQueueForCounter().put(str);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        generator.start();

        Thread counterA = new Thread(()->charCounterA.count());
        Thread counterB = new Thread(()->charCounterB.count());
        Thread counterC = new Thread(()->charCounterC.count());

        counterA.start();
        counterB.start();
        counterC.start();

        generator.join();
        counterA.join();
        counterB.join();
        counterC.join();

        System.out.println(String.format("Максимальное количество символов 'a' в строке равно %d", charCounterA.getMaxNumber().get()));
        System.out.println(String.format("Максимальное количество символов 'b' в строке равно %d", charCounterB.getMaxNumber().get()));
        System.out.println(String.format("Максимальное количество символов 'c' в строке равно %d", charCounterC.getMaxNumber().get()));
        System.out.println();

        long endTs = System.currentTimeMillis(); // end time

        System.out.println("Подсчет осуществлен за: " + (endTs - startTs) + "мс");
    }
}
