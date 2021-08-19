import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;

//All methods relating to the food data central USDA API, which are called when searching for ingredients, parsing responses, getting information for ingredients, and more.
public class foodApiFunctions{
    //Using the id argument which corresponds to 1 ingredient in the USDA API, the contents of the body of the JSON object are used to
    //Create an ingredient via parseIdResponse method.
    public static ingredient requestIngredientWithId(int id){
        //Creates a HTTPClient instance which is used to make a request.
        HttpClient client = HttpClient.newHttpClient();
        //The uri (Uniform Resource Identifier) which is used to locate the JSON object with the given id.
        //ADD THE API KEY TO THIS VARIABLE: (eg. String requestLink = "https://api.nal.usda.gov/fdc/v1/food/" + id + 
        String requestLink = "https://api.nal.usda.gov/fdc/v1/food/" + id + 
        "?api_key=PLACEKEYHERE&query&nutrients=255&nutrients=208&nutrients=203&nutrients=956&nutrients=204&nutrients=269";
        //Initialises a http request using the uri that has been created above.
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(requestLink)).build();
        //Uses the sendAsync method on the client instance to receive an asynchronous response using the request containing the URI above
        //and returns the response as a string
        HttpResponse<String> response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).join();
        //parseIdResponse uses the response body (which is a string) in order to return an ingredient object.
        return parseIdResponse(response.body());
    }

    //Creates an ingredient given JSON body file and creates an ingredient object using the given information in the JSON Object.
    public static ingredient parseIdResponse(String responseBody){
        //Stores all the numerical constants for initializing an ingredient object.
        //index representations - 0: water, 1: kcals, 2: proteins, 3: carbohydrates, 4: fats 5: sugar
        double[] initializeNutrientsArray = new double[6];

        //Creates a JSON object using the responseBody
        JSONObject obj = new JSONObject(responseBody);
        //Uses arrayOfNutrients to store all numerical nutrients returned by fetchNutrientUnits method.
        double[] arrayOfNutrients = fetchNutrientUnits(obj);
        
        //Optimises the arrayOfNutrients if the JSON Object contains "servingSize" and "labelNutrients" keys
        //Which sometimes contains all the nutrient information (since the JSON objects provided by the API are not consistent with the keys they contain)
        if(obj.has("servingSize") && obj.has("labelNutrients")){
            arrayOfNutrients = fillInIngredients(arrayOfNutrients, obj);
        }

        //Checks if arrayOfNutrients is empty (happens if the JSON file is incomplete)
        boolean isArrayOfNutrientsEmpty = checkIfEmpty(arrayOfNutrients);

        //If the JSON object contains the description key (which is the name variable for ingredient instances in the API)
        //Then this method has found everything in order to instantiate an ingredient and return it.
        if(isArrayOfNutrientsEmpty == false && obj.has("description")){
            //Copies the values fetched from arrayOfNutrients to initializeNutrientsArray
            for(int i = 0; i <= initializeNutrientsArray.length-1;i++){
                initializeNutrientsArray[i] = arrayOfNutrients[i];
            }
            //Declaring nameOfIngredient.
            String nameOfIngredient;

            //Name of the ingredient requested.
            nameOfIngredient = obj.getString("description");
            //All data fetched for ingredients will always be 100g or 100ml. See: https://fdc.nal.usda.gov/docs/BFPDB_Doc_Aug2018.pdf page 4 section "Standardizing and Presenting BFPD Data"
            double mass = 100;
            //Ingredient object instantiated with all necessary variables which are initialised.
            ingredient ingredient = new ingredient(nameOfIngredient, mass, initializeNutrientsArray[0], initializeNutrientsArray[1], initializeNutrientsArray[2], initializeNutrientsArray[3], 
            initializeNutrientsArray[4], initializeNutrientsArray[5]);
            return ingredient;
            }
        else{
            //If the JSON body does not contain the description field then there aren't enough values in order to create
            // an instance of ingredients. This will therefore return null.
            return null;
        }
    }
    
    //Method that returns true if 3 or more elements in the array of nutrients are 0, indicating it is empty.
    public static boolean checkIfEmpty(double[] nutrients){
        int countZeros = 0;
        boolean thresholdReached = false;

        //Checks if the whole array is null.
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

    //This method will fetch the different nutrients for the ingredient.
    //This method was required because not every response body (i.e. JSON object) will contain
    //every nutrient or every nutrient in the same order. Therefore, this needs to be checked
    //using conditionals.
    public static double[] fetchNutrientUnits(JSONObject specificFoodObject){
        //Stores all the constants for initializing an ingredient object - this is what is returned in the end.
        //indexes - 0: water, 1: kcals, 2: proteins, 3: carbohydrates, 4: fats 5: sugar
        double[] initializeNutrientsArray = new double[6];

        //Case if the JSON object doesn't contain the foodNutrients field
        //(in which case there is no information to be fetched since it is empty)
        if(!specificFoodObject.has("foodNutrients")){
            return null;
        }
        //Iterates through the foodNutrient JSONArray and adds values to initializeNutrientsArray.
        else{
            JSONArray nutrientsArray = specificFoodObject.getJSONArray("foodNutrients");
            for(int i = 0; i <= nutrientsArray.length()-1; i++){
                JSONObject currentNutrientInfo = nutrientsArray.getJSONObject(i).getJSONObject("nutrient");
                //If the current JSONObject nutrient is water, then the correct index for water will be initialised 
                if(currentNutrientInfo.getString("name").equals("Water")){
                    //Checks if the current object even contains the variable 'amount' to indicate a quantity. If it doesn't well then it has to be 0.0
                    //If it does, then the value is added.
                    if(!nutrientsArray.getJSONObject(i).has("amount")){
                        initializeNutrientsArray[0] = 0.0;
                    }
                    else{
                        initializeNutrientsArray[0] = nutrientsArray.getJSONObject(i).getDouble("amount");
                    }
                }
                else if(currentNutrientInfo.getString("name").equals("Energy")){
                    if(!nutrientsArray.getJSONObject(i).has("amount")){
                        initializeNutrientsArray[1] = 0.0;
                    }
                    else{
                        initializeNutrientsArray[1] = nutrientsArray.getJSONObject(i).getDouble("amount");
                    }
                }
                else if(currentNutrientInfo.getString("name").equals("Protein")){
                    if(!nutrientsArray.getJSONObject(i).has("amount")){
                        initializeNutrientsArray[2] = 0.0;
                    }
                    else{
                        initializeNutrientsArray[2] = nutrientsArray.getJSONObject(i).getDouble("amount");
                    }
                }
                else if(currentNutrientInfo.getString("name").equals("Carbohydrates") || currentNutrientInfo.getString("name").equals("Carbohydrate, by difference")){
                    if(!nutrientsArray.getJSONObject(i).has("amount")){
                        initializeNutrientsArray[3] = 0.0;
                    }
                    else{
                        initializeNutrientsArray[3] = nutrientsArray.getJSONObject(i).getDouble("amount");
                    }
                }
                else if(currentNutrientInfo.getString("name").equals("Total lipid (fat)")){
                    if(!nutrientsArray.getJSONObject(i).has("amount")){
                        initializeNutrientsArray[4] = 0.0;
                    }
                    else{
                        initializeNutrientsArray[4] = nutrientsArray.getJSONObject(i).getDouble("amount");
                    }
                }
                else if(currentNutrientInfo.getString("name").equals("Sugars, total including NLEA")){
                    if(!nutrientsArray.getJSONObject(i).has("amount")){
                        initializeNutrientsArray[5] = 0.0;
                    }
                    else{
                        initializeNutrientsArray[5] = nutrientsArray.getJSONObject(i).getDouble("amount");
                    }
                }
            }
        }
        //Returns the array of values ready to initialize an ingredient instance.
        return initializeNutrientsArray;
    }

    //Since the contained JSON object keys are inconsistent (some objects contain information 
    //for all nutrients and some just use a different section called labelNutrients),this method is used to 
    //fill in any values that are not contained in some JSON objects.
    public static double[] fillInIngredients(double[] arrayOfNutrientValues, JSONObject nutrientObject){
        //ServingSize is initialised in order to estimate the nutrients
        Double servingSize = nutrientObject.getDouble("servingSize");
        //Used to multipy by each amount so that the nutrients are adjusted for 100g (standard info)
        Double multiplyingConstant = 100/servingSize;
        //Goes through each element in arrayOfNutrientValues to check if it is 0.0 which could mean it missed values.
        // Because some JSON objects contain a key called "labelNutrients" instead of "foodNutrients", this for loop
        //Will look through "labelNutrients" to check if any values are missing/haven't been added
        for(int i = 0; i <= arrayOfNutrientValues.length-1;i++){
            if(arrayOfNutrientValues[i] == 0.0){
                if(i == 0){
                    //Checks if the key even exists before setting a value to arrayOfNutrientValues.
                    if(nutrientObject.getJSONObject("labelNutrients").has("water")){
                        arrayOfNutrientValues[i] = (nutrientObject.getJSONObject("labelNutrients").getJSONObject("water").getDouble("value")*multiplyingConstant);
                    }
                }
                else if(i == 1){
                    if(nutrientObject.getJSONObject("labelNutrients").has("calories")){
                        arrayOfNutrientValues[i] = (nutrientObject.getJSONObject("labelNutrients").getJSONObject("calories").getDouble("value")*multiplyingConstant);
                    }
                }
                else if(i == 2){
                    if(nutrientObject.getJSONObject("labelNutrients").has("protein")){
                        arrayOfNutrientValues[i] = (nutrientObject.getJSONObject("labelNutrients").getJSONObject("protein").getDouble("value")*multiplyingConstant);
                    }
                }
                else if(i == 3){
                    if(nutrientObject.getJSONObject("labelNutrients").has("carbohydrates")){
                        arrayOfNutrientValues[i] = (nutrientObject.getJSONObject("labelNutrients").getJSONObject("carbohydrates").getDouble("value")*multiplyingConstant);
                    }
                }
                else if(i == 4){
                    if(nutrientObject.getJSONObject("labelNutrients").has("fat")){
                        arrayOfNutrientValues[i] = (nutrientObject.getJSONObject("labelNutrients").getJSONObject("fat").getDouble("value")*multiplyingConstant);   
                    }
                }
                else if(i == 5){
                    if(nutrientObject.getJSONObject("labelNutrients").has("sugars")){
                        arrayOfNutrientValues[i] = (nutrientObject.getJSONObject("labelNutrients").getJSONObject("sugars").getDouble("value")*multiplyingConstant);
                    }
                }
            }
        }
        return arrayOfNutrientValues;
    }

    /*Searches the USDA API for a given food denoted by the variable foodName. This method will return the first 50 results
    since showing the user all the results would make a large impact on memory usage. */
    public static String[] query(String query){
        //Creates a HTTPClient instance which is used to make a request.
        HttpClient client = HttpClient.newHttpClient();
        
        String formattedQuery = formatQuery(query);
        String base = "https://api.nal.usda.gov/fdc/v1/foods/search?api_key=A9Fcb6nKYR2OaTLMlCyrKoWg2AQ3dXiqTWKa48X3&query=";
        //Initialises the uri (Uniform Resource Identifier) which is used to locate the JSON object with the given id.     
        String uri = base+formattedQuery;
         //Initialises a http request using the uri that has been created above.
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).build();

        //Uses the sendAsync method on the client instance to receive an asynchronous response using the request containing the URI above
        //and returns the response as a string. Asynchronous means that the the whole program will not wait for the response to the request,
        // and instead keep going until the request has been received (which is when the program is updated accordingly)
        HttpResponse<String> response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).join();
        return parseQueryResponse(response.body());
    }

    /*Avoiding search errors with strings in the link requires splitting
    the string into characters such that a space can be converted to + or %20: */
    public static String formatQuery(String unformattedQuery){
        //Declaring an array of characters equal to the length of the query
        char[] characters = new char[unformattedQuery.length()];
        //Iterates through characters of the unformatted query, replacing spaces with '+'
        for (int i = 0; i <= unformattedQuery.length()-1; i++) { 
            if(unformattedQuery.charAt(i) == ' '){
                characters[i] = '+';
            }
            else{
                characters[i] = unformattedQuery.charAt(i);
            }
        }
        String formattedQuery = new String(characters);
        return formattedQuery;
    }

    //Parses the query response and returns the array of names as a String
    public static String[] parseQueryResponse(String responseBody){
        JSONObject obj = new JSONObject(responseBody);
        JSONArray queryResults = new JSONArray(obj.getJSONArray("foods"));
        String[] results = new String[50]; 
        for(int i = 0; i <= queryResults.length()-1; i++){
            if(i == 51){
                break;
            }
            JSONObject newFood = queryResults.getJSONObject(i);
            String nameOfFood = newFood.getString("description");
            results[i] = nameOfFood;
            //int foodId = newFood.getInt("fdcId");
        }
        return results;
    }

    //This method is used when the user has chosen an ingredient they would like to view. 
    //This function will be called to retrieve the ID of the ingredient that the user has chosen, 
    //and will make another HTTPRequest to fetch the information for this ID.
    public static ingredient returnIngredientGivenSearchedIndex(int index, String query){
        HttpClient client = HttpClient.newHttpClient();
        String formattedQuery = formatQuery(query);
        String base = "https://api.nal.usda.gov/fdc/v1/foods/search?api_key=A9Fcb6nKYR2OaTLMlCyrKoWg2AQ3dXiqTWKa48X3&query=";
        String uri = base+formattedQuery;
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).build();
        HttpResponse<String> response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).join();

        JSONObject obj = new JSONObject(response.body());
        JSONArray queryResults = new JSONArray(obj.getJSONArray("foods"));
        
        JSONObject newFood = queryResults.getJSONObject(index);
        int id = newFood.getInt("fdcId");

        return requestIngredientWithId(id);
    }


}
