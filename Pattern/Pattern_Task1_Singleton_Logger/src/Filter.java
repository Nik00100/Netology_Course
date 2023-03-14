import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Filter {
    protected int treshold;

    public Filter(int treshold) {
        this.treshold = treshold;
    }

    public List<Integer> filterOut(List<Integer> source) {
        Logger logger = Logger.getInstance();
        logger.log("Запускаем фильтрацию");
        List<Integer> result = new ArrayList<>();
        result = source.stream()
                .peek(num->{
                    if (num>treshold) logger.log(String.format("Элемент '%d' проходит",num));
                    else logger.log(String.format("Элемент '%d' не проходит",num));
                })
                .filter(num-> num>treshold)
                .sorted()
                .collect(Collectors.toList());
        logger.log(String.format("Прошло фильтр %d элемента из %d",result.size(),source.size()));
        return result;
    }
}