import java.util.Iterator;
import java.util.Random;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Randoms implements Iterable<Integer> {
    private int min;
    private int max;
    protected Random random;

    public Randoms(int min, int max) {
        this.random = new Random();
        this.min = min;
        this.max = max;
    }


    @Override
    public Iterator<Integer> iterator() {
        Iterator<Integer> result = new Iterator<>() {

            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public Integer next() {
                if (this.hasNext()) return random.nextInt(min, max + 1);
                return null;
            }
        };
        return result;
    }

    @Override
    public void forEach(Consumer<? super Integer> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<Integer> spliterator() {
        return Iterable.super.spliterator();
    }

}
