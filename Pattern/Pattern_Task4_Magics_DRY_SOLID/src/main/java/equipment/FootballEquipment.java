package equipment;

import properties.FootballBrand;
import properties.SportType;

public class FootballEquipment extends SportEquipment {
    private final SportType SPORT_TYPE = SportType.FOOTBALL;
    private int id = 100;
    private boolean isOutdoor;
    private String footballBrand;

    public FootballEquipment(int rating, String equipment, String level, String material, String colour,
                             int weight, int price, boolean isOutdoor, String footballBrand) {
        super(rating, equipment, level, material, colour, weight, price);
        this.isOutdoor = isOutdoor;
        try {
            FootballBrand.valueOf(footballBrand);
            this.footballBrand = footballBrand;
        } catch (IllegalArgumentException e) {
            System.out.println("Неверно указан производитель футбольного инвентаря");
        }
        this.id += super.getId();
    }

    @Override
    public int getId() {
        return id;
    }

    public boolean isOutdoor() {
        return isOutdoor;
    }

    public String getFootballBrand() {
        return footballBrand;
    }

    @Override
    public SportType getSportType() {
        return SPORT_TYPE;
    }

    @Override
    public String toString() {
        return "FootballEquipment{" +
                "SPORT_TYPE=" + SPORT_TYPE +
                ", id=" + id +
                ", isOutdoor=" + isOutdoor +
                ", footballBrand='" + footballBrand + '\'' +
                ", rating=" + rating +
                ", equipment='" + super.getEquipment() + '\'' +
                ", footballLevel='" + super.getLevel() + '\'' +
                ", material='" + super.getMaterial() + '\'' +
                ", colour='" + super.getColour() + '\'' +
                ", weight=" + super.getWeight() +
                ", price=" + super.getPrice() +
                '}';
    }
}
