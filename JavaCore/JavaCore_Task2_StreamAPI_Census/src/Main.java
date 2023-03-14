import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();

        for (int i = 0; i < 1000000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        System.out.printf("Количество несовершеннолетних: %d",
                persons.stream().filter(p -> p.getAge() < 18).count());
        System.out.println();

        System.out.println("Cписок фамилий призывников:");
        persons.stream().
                filter(p -> p.getSex() == Sex.MAN).
                filter(p -> p.getAge() >= 18 && p.getAge() <=27).
                map(p -> p.getFamily()).
                collect(Collectors.toList()).
                forEach(System.out::println);

        System.out.println("Cписок потенциально работоспособных людей с высшим образованием:");
        Comparator<Person> byFamily = Comparator.comparing(person -> person.getFamily());
        persons.stream().
                filter(p -> p.getEducation() == Education.HIGHER).
                filter(p -> p.getSex() == Sex.MAN
                        ? p.getAge() >= 18 && p.getAge() <= 65
                        : p.getAge() >= 18 && p.getAge() <= 60).
                sorted(byFamily).
                forEach(System.out::println);
    }

}
