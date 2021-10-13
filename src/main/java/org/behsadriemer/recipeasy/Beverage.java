package org.behsadriemer.recipeasy;

//Subclass of org.behsadriemer.recipeasy.recipe - org.behsadriemer.recipeasy.beverage
public class Beverage extends Recipe {

    // Constructor: Allows for instantiating objects of the org.behsadriemer.recipeasy.beverage class.
    // Initialises the org.behsadriemer.recipeasy.beverage type and calls the super constructor, org.behsadriemer.recipeasy.recipe.
    public Beverage(String name){
        super(name);
        this.setType("beverage");
    }
}