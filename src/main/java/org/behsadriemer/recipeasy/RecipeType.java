package org.behsadriemer.recipeasy;

public enum RecipeType {
    BEVERAGE("beverage"),
    DESSERT("dessert"),
    MAIN("main dish"),
    SIDE("side dish"),
    STARTER("starter");

    String type;
    RecipeType(String type) {
        this.type = type;
    }

    public static RecipeType from(String name) {
        return switch (name) {
            case "beverage" -> BEVERAGE;
            case "dessert" -> DESSERT;
            case "main dish" -> MAIN;
            case "side dish" -> SIDE;
            case "starter" -> STARTER;
            default -> null;
        };
    }

    @Override
    public String toString() {
        return type;
    }
}
