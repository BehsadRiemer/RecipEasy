package org.behsadriemer.recipeasy;

//Subclass of org.behsadriemer.recipeasy.recipe - org.behsadriemer.recipeasy.starter
public class Starter extends Recipe {

    // Constructor: Allows for instantiating objects of the org.behsadriemer.recipeasy.starter class.
    // Initialises the org.behsadriemer.recipeasy.starter type and calls the super constructor, org.behsadriemer.recipeasy.recipe.
    public Starter(String name){
        super(name);
        this.setType("starter");
    }
}





