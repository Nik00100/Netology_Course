package equipment;

import properties.SportLevel;
import properties.SportType;
import properties.TennisBrand;

public class TennisEquipment extends SportEquipment {
    private final SportType SPORT_TYPE = SportType.TENNIS;
    private int id = 300;
    private boolean isEquipmentForClay;
    private String tennisBrand;

    public TennisEquipment(int rating, String equipment, String level, String material, String colour,
                           int weight, int price, boolean isEquipmentForClay, String tennisBrand) {
        super(rating, equipment, level, material, colour, weight, price);
        this.isEquipmentForClay = isEquipmentForClay;
        try {
            TennisBrand.valueOf(tennisBrand);
            this.tennisBrand = tennisBrand;
        } catch (IllegalArgumentException e) {
            System.out.println("Неверно указан производитель теннисного инвентаря");
        }
        this.id += super.getId();
    }

    @Override
    public int getId() {
        return id;
    }

    public boolean isEquipmentForClay() {
        return isEquipmentForClay;
    }

    public String getTennisBrand() {
        return tennisBrand;
    }

    @Override
    public SportType getSportType() {
        return SPORT_TYPE;
    }

    @Override
    public String toString() {
        return "TennisEquipment{" +
                "SPORT_TYPE=" + SPORT_TYPE +
                ", id=" + id +
                ", isEquipmentForClay=" + isEquipmentForClay + '\'' +
                ", tennisBrand='" + tennisBrand + '\'' +
                ", rating=" + rating +
                ", equipment='" + super.getEquipment() + '\'' +
                ", tennisLevel='" + super.getLevel() + '\'' +
                ", material='" + super.getMaterial() + '\'' +
                ", colour='" + super.getColour() + '\'' +
                ", weight=" + super.getWeight() +
                ", price=" + super.getPrice() +
                '}';
    }
}
