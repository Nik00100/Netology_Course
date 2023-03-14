import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    // счетчики слов, прошедших критерии
    static AtomicInteger numberOfChecked3CharsStrings=new AtomicInteger(0);
    static AtomicInteger numberOfChecked4CharsStrings=new AtomicInteger(0);
    static AtomicInteger numberOfChecked5CharsStrings=new AtomicInteger(0);

    // общее количество слов, длина которых (3,4,5)
    static AtomicInteger totalAmount3CharsStrings=new AtomicInteger(0);
    static AtomicInteger totalAmount4CharsStrings=new AtomicInteger(0);
    static AtomicInteger totalAmount5CharsStrings=new AtomicInteger(0);

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    // проверка того, что слово является палиндромом
    public static boolean isPalindrome(String str) {
        int i=0;
        int n=str.length();
        while (i<n/2){
            if (str.charAt(i)!= str.charAt(n-i-1)) return false;
            i++;
        }
        return true;
    }

    // проверка на то, что слово состоит из одной и той же буквы, например, aaa
    public static boolean hasAllIdenticalChars(String str) {
        int i=1;
        int n=str.length();
        while (i<n){
            if (str.charAt(i-1)!= str.charAt(i)) return false;
            i++;
        }
        return true;
    }

    // проверка на то, что буквы в слове идут по возрастанию, например, aaccc.
    public static boolean hasAscendingSortedChars(String str) {
        int i=1;
        int n=str.length();
        while (i<n){
            if (str.charAt(i-1) > str.charAt(i)) return false;
            i++;
        }
        return true;
    }

    // метод проверки строк в массиве на соответствие трем критериям
    // аргументы метода:
    // входной массив строк;
    // требуемая длина строки (3,4 или 5);
    // общее число строк длина, которых равна требуемой длине;
    // счетчик строк, подходящих под критерии
    public static void checkString(String[]nickNames, int strLength, AtomicInteger totalAmount, AtomicInteger count) {
        int i=0;
        while (i< nickNames.length) {
            String str = nickNames[i++];
            if (str.length()==strLength) {
                totalAmount.getAndIncrement();
                if (isPalindrome(str) || hasAllIdenticalChars(str) || hasAscendingSortedChars(str))
                    count.getAndIncrement();
            }
        }
    }

    // переключатель между счетчиками
    public static AtomicInteger[] switchBetweenCounters(int strLength) {
        AtomicInteger[] atomics = new AtomicInteger[2];
        switch (strLength) {
            case 3:
                atomics[0]=totalAmount3CharsStrings;
                atomics[1]=numberOfChecked3CharsStrings;
                break;
            case 4:
                atomics[0]=totalAmount4CharsStrings;
                atomics[1]=numberOfChecked4CharsStrings;
                break;
            default:
                atomics[0]=totalAmount5CharsStrings;
                atomics[1]=numberOfChecked5CharsStrings;
                break;
        }
        return atomics;
    }

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }

        List<Thread> threads = new ArrayList<>();

        for (int i=3; i<6; i++) {
            int strLength = i;
            AtomicInteger totalAmount = switchBetweenCounters(strLength)[0];
            AtomicInteger count = switchBetweenCounters(strLength)[1];

            Thread thread = new Thread(()->checkString(texts, strLength,totalAmount,count));
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        for (int i=3; i<6; i++) {
            int strLength = i;
            AtomicInteger totalAmount = switchBetweenCounters(strLength)[0];
            AtomicInteger count = switchBetweenCounters(strLength)[1];

            System.out.println(String.format("Красивых слов с длиной %d: %d шт. Всего слов с длиной %d: %d шт."
                    ,i,count.get(),i,totalAmount.get()));
        }

    }
}
