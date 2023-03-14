package equipment;

import properties.HockeyBrand;
import properties.SportType;
import properties.TennisBrand;

public class HockeyEquipment extends SportEquipment {
    private final SportType SPORT_TYPE = SportType.HOCKEY;
    private int id = 200;
    private String hockeyBrand;

    public HockeyEquipment(int rating, String equipment, String level, String material, String colour,
                           int weight, int price, String hockeyBrand) {
        super(rating, equipment, level, material, colour, weight, price);
        try {
            HockeyBrand.valueOf(hockeyBrand);
            this.hockeyBrand = hockeyBrand;
        } catch (IllegalArgumentException e) {
            System.out.println("Неверно указан производитель хокейного инвентаря");
        }
        this.id += super.getId();
    }

    @Override
    public int getId() {
        return id;
    }

    public String getHockeyBrand() {
        return hockeyBrand;
    }

    @Override
    public SportType getSportType() {
        return SPORT_TYPE;
    }

    @Override
    public String toString() {
        return "HockeyEquipment{" +
                "SPORT_TYPE=" + SPORT_TYPE +
                ", id=" + id +
                ", hockeyBrand='" + hockeyBrand + '\'' +
                ", rating=" + rating +
                ", equipment='" + super.getEquipment() + '\'' +
                ", hockeyLevel='" + super.getLevel() + '\'' +
                ", material='" + super.getMaterial() + '\'' +
                ", colour='" + super.getColour() + '\'' +
                ", weight=" + super.getWeight() +
                ", price=" + super.getPrice() +
                '}';
    }
}
