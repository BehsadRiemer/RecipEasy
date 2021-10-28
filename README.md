## Welcome to RecipEasy
RecipEasy is a desktop application that can be used in order to keep track of your recipes and the various types of nutritional information associated with these (and their individual ingredients). This application works by using the Food Data Central USDA API which allows for searching and retrieving the nutrients for 100 grams or milliters of any types of ingredients or food. Recipes (which contain ingredients) will contain a balance index (see 'Recipe'-class) and total nutrients.

## User Features 
- Create Recipes
- Rename Recipes
- Delete Recipes
- Search for ingredients
- View retrieved ingredients using the API and view their nutritional information
- Add custom amounts of an Ingredient to a Recipe
- Rename an Ingredient that has been added to a Recipe
- Adjust the amount of an Ingredient in a Recipe
- Generate a 'balance index' for each Recipe (scale 0-100)
- Sort all recipes in descending order of the balance index

## Development
This project is a Maven Project and can be cloned as you'd usually do.
Some IDE's such as IntelliJ IDEA allow you to import Maven projects using 
the `pom.xml` otherwise, if you are used to developing in Text Editors you
can get familiar with the Maven Workflow 
[here](https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html).   
For reasons stated in the [changelog](./CHANGELOG.md) I decided to go for this
change.

## Technical Features
- Implemented my own simplified version of a "ranking algorithm" that I call balance index (see 'Recipe.java' class)
- Used Java swings for the graphical user interface
- Made a custom JList using Swings
- Made use of HTTP requests and responses when using the API to retrieve information
- Parsed and Read JSON responses to instantiate the necessary java objects

