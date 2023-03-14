package equipment;

import properties.SportLevel;
import properties.SportType;


public abstract class SportEquipment implements TypeOfSport {

    protected static int id;
    protected int rating;
    private String equipment;
    private String level;
    private String material;
    private String colour;
    private int weight;
    private int price;

    protected SportEquipment() {
    }

    public SportEquipment(int rating, String equipment, String level, String material, String colour, int weight, int price) {
        this.rating = rating;
        this.equipment = equipment;
        try {
            SportLevel.valueOf(level);
            this.level = level;
        } catch (IllegalArgumentException e) {
            System.out.println("Неверно указан уровень подготовки");
        }
        this.material = material;
        this.colour = colour;
        this.price = price;
        this.weight = weight;
        ++id;

    }

    @Override
    public abstract SportType getSportType();

    public int getId() {
        return id;
    }

    public void setRating(int value) {
        if (rating != 0) {
            this.rating = (rating + value) / 2;
        } else this.rating = value;
        System.out.println("Спасибо за оценку!");
    }

    public int getRating() {
        return rating;
    }

    public String getEquipment() {
        return equipment;
    }

    public String getLevel() {
        return level;
    }

    public String getMaterial() {
        return material;
    }

    public String getColour() {
        return colour;
    }

    public int getWeight() {
        return weight;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "SportEquipment{" +
                "rating=" + rating +
                ", equipment='" + equipment + '\'' +
                ", material='" + material + '\'' +
                ", colour='" + colour + '\'' +
                ", weight=" + weight +
                ", price=" + price +
                '}';
    }
}
