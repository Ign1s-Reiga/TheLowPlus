package dev.reiga.thelowplus;

public enum NumeralsList {
    ONE(1, "Ⅰ"),
    TWO(2, "Ⅱ"),
    THREE(3, "Ⅲ"),
    FOUR(4, "Ⅳ"),
    FIVE(5, "Ⅴ"),
    SIX(6, "Ⅵ"),
    SEVEN(7, "Ⅶ"),
    EIGHT(8, "Ⅷ"),
    NINE(9, "Ⅸ"),
    TEN(10, "Ⅹ"),
    ELEVEN(11, "Ⅺ"),
    TWELVE(12, "Ⅻ");

    private final int arabicNumerals;
    private final String romeNumerals;

    NumeralsList(int arabicNumerals, String romeNumerals) {
        this.arabicNumerals = arabicNumerals;
        this.romeNumerals = romeNumerals;
    }

    public int getArabicNumerals() {
        return this.arabicNumerals;
    }

    public String getRomeNumerals() {
        return this.romeNumerals;
    }
}
