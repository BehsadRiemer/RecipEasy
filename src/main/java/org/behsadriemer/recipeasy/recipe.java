package org.behsadriemer.recipeasy;/*
    SOURCES: 
    Hashmap
    - youtube.com/watch?v=YFPVyj_aP64&ab_channel=TechWithTim
*/

import java.util.*;

//Abstract Superclass Recipe
public abstract class recipe{

    // Variable and Constant Declarations\\
    private String name = null;
    //Arraylist of ingredients. It needed to be dynamic because it grows according to the users needs.
    private ArrayList<ingredient> ingredientsList = new ArrayList<ingredient>();
    //The total mass of the whole org.behsadriemer.recipeasy.recipe
    private Double totalMass = null;
    //The index that lets the user know how balanced a diet is. This will range between 0 and 100.
    private Double balanceIndex = null;
    //Type - indicates the type of instance it is.
    private String type = null;
    //Hashmap that stores the total nutrients.
    private Map nutrients = new LinkedHashMap();
    
    //Constructor: Allows for instantiating objects of the org.behsadriemer.recipeasy.recipe class. Initialises the name, org.behsadriemer.recipeasy.recipe type
    //and total nutrients
    public recipe(String name){
        this.name = name;
        this.type = "recipe";
        this.nutrients.put("mass", 0.0);
        this.nutrients.put("water", 0.0);
        this.nutrients.put("kCals", 0.0);
        this.nutrients.put("proteins", 0.0);
        this.nutrients.put("carbohydrates", 0.0);
        this.nutrients.put("fats", 0.0);
        this.nutrients.put("sugars", 0.0);
    }

    //Getters: Methods that return the name, mass and balanceIndex of an instance 
    public String getName(){
        return this.name;
    }
    public Double getNutrient(String key){
        compileNutrients();
        double nutrient = (double) this.nutrients.get(key);
        return nutrient;
    }
    public String getType(){
        return this.type;
    }
    public ArrayList<ingredient> getFullIngredientList(){
        return this.ingredientsList;
    }
    //Calculates total mass - avoids summing all nutrients to save memory and time
    public Double getTotalMass(){
        for(int i = 0; i <= ingredientsList.size()-1; i++){
            this.totalMass += ingredientsList.get(i).getMass();
        }
        return this.totalMass;
    }
    //Method that returns an org.behsadriemer.recipeasy.ingredient stored by this org.behsadriemer.recipeasy.recipe instance in the arraylist, "ingredientslist"
    public ingredient getIngredientAtIndex(int index){
        ingredient tempIngredient;
        if(index > this.ingredientsList.size()){
            System.out.println("Heads up, index is too high and doesn't exist. Largest index was returned: ");
            tempIngredient = this.ingredientsList.get(ingredientsList.size()-1);
        }
        else{
            tempIngredient = this.ingredientsList.get(index);    
        }
        return tempIngredient;
    }

    //Setters: Change the name,type, append an org.behsadriemer.recipeasy.ingredient or replace the arraylist of ingredients
    public void setName(String name){
        this.name = name;
    }
    public void setType(String type){
        this.type = type;
    }
    public void appendIngredient(ingredient newIngredient){
        this.ingredientsList.add(newIngredient);
    }
    public void replaceIngredientsList(ArrayList<ingredient> list){
        this.ingredientsList.clear();
        this.ingredientsList = list;
    }
    public void removeIngredientAtIndex(int index){
        this.ingredientsList.remove(index);
    }
    public void removeIngredient(ingredient ingredient){
        for(int i = 0; i < this.ingredientsList.size(); i++){
            if(ingredientsList.get(i) == ingredient){
                ingredientsList.remove(i);
                break;
            }
        }
    }

    //Calculates balance index (no need for setter)
    public Double getBalanceIndex(){
        compileNutrients(); //Sums all nutrients to ensure the nutrients fetched are up to date.
        double proteins = (double) this.nutrients.get("proteins");
        double carbohydrates = (double) this.nutrients.get("carbohydrates");
        double fats = (double) this.nutrients.get("fats");
        //Error handling for if there has not been any 
        if(proteins == 0.0 && carbohydrates == 0.0 && fats == 0.0){
            return 0.0;
        }

        double totalMacronutrientsMass = proteins + carbohydrates + fats; //the total mass of proteins, carbohydrates and fats

        double percentOfProteins = (proteins/totalMacronutrientsMass); //The decimal value representing the percentage of the org.behsadriemer.recipeasy.recipe that each nutrient makes up
        double percentOfCarbohydrates = (carbohydrates/totalMacronutrientsMass);
        double percentOfFats = (fats/totalMacronutrientsMass);

        double idealProteins = 0.2; //Decimal values representing the ideal percentage composition of each macronutrient
        double idealCarbohydrates = 0.4;
        double idealFats = 0.4;

        double proteinsWeight = 5; //Weights representing the importances of having ideal 
        double carbohydratesWeight = 8;
        double fatsWeight = 3;

        double percentageDifferenceOfProteins = (proteinsWeight*(1-(Math.abs(percentOfProteins-idealProteins)/idealProteins))); //Weighted percentage difference 
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

        //Sums all the weighted total percentage differences
        double totalPercentDifferences = percentageDifferenceOfProteins+percentageDifferenceOfCarbohydrates+percentageDifferenceOfFats;
        double totalWeights = proteinsWeight+carbohydratesWeight+fatsWeight; //Sums all weights
        //Creates the balance index which indicates how close, to the ideal percentage compositions of macronutrients, the org.behsadriemer.recipeasy.recipe is.
        this.balanceIndex = Math.abs((totalPercentDifferences/totalWeights)*100);

        return this.balanceIndex;
    }

    //Compiles all the nutrients into the hashmap
    public void compileNutrients(){
        Double totalMass = 0.0;
        Double totalWater = 0.0;
        Double totalkCals = 0.0;
        Double totalProteins = 0.0;
        Double totalCarbohydrates = 0.0;
        Double totalFats = 0.0;
        Double totalSugars = 0.0;

        for(int i = 0; i < this.ingredientsList.size(); i++){
            totalMass += ingredientsList.get(i).getMass();
            totalWater += ingredientsList.get(i).getWater();
            totalkCals += ingredientsList.get(i).getkCals();
            totalProteins += ingredientsList.get(i).getProteins();
            totalCarbohydrates += ingredientsList.get(i).getCarbohydrates();
            totalFats += ingredientsList.get(i).getFats();
            totalSugars += ingredientsList.get(i).getSugars();
        }
        nutrients.put("mass", totalMass);
        nutrients.put("water", totalWater);
        nutrients.put("kCals", totalkCals);
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

}