package org.behsadriemer.recipeasy;

import java.io.*;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;

public class Serializer {

    public static LinkedList<Recipe> convertJsonToLinkedList() {
        JsonArray recipeArray = returnRecipeArray();
        Recipe currentRecipe;
        LinkedList<Recipe> recipeList = new LinkedList<>();
        if(recipeArray.size() == 0){
            return recipeList;
        }
        for (int i = 0; i < recipeArray.size(); i++) {
            JsonObject currObject = (JsonObject) recipeArray.get(i);
            String name = currObject.get("name").getAsString();
            String type = currObject.get("type").getAsString();
            ArrayList<Ingredient> ingredientsList = jsonArraytoArrayList((JsonArray) currObject.get("ingredientsList"));

            currentRecipe = new Recipe(name, RecipeType.from(type));
            currentRecipe.replaceIngredientsList(ingredientsList);
            recipeList.add(currentRecipe);
        }
        return recipeList;
    }

    public static void writeRecipesFromLinkedList(LinkedList<Recipe> list) {
        String path = "recipes.json";
        String contents;
        try {
            contents = new String((Files.readAllBytes(Paths.get(path))));
            JsonObject jsonFileObject = JsonParser.parseString(contents).getAsJsonObject();
            jsonFileObject.remove("recipes");
            JsonArray recipes = new JsonArray();
            if(list.get(0) == null){
                replaceJsonDocument("{ \"recipes\" : []}");
            } else {
                for (Recipe recipe : list) {
                    JsonObject currentObject = returnRecipeJsonObject(recipe);
                    recipes.add(currentObject);
                }
                jsonFileObject.add("recipes", recipes);
                String output = jsonFileObject.toString();
                replaceJsonDocument(output);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void replaceJsonDocument(String jsonBody){
        Path path = Paths.get("recipes.json");
        try {
            Files.write(path, jsonBody.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JsonArray returnRecipeArray(){
        String path = "recipes.json"; 
        JsonArray recipeArray = new JsonArray();
        try {
            String contents = new String((Files.readAllBytes(Paths.get(path)))); 
            JsonObject jsonFileWithoutRecipe = JsonParser.parseString(contents).getAsJsonObject();
            recipeArray = jsonFileWithoutRecipe.get("recipes").getAsJsonArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return recipeArray;
    }

    public static ArrayList<Ingredient> jsonArraytoArrayList(JsonArray ingredientsArray){
        ArrayList<Ingredient> ingredientsList = new ArrayList<>();
        for(int i = 0; i < ingredientsArray.size(); i++){ 
            JsonObject currObject = (JsonObject) ingredientsArray.get(i);

            String name = currObject.get("name").getAsString();
            double mass = currObject.get("mass").getAsDouble();
            double multiplierConstant = mass/100;
            double water = currObject.get("water").getAsDouble();
            water = water/multiplierConstant;
            double kCals = currObject.get("kCals").getAsDouble();
            kCals = kCals/multiplierConstant;
            double proteins = currObject.get("proteins").getAsDouble();
            proteins = proteins/multiplierConstant;
            double carbohydrates = currObject.get("carbohydrates").getAsDouble();
            carbohydrates = carbohydrates/multiplierConstant;
            double fats = currObject.get("fats").getAsDouble();
            fats = fats/multiplierConstant;
            double sugars = currObject.get("sugars").getAsDouble();
            sugars = sugars/multiplierConstant;


            Ingredient currentIngredient = new Ingredient(name, mass, water, kCals, proteins, carbohydrates, fats, sugars);
            ingredientsList.add(currentIngredient);
        }
        return ingredientsList;
    }

    public static void removeRecipe(int index){
        LinkedList currentList = convertJsonToLinkedList();
        currentList.remove(index);
        writeRecipesFromLinkedList(currentList);
    }

    public static JsonObject appendRecipe(JsonObject jsonRecipe){
        String path = "recipes.json";
        JsonObject jsonFileWithRecipe = new JsonObject();

        try {
            String contents = new String((Files.readAllBytes(Paths.get(path))));
            JsonObject jsonFileWithoutRecipe = JsonParser.parseString(contents).getAsJsonObject();
            JsonArray recipes = jsonFileWithoutRecipe.get("recipes").getAsJsonArray();
            recipes.add(jsonRecipe);
            jsonFileWithoutRecipe.remove("recipes");
            jsonFileWithoutRecipe.add("recipes", recipes);
            jsonFileWithRecipe = jsonFileWithoutRecipe;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonFileWithRecipe;
    }

    public static JsonObject returnRecipeJsonObject(Recipe recipe){
        JsonObject jsonRecipe = new JsonObject();
        jsonRecipe.addProperty("type", recipe.getType().toString());
        jsonRecipe.addProperty("name", recipe.getName());
        JsonArray ingredientsForNewRecipe = convertArrayListToJSONArray(recipe.getFullIngredientList());
        jsonRecipe.add("ingredientsList", ingredientsForNewRecipe);
        return jsonRecipe;
    }

    public static void writeRecipe(Recipe recipe){
        Path path = Paths.get("recipes.json");
        JsonObject newRecipe = returnRecipeJsonObject(recipe);
        JsonObject wholeFile = appendRecipe(newRecipe);
        String output = wholeFile.toString();
        try {
            Files.write(path, output.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JsonArray convertArrayListToJSONArray(ArrayList<Ingredient> ingredientsList){
        JsonArray jsonIngredients = new JsonArray();
        for(int i = 0; i <= ingredientsList.size()-1;i++){
            JsonObject currentIngredient = new JsonObject();
            String name = "";
            String mass = "";
            String water = "";
            String kCals = "";
            String proteins = "";
            String carbohydrates = "";
            String fats = "";
            String sugars = "";

            name = ingredientsList.get(i).getName();
            mass = String.valueOf(ingredientsList.get(i).getMass());
            water = String.valueOf(ingredientsList.get(i).getWater());
            kCals = String.valueOf(ingredientsList.get(i).getkCals());
            proteins = String.valueOf(ingredientsList.get(i).getProteins());
            carbohydrates = String.valueOf(ingredientsList.get(i).getCarbohydrates());
            fats = String.valueOf(ingredientsList.get(i).getFats());
            sugars = String.valueOf(ingredientsList.get(i).getSugars());

            currentIngredient.addProperty("name", name);
            currentIngredient.addProperty("mass", mass);
            currentIngredient.addProperty("water", water);
            currentIngredient.addProperty("kCals", kCals);
            currentIngredient.addProperty("proteins", proteins);
            currentIngredient.addProperty("carbohydrates", carbohydrates);
            currentIngredient.addProperty("fats", fats);
            currentIngredient.addProperty("sugars", sugars);
            jsonIngredients.add(currentIngredient);
        }

        return jsonIngredients;
    }
}