package properties;

public enum SportType {

    TENNIS("теннис"),
    FOOTBALL("футбол"),
    HOCKEY("хоккей");

    private final String sportType;

    SportType(String sportType) {
        this.sportType = sportType;
    }

    public String getDescription() {
        return sportType;
    }
}
