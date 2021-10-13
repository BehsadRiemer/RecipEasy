package org.behsadriemer.recipeasy;

import java.io.*;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/*
Parsing JSON files:
https://www.youtube.com/watch?v=6IGl4Tf2VVI&ab_channel=Tabnine

NIO for filewriting:
https://examples.javacodegeeks.com/core-java/nio/file-nio/java-nio-write-file-example/

IO Documentation:
https://docs.oracle.com/javase/7/docs/api/java/io/package-summary.html

Converting bigDecimals to double:
https://stackoverflow.com/questions/19650917/how-to-convert-bigdecimal-to-double-in-java
*/

//All methods relating to serializing and deserializing any recipes and ingredients that the user has created locally.
public class serialize {

    public static linkedList convertJsonToLinkedList() {
        JsonArray recipeArray = returnRecipeArray(); //Reads the file by returning the JSON array of recipes
        recipe currentRecipe;
        recipe firstRecipe = new dessert("tempRecipe"); //Initialises temporary org.behsadriemer.recipeasy.recipe to add a head to the linked list
        node head = new node(firstRecipe); //Instantiates a org.behsadriemer.recipeasy.node storing the temporary org.behsadriemer.recipeasy.recipe
        node currentNode = new node(null);
        linkedList recipeList = new linkedList(); //Instantiates new linked list
        if(recipeArray.size() == 0){ //Returns an empty linked list if the org.behsadriemer.recipeasy.recipe array is empty
            return recipeList;
        }
        recipeList.setHead(head); //Sets the head (containing the temporary org.behsadriemer.recipeasy.recipe which will be replaced)
        //Iterates through the JSON array, converting each JSON object to a org.behsadriemer.recipeasy.recipe instance and adding it to the linked list
        for (int i = 0; i < recipeArray.size(); i++) {
            JsonObject currObject = (JsonObject) recipeArray.get(i); //Reads the current JSON object
            String name = currObject.get("name").getAsString(); // initialises name to the name of the current json object
            String type = currObject.get("type").getAsString(); // initialises type to the name of the current json object
            //Converts the JSONArray storing the ingredients to an arraylist holding org.behsadriemer.recipeasy.ingredient objects
            ArrayList<ingredient> ingredientsList = jsonArraytoArrayList((JsonArray) currObject.get("ingredientsList"));

            switch (type) {
                case "starter" -> {
                    currentRecipe = new starter(name);
                    currentRecipe.setType(type);
                    currentRecipe.replaceIngredientsList(ingredientsList);
                }
                case "sideDish" -> {
                    currentRecipe = new sideDish(name);
                    currentRecipe.setType(type);
                    currentRecipe.replaceIngredientsList(ingredientsList);
                }
                case "mainDish" -> {
                    currentRecipe = new mainDish(name);
                    currentRecipe.setType(type);
                    currentRecipe.replaceIngredientsList(ingredientsList);
                }
                case "dessert" -> {
                    currentRecipe = new dessert(name);
                    currentRecipe.setType(type);
                    currentRecipe.replaceIngredientsList(ingredientsList);
                }
                default -> {
                    currentRecipe = new beverage(name);
                    currentRecipe.setType(type);
                    currentRecipe.replaceIngredientsList(ingredientsList);
                }
            }
            //Adds the org.behsadriemer.recipeasy.recipe to the current org.behsadriemer.recipeasy.node to the next pointer
            if (i == 0) {
                head.setData(currentRecipe);
                currentNode = head;
            } else {
                node nextNode = new node(currentRecipe);
                currentNode.setNext(nextNode);
                currentNode = nextNode;
            }
        }
        return recipeList; //Returns the linked list
    }

