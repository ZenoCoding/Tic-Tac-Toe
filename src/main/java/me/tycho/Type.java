package me.tycho;

/**
 * Overcomplicated enum to store game state with integer values to allow for minimax implementation?
 */
public enum Type {
    EMPTY(0, " "), X(-1, "X"), O(1, "O");

    public final int value;
    public final String display;

    Type(int value, String display){
        this.value = value;
        this.display = display;
    }

    /**
     * Converts integer to Type
     * @param i the integer value
     * @return the corresponding enum value
     */
    public static Type of(int i) {
        return switch(i){
            case 0 -> EMPTY;
            case -1 -> X;
            case 1 -> O;
            default -> throw new IllegalStateException("Unexpected value: " + i);
        };
    }

    @Override
    public String toString() {
        return display;
    }
}
