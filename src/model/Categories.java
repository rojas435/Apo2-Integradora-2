package model;

public enum Categories {
    BOOKS, ELECTRONICS, CLOTHES, ACCESORIES, FOOD, DRINKS, STATIONERY, SPORTS, BEAUTY, PERSONAL, TOYS, GAMES, KIDS;
    public static Categories fromInt(int value) {
        switch (value) {
            case 1: return BOOKS;
            case 2: return ELECTRONICS;
            case 3: return CLOTHES;
            case 4: return ACCESORIES;
            case 5: return FOOD;
            case 6: return DRINKS;
            case 7: return STATIONERY;
            case 8: return SPORTS;
            case 9: return BEAUTY;
            case 10: return PERSONAL;
            case 11: return TOYS;
            case 12: return GAMES;
            case 13: return KIDS;
            default:
                throw new IllegalArgumentException("Invalid integer value for Categories: " + value);
        }
    }
}
