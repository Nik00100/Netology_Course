package properties;

public enum FilterProperties {

    RATING("рейтинг"),
    EQUIPMENT("название"),
    PRICE("цена");
    private final String description;

    FilterProperties(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
