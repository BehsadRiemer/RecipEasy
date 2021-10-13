package org.behsadriemer.recipeasy;

//Subclass of org.behsadriemer.recipeasy.recipe - org.behsadriemer.recipeasy.dessert
public class Dessert extends Recipe {

    // Constructor: Allows for instantiating objects of the org.behsadriemer.recipeasy.dessert class.
    // Initialises the org.behsadriemer.recipeasy.dessert type and calls the super constructor, org.behsadriemer.recipeasy.recipe.
    public Dessert(String name){
        super(name);
        this.setType("dessert");
    }
}