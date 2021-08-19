import java.io.*;

import org.json.JSONArray;
import org.json.JSONObject;
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
        JSONArray recipeArray = returnRecipeArray(); //Reads the file by returning the JSON array of recipes
        recipe currentRecipe;
        recipe firstRecipe = new dessert("tempRecipe"); //Initialises temporary recipe to add a head to the linked list
        node head = new node(firstRecipe); //Instantiates a node storing the temporary recipe
        node currentNode = new node(null);
        linkedList recipeList = new linkedList(); //Instantiates new linked list
        if(recipeArray.length() == 0){ //Returns an empty linked list if the recipe array is empty
            return recipeList;
        }
        recipeList.setHead(head); //Sets the head (containing the temporary recipe which will be replaced)
        //Iterates through the JSON array, converting each JSON object to a recipe instance and adding it to the linked list
        for (int i = 0; i < recipeArray.length(); i++) {
            JSONObject currObject = (JSONObject) recipeArray.get(i); //Reads the current JSON object
            String name = (String) currObject.get("name"); // initialises name to the name of the current json object
            String type = (String) currObject.get("type"); // initialises type to the name of the current json object
            //Converts the JSONArray storing the ingredients to an arraylist holding ingredient objects
            ArrayList<ingredient> ingredientsList = jsonArraytoArrayList((JSONArray) currObject.get("ingredientsList"));

            //Conditionals for understanding what type of ingredient is to be instantiated and copies the data to it
            if (type == "starter") {
                currentRecipe = new starter(name);
                currentRecipe.setType(type);
                currentRecipe.replaceIngredientsList(ingredientsList);
            } else if (type == "sideDish") {
                currentRecipe = new sideDish(name);
                currentRecipe.setType(type);
                currentRecipe.replaceIngredientsList(ingredientsList);
            } else if (type == "mainDish") {
                currentRecipe = new mainDish(name);
                currentRecipe.setType(type);
                currentRecipe.replaceIngredientsList(ingredientsList);
            } else if (type == "dessert") {
                currentRecipe = new dessert(name);
                currentRecipe.setType(type);
                currentRecipe.replaceIngredientsList(ingredientsList);
            } else {
                currentRecipe = new beverage(name);
                currentRecipe.setType(type);
                currentRecipe.replaceIngredientsList(ingredientsList);
            }
            //Adds the recipe to the current node to the next pointer
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
            JSONObject jsonFileObject = new JSONObject(contents); //Converts the String to an object
            jsonFileObject.remove("recipes"); //removes the JSONArray recipes which stores recipe objects
            JSONArray recipes = new JSONArray(); //Creates a new array to store the new recipes
            if(list.getHead() == null){
                replaceJsonDocument("{ \"recipes\" : []}"); //If the linked list is empty, the array is empty
            }
            else{
                //If linked list is not empty, Appends each recipe object to the json array
                for(int i = 0; i < list.size(); i++){
                    JSONObject currentObject = returnRecipeJsonObject(currentNode.getData());
                    recipes.put(i, currentObject);
                    currentNode = currentNode.getNext();
                }
                jsonFileObject.put("recipes", recipes); //Adds the json array to the json object with key "recipes"
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
    public static JSONArray returnRecipeArray(){
        String path = "recipes.json"; 
        JSONArray recipeArray = new JSONArray(); //Instantiates JSON array
        try { //Tries to do the following
            String contents = new String((Files.readAllBytes(Paths.get(path)))); 
            JSONObject jsonFileWithoutRecipe = new JSONObject(contents); //Converts String to JSON object
            recipeArray = jsonFileWithoutRecipe.getJSONArray("recipes"); //Initialises JSON array to the recipes JSON array
        } catch (IOException e) { //If there is an input or output error, the error is printed 
            e.printStackTrace();
        }
        return recipeArray;
    }

    public static ArrayList<ingredient> jsonArraytoArrayList(JSONArray ingredientsArray){
        ArrayList<ingredient> ingredientsList = new ArrayList<ingredient>(); //Declares an arraylist
        //Iterates through the JSON array, converting each quantity to a double and using it 
        //to initialise an ingredient and add it to the arraylist
        for(int i = 0; i < ingredientsArray.length(); i++){ 
            JSONObject currObject = (JSONObject) ingredientsArray.get(i);

            String name = (String) currObject.get("name");
            Double mass = Double.parseDouble((String) currObject.get("mass"));
            Double multiplierConstant = mass/100;
            Double water = Double.parseDouble((String) currObject.get("water"));
            water = water/multiplierConstant;
            Double kCals = Double.parseDouble((String) currObject.get("kCals"));
            kCals = kCals/multiplierConstant;
            Double proteins = Double.parseDouble((String) currObject.get("proteins"));
            proteins = proteins/multiplierConstant;
            Double carbohydrates = Double.parseDouble((String) currObject.get("carbohydrates"));
            carbohydrates = carbohydrates/multiplierConstant;
            Double fats = Double.parseDouble((String) currObject.get("fats"));
            fats = fats/multiplierConstant;
            Double sugars = Double.parseDouble((String) currObject.get("sugars"));
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

    //Adds a recipe json object to the end of the json array
    public static JSONObject appendRecipe(JSONObject jsonRecipe){
        String path = "recipes.json";
        JSONObject jsonFileWithRecipe = new JSONObject();

        try {
            String contents = new String((Files.readAllBytes(Paths.get(path))));
            JSONObject jsonFileWithoutRecipe = new JSONObject(contents);
            JSONArray recipes = jsonFileWithoutRecipe.getJSONArray("recipes");
            recipes.put(jsonRecipe);
            jsonFileWithoutRecipe.remove("recipes");
            jsonFileWithoutRecipe.put("recipes", recipes);
            jsonFileWithRecipe = jsonFileWithoutRecipe;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonFileWithRecipe;
    }

    //Converts a recipe to a JSON object
    public static JSONObject returnRecipeJsonObject(recipe recipe){
        JSONObject jsonRecipe = new JSONObject();
        jsonRecipe.put("type", recipe.getType());
        jsonRecipe.put("name", recipe.getName());
        JSONArray ingredientsForNewRecipe = convertArrayListToJSONArray(recipe.getFullIngredientList());
        jsonRecipe.put("ingredientsList", ingredientsForNewRecipe);
        return jsonRecipe;
    }

    //Writes a recipe to the json document
    public static void writeRecipe(recipe recipe){
        Path path = Paths.get("recipes.json");
        JSONObject newRecipe = returnRecipeJsonObject(recipe);
        JSONObject wholeFile = appendRecipe(newRecipe);
        String output = wholeFile.toString();
        try {
            Files.write(path, output.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Method that converts an arraylist of ingredient object to a JSON array
    public static JSONArray convertArrayListToJSONArray(ArrayList<ingredient> ingredientsList){
        JSONArray jsonIngredients = new JSONArray(); //Instantiates JSON array
        //Iterates through the arraylist, using variables for each object to create a JSON object
        // and appending the JSON object to the 'jsonIngredients' array
        for(int i = 0; i <= ingredientsList.size()-1;i++){
            JSONObject currentIngredient = new JSONObject();
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

            currentIngredient.put("name", name);
            currentIngredient.put("mass", mass);
            currentIngredient.put("water", water);
            currentIngredient.put("kCals", kCals);
            currentIngredient.put("proteins", proteins);
            currentIngredient.put("carbohydrates", carbohydrates);
            currentIngredient.put("fats", fats);
            currentIngredient.put("sugars", sugars);
            jsonIngredients.put(currentIngredient);
        }

        return jsonIngredients;
    }



}