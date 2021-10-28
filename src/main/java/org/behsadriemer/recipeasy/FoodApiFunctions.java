package org.behsadriemer.recipeasy;

import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class FoodApiFunctions {
    public static Ingredient requestIngredientWithId(int id){
        HttpClient client = HttpClient.newHttpClient();
        String requestLink = "https://api.nal.usda.gov/fdc/v1/food/" + id + 
        "?api_key=PLACEKEYHERE&query&nutrients=255&nutrients=208&nutrients=203&nutrients=956&nutrients=204&nutrients=269";
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(requestLink)).build();
        HttpResponse<String> response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).join();
        return parseIdResponse(response.body());
    }

    public static Ingredient parseIdResponse(String responseBody){
        double[] initializeNutrientsArray = new double[6];
        JsonObject obj = JsonParser.parseString(responseBody).getAsJsonObject();
        double[] arrayOfNutrients = fetchNutrientUnits(obj);
        if(obj.has("servingSize") && obj.has("labelNutrients")){
            arrayOfNutrients = fillInIngredients(arrayOfNutrients, obj);
        }

        boolean isArrayOfNutrientsEmpty = checkIfEmpty(arrayOfNutrients);

        if(isArrayOfNutrientsEmpty == false && obj.has("description")){
            for(int i = 0; i <= initializeNutrientsArray.length-1;i++){
                initializeNutrientsArray[i] = arrayOfNutrients[i];
            }
            String nameOfIngredient;

            nameOfIngredient = obj.get("description").getAsString();
            double mass = 100;
            Ingredient ingredient = new Ingredient(nameOfIngredient, mass, initializeNutrientsArray[0], initializeNutrientsArray[1], initializeNutrientsArray[2], initializeNutrientsArray[3],
            initializeNutrientsArray[4], initializeNutrientsArray[5]);
            return ingredient;
        } else {
            return null;
        }
    }

    public static boolean checkIfEmpty(double[] nutrients){
        int countZeros = 0;
        boolean thresholdReached = false;

        if(nutrients == null){
            return true;
        }

        for(int i = 0; i <= nutrients.length-1;i++){
            if(nutrients[i] == 0){
                countZeros += 1;    
            }
        }
        if(countZeros >= 5 ){
            thresholdReached = true;
        }

        return thresholdReached;
    }

    public static double[] fetchNutrientUnits(JsonObject specificFoodObject){
        double[] initializeNutrientsArray = new double[6];

        if(!specificFoodObject.has("foodNutrients")){
            return null;
        } else {
            JsonArray nutrientsArray = specificFoodObject.get("foodNutrients").getAsJsonArray();
            for(int i = 0; i <= nutrientsArray.size()-1; i++){
                JsonObject currentNutrientInfo = nutrientsArray.get(i).getAsJsonObject().get("nutrient").getAsJsonObject();
                if(currentNutrientInfo.get("name").getAsString().equals("Water")){
                    if(!nutrientsArray.get(i).getAsJsonObject().has("amount")){
                        initializeNutrientsArray[0] = 0.0;
                    } else {
                        initializeNutrientsArray[0] = nutrientsArray.get(i).getAsJsonObject().get("amount").getAsDouble();
                    }
                } else if(currentNutrientInfo.get("name").getAsString().equals("Energy")){
                    if(!nutrientsArray.get(i).getAsJsonObject().has("amount")){
                        initializeNutrientsArray[1] = 0.0;
                    } else {
                        initializeNutrientsArray[1] = nutrientsArray.get(i).getAsJsonObject().get("amount").getAsDouble();
                    }
                } else if(currentNutrientInfo.get("name").getAsString().equals("Protein")){
                    if(!nutrientsArray.get(i).getAsJsonObject().has("amount")){
                        initializeNutrientsArray[2] = 0.0;
                    } else{
                        initializeNutrientsArray[2] = nutrientsArray.get(i).getAsJsonObject().get("amount").getAsDouble();
                    }
                } else if(currentNutrientInfo.get("name").getAsString().equals("Carbohydrates") || currentNutrientInfo.get("name").getAsString().equals("Carbohydrate, by difference")){
                    if(!nutrientsArray.get(i).getAsJsonObject().has("amount")){
                        initializeNutrientsArray[3] = 0.0;
                    } else{
                        initializeNutrientsArray[3] = nutrientsArray.get(i).getAsJsonObject().get("amount").getAsDouble();
                    }
                } else if(currentNutrientInfo.get("name").getAsString().equals("Total lipid (fat)")){
                    if(!nutrientsArray.get(i).getAsJsonObject().has("amount")){
                        initializeNutrientsArray[4] = 0.0;
                    } else {
                        initializeNutrientsArray[4] = nutrientsArray.get(i).getAsJsonObject().get("amount").getAsDouble();
                    }
                } else if(currentNutrientInfo.get("name").getAsString().equals("Sugars, total including NLEA")){
                    if(!nutrientsArray.get(i).getAsJsonObject().has("amount")){
                        initializeNutrientsArray[5] = 0.0;
                    } else{
                        initializeNutrientsArray[5] = nutrientsArray.get(i).getAsJsonObject().get("amount").getAsDouble();
                    }
                }
            }
        }
        return initializeNutrientsArray;
    }

    public static double[] fillInIngredients(double[] arrayOfNutrientValues, JsonObject nutrientObject){
        Double servingSize = nutrientObject.get("servingSize").getAsDouble();
        Double multiplyingConstant = 100/servingSize;
        for(int i = 0; i <= arrayOfNutrientValues.length-1;i++){
            if(arrayOfNutrientValues[i] == 0.0){
                if(i == 0) {
                    if(nutrientObject.get("labelNutrients").getAsJsonObject().has("water")){
                        arrayOfNutrientValues[i] = (nutrientObject.get("labelNutrients").getAsJsonObject().get("water").getAsJsonObject().get("value").getAsDouble()*multiplyingConstant);
                    }
                } else if(i == 1){
                    if(nutrientObject.get("labelNutrients").getAsJsonObject().has("calories")){
                        arrayOfNutrientValues[i] = (nutrientObject.get("labelNutrients").getAsJsonObject().get("calories").getAsJsonObject().get("value").getAsDouble()*multiplyingConstant);
                    }
                } else if(i == 2){
                    if(nutrientObject.get("labelNutrients").getAsJsonObject().has("protein")){
                        arrayOfNutrientValues[i] = (nutrientObject.get("labelNutrients").getAsJsonObject().get("protein").getAsJsonObject().get("value").getAsDouble()*multiplyingConstant);
                    }
                } else if(i == 3){
                    if(nutrientObject.get("labelNutrients").getAsJsonObject().has("carbohydrates")){
                        arrayOfNutrientValues[i] = (nutrientObject.get("labelNutrients").getAsJsonObject().get("carbohydrates").getAsJsonObject().get("value").getAsDouble()*multiplyingConstant);
                    }
                } else if(i == 4){
                    if(nutrientObject.get("labelNutrients").getAsJsonObject().has("fat")){
                        arrayOfNutrientValues[i] = (nutrientObject.get("labelNutrients").getAsJsonObject().get("fat").getAsJsonObject().get("value").getAsDouble()*multiplyingConstant);
                    }
                } else if(i == 5){
                    if(nutrientObject.get("labelNutrients").getAsJsonObject().has("sugars")){
                        arrayOfNutrientValues[i] = (nutrientObject.get("labelNutrients").getAsJsonObject().get("sugars").getAsJsonObject().get("value").getAsDouble()*multiplyingConstant);
                    }
                }
            }
        }
        return arrayOfNutrientValues;
    }

    public static String[] query(String query){
        HttpClient client = HttpClient.newHttpClient();
        
        String formattedQuery = formatQuery(query);
        // FIXME: Extract Key into Constant Config
        String base = "https://api.nal.usda.gov/fdc/v1/foods/search?api_key=A9Fcb6nKYR2OaTLMlCyrKoWg2AQ3dXiqTWKa48X3&query=";
        String uri = base+formattedQuery;
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).build();

        HttpResponse<String> response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).join();
        return parseQueryResponse(response.body());
    }

    public static String formatQuery(String unformattedQuery){
        char[] characters = new char[unformattedQuery.length()];
        for (int i = 0; i <= unformattedQuery.length()-1; i++) { 
            if(unformattedQuery.charAt(i) == ' '){
                characters[i] = '+';
            } else{
                characters[i] = unformattedQuery.charAt(i);
            }
        }
        String formattedQuery = new String(characters);
        return formattedQuery;
    }

    public static String[] parseQueryResponse(String responseBody){
        JsonObject obj = JsonParser.parseString(responseBody).getAsJsonObject();
        JsonArray queryResults = obj.get("foods").getAsJsonArray();
        String[] results = new String[50]; 
        for(int i = 0; i <= queryResults.size()-1; i++){
            if(i == 51){
                break;
            }
            JsonObject newFood = queryResults.get(i).getAsJsonObject();
            String nameOfFood = newFood.get("description").getAsString();
            results[i] = nameOfFood;
        }
        return results;
    }

    public static Ingredient returnIngredientGivenSearchedIndex(int index, String query){
        HttpClient client = HttpClient.newHttpClient();
        String formattedQuery = formatQuery(query);
        // FIXME: Extract Key into Constant Config
        String base = "https://api.nal.usda.gov/fdc/v1/foods/search?api_key=A9Fcb6nKYR2OaTLMlCyrKoWg2AQ3dXiqTWKa48X3&query=";
        String uri = base+formattedQuery;
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).build();
        HttpResponse<String> response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).join();

        JsonObject obj = JsonParser.parseString(response.body()).getAsJsonObject();
        JsonArray queryResults = obj.get("foods").getAsJsonArray();
        
        JsonObject newFood = queryResults.get(index).getAsJsonObject();
        int id = newFood.get("fdcId").getAsInt();

        return requestIngredientWithId(id);
    }
}
