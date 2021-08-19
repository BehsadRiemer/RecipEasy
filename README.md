## Welcome to RecipEasy
RecipEasy is a desktop application that can be used in order to keep track of your recipes and the various types of nutritional information associated with these (and their individual ingredients). This application works by using the Food Data Central USDA API which allows for searching and retrieving the nutrients for 100 grams or milliters of any types of ingredients or food. Recipes (which contain ingredients) will contain a balance index (see 'recipe.class') and total nutrients.

## User Features 
- Create Recipes
- Rename Recipes
- Delete Recipes
- Search for ingredients
- View retrieved ingredients using the API and view their nutritional information
- Add custom amounts of an ingredient to a recipe
- Rename an ingredient that has been added to a recipe
- Adjust the amount of an ingredient in a recipe
- Generate a 'balance index' for each recipe (scale 0-100)
- Sort all recipes in descending order of the balance index

## Technical Features
- Implemented my own custom linked list
- Implemented merge sort recursively using my linked list O(NlogN)
- Implemented my own simplified version of a "ranking algorithm" that I call balance index (see 'recipe.java' class)
- Used Java swings for the graphical user interface
- Made a custom JList using Swings
- Made use of HTTP requests and responses when using the API to retrieve information
- Parsed and Read JSON responses to instantiate the necessary java objects

## Folder Structure
The workspace contains two folders by default, where:
- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

## Compiling RecipEasy

Option 1: The easy option
1. Click on the green 'Code' button next to the 'about section'
2. Click on 'Download ZIP'
3. Drag the downloaded ZIP file to a preferred directory on your device.
4. Extract the ZIP file
5. Import the project to a preferred IDE (Make sure that the libraries are imported as well)
6. Open 'app.java'
7. Run the main method 
8. Happy Cooking!


Option 2: Through Github
1. Navigate to your Shell ('Terminal' for MacOS or 'cmd' for Windows)
2. Type 'cd desktop' to navigate to the desktop directory (you will be cloning this project to your desktop)
3. Type Git Clone https://github.com/BehsadRiemer/RecipEasy/ (using your personal access token)
4. Open the project file.
5. Import the project or open it in a preferred manner.
5. Open 'app.java'
6. Compile 'app.java'
8. Happy Cooking!


## Disclaimers
This project *USED* to contain an API key. If you would like to use and test the application out please visit the Food Data Central USDA website to generate an API key. You can paste this API key below the commented line in the 'foodApiFunctions.java' class.

