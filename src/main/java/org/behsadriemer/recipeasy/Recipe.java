package org.behsadriemer.recipeasy;

import java.util.*;

public class Recipe implements Comparable<Recipe> {

    private String name;
    private ArrayList<Ingredient> ingredientsList = new ArrayList<>();
    private final RecipeType type;
    private final Map<String, Double> nutrients = new LinkedHashMap<>();

    public Recipe(String name, RecipeType type){
        this.name = name;
        this.type = type;
        this.nutrients.put("mass", 0.0);
        this.nutrients.put("water", 0.0);
        this.nutrients.put("kCals", 0.0);
        this.nutrients.put("proteins", 0.0);
        this.nutrients.put("carbohydrates", 0.0);
        this.nutrients.put("fats", 0.0);
        this.nutrients.put("sugars", 0.0);
    }

    public String getName(){
        return this.name;
    }

    public Double getNutrient(String key){
        compileNutrients();
        return this.nutrients.get(key);
    }

    public RecipeType getType(){
        return this.type;
    }

    public ArrayList<Ingredient> getFullIngredientList(){
        return this.ingredientsList;
    }

    public void setName(String name){
        this.name = name;
    }

    public void appendIngredient(Ingredient newIngredient){
        this.ingredientsList.add(newIngredient);
    }

    public void replaceIngredientsList(ArrayList<Ingredient> list){
        this.ingredientsList.clear();
        this.ingredientsList = list;
    }
    public void removeIngredientAtIndex(int index){
        this.ingredientsList.remove(index);
    }

    public Double getBalanceIndex() {
        compileNutrients();
        double proteins = this.nutrients.get("proteins");
        double carbohydrates = this.nutrients.get("carbohydrates");
        double fats = this.nutrients.get("fats");
        if(proteins == 0.0 && carbohydrates == 0.0 && fats == 0.0){
            return 0.0;
        }

        double totalMacronutrientsMass = proteins + carbohydrates + fats;

        double percentOfProteins = (proteins/totalMacronutrientsMass);
        double percentOfCarbohydrates = (carbohydrates/totalMacronutrientsMass);
        double percentOfFats = (fats/totalMacronutrientsMass);

        double idealProteins = 0.2;
        double idealCarbohydrates = 0.4;
        double idealFats = 0.4;

        double proteinsWeight = 5;
        double carbohydratesWeight = 8;
        double fatsWeight = 3;

        double percentageDifferenceOfProteins = (proteinsWeight*(1-(Math.abs(percentOfProteins-idealProteins)/idealProteins)));
        double percentageDifferenceOfCarbohydrates = (carbohydratesWeight*(1-(Math.abs(percentOfCarbohydrates-idealCarbohydrates)/idealCarbohydrates)));
        double percentageDifferenceOfFats = (fatsWeight*(1-(Math.abs(percentOfFats-idealFats)/idealFats)));

        if(proteins == 0.0){
            percentageDifferenceOfProteins = 0;
        }
        if(carbohydrates == 0.0){
            percentageDifferenceOfProteins = 0;
        }
        if(proteins == 0.0){
            percentageDifferenceOfProteins = 0;
        }

        double totalPercentDifferences = percentageDifferenceOfProteins+percentageDifferenceOfCarbohydrates+percentageDifferenceOfFats;
        double totalWeights = proteinsWeight+carbohydratesWeight+fatsWeight;
        return Math.abs((totalPercentDifferences / totalWeights) * 100);
    }

    public void compileNutrients(){
        double totalMass = 0.0;
        double totalWater = 0.0;
        double totalKCals = 0.0;
        double totalProteins = 0.0;
        double totalCarbohydrates = 0.0;
        double totalFats = 0.0;
        double totalSugars = 0.0;

        for (Ingredient ingredient : this.ingredientsList) {
            totalMass += ingredient.getMass();
            totalWater += ingredient.getWater();
            totalKCals += ingredient.getkCals();
            totalProteins += ingredient.getProteins();
            totalCarbohydrates += ingredient.getCarbohydrates();
            totalFats += ingredient.getFats();
            totalSugars += ingredient.getSugars();
        }
        nutrients.put("mass", totalMass);
        nutrients.put("water", totalWater);
        nutrients.put("kCals", totalKCals);
        nutrients.put("proteins", totalProteins);
        nutrients.put("carbohydrates", totalCarbohydrates);
        nutrients.put("fats", totalFats);
        nutrients.put("sugars", totalSugars);
    }

    @Override
    public String toString() {
        String recipeName = this.name + " - ";
        String balanceIndex = String.format("%.0f", getBalanceIndex());
        return recipeName+balanceIndex;
    }

    @Override
    public int compareTo(Recipe o) {
        return Double.compare(this.getBalanceIndex(), o.getBalanceIndex());
    }
}