    //Replaces the whole contents of the file using the linked list
    public static void writeRecipesFromLinkedList(linkedList list) {
        String path = "recipes.json"; //Relative file path of the file that is being written to
        String contents; //Declares variable to store the bytes in the whole document.
        node currentNode = list.getHead();
        try { //Tries to do the following
            contents = new String((Files.readAllBytes(Paths.get(path)))); //Converts JSON file to String
            JsonObject jsonFileObject = JsonParser.parseString(contents).getAsJsonObject(); //Converts the String to an object
            jsonFileObject.remove("recipes"); //removes the JSONArray recipes which stores org.behsadriemer.recipeasy.recipe objects
            JsonArray recipes = new JsonArray(); //Creates a new array to store the new recipes
            if(list.getHead() == null){
                replaceJsonDocument("{ \"recipes\" : []}"); //If the linked list is empty, the array is empty
            }
            else{
                //If linked list is not empty, Appends each org.behsadriemer.recipeasy.recipe object to the json array
                for(int i = 0; i < list.size(); i++){
                    JsonObject currentObject = returnRecipeJsonObject(currentNode.getData());
                    recipes.add(currentObject);
                    currentNode = currentNode.getNext();
                }
                jsonFileObject.add("recipes", recipes); //Adds the json array to the json object with key "recipes"
                String output = jsonFileObject.toString(); //Converts the object to string
                replaceJsonDocument(output); //Replaces the file by overwriting the document with output
            }

        } catch (IOException e) { //If there is an input or output error, the error is printed 
            e.printStackTrace();
        }
    }

    //Overwrites the JSON document given the String to overwrite it with
    public static void replaceJsonDocument(String jsonBody){
        Path path = Paths.get("recipes.json");
        try { //Tries to do the following
            Files.write(path, jsonBody.getBytes());
        } catch (IOException e) { //Given an an input or output error,e, the error is printed 
            e.printStackTrace();
        }
    }

    //Reads the whole file and returns the json array "recipes"
    public static JsonArray returnRecipeArray(){
        String path = "recipes.json"; 
        JsonArray recipeArray = new JsonArray(); //Instantiates JSON array
        try { //Tries to do the following
            String contents = new String((Files.readAllBytes(Paths.get(path)))); 
            JsonObject jsonFileWithoutRecipe = JsonParser.parseString(contents).getAsJsonObject(); //Converts String to JSON object
            recipeArray = jsonFileWithoutRecipe.get("recipes").getAsJsonArray(); //Initialises JSON array to the recipes JSON array
        } catch (IOException e) { //If there is an input or output error, the error is printed 
            e.printStackTrace();
        }
        return recipeArray;
    }

    public static ArrayList<ingredient> jsonArraytoArrayList(JsonArray ingredientsArray){
        ArrayList<ingredient> ingredientsList = new ArrayList<>(); //Declares an arraylist
        //Iterates through the JSON array, converting each quantity to a double and using it 
        //to initialise an org.behsadriemer.recipeasy.ingredient and add it to the arraylist
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


            ingredient currentIngredient = new ingredient(name, mass, water, kCals, proteins, carbohydrates, fats, sugars);
            ingredientsList.add(currentIngredient);
        }
        return ingredientsList;
    }

    public static void removeRecipe(int index){
        linkedList currentList = convertJsonToLinkedList();
        currentList.removeAtIndex(index);
        writeRecipesFromLinkedList(currentList);
    }

    //Adds a org.behsadriemer.recipeasy.recipe json object to the end of the json array
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

    //Converts a org.behsadriemer.recipeasy.recipe to a JSON object
    public static JsonObject returnRecipeJsonObject(recipe recipe){
        JsonObject jsonRecipe = new JsonObject();
        jsonRecipe.addProperty("type", recipe.getType());
        jsonRecipe.addProperty("name", recipe.getName());
        JsonArray ingredientsForNewRecipe = convertArrayListToJSONArray(recipe.getFullIngredientList());
        jsonRecipe.add("ingredientsList", ingredientsForNewRecipe);
        return jsonRecipe;
    }

    //Writes a org.behsadriemer.recipeasy.recipe to the json document
    public static void writeRecipe(recipe recipe){
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

    //Method that converts an arraylist of org.behsadriemer.recipeasy.ingredient object to a JSON array
    public static JsonArray convertArrayListToJSONArray(ArrayList<ingredient> ingredientsList){
        JsonArray jsonIngredients = new JsonArray(); //Instantiates JSON array
        //Iterates through the arraylist, using variables for each object to create a JSON object
        // and appending the JSON object to the 'jsonIngredients' array
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