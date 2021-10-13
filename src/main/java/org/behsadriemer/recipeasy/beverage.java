package org.behsadriemer.recipeasy;

//Subclass of org.behsadriemer.recipeasy.recipe - org.behsadriemer.recipeasy.beverage
public class beverage extends recipe{

    // Constructor: Allows for instantiating objects of the org.behsadriemer.recipeasy.beverage class.
    // Initialises the org.behsadriemer.recipeasy.beverage type and calls the super constructor, org.behsadriemer.recipeasy.recipe.
    public beverage(String name){
        super(name);
        this.setType("beverage");
    }
}