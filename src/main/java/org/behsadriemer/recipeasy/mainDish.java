package org.behsadriemer.recipeasy;

//Subclass of org.behsadriemer.recipeasy.recipe - org.behsadriemer.recipeasy.mainDish
public class mainDish extends recipe{

    // Constructor: Allows for instantiating objects of the org.behsadriemer.recipeasy.mainDish class.
    // Initialises the org.behsadriemer.recipeasy.mainDish type and calls the super constructor, org.behsadriemer.recipeasy.recipe.
    public mainDish(String name){
        super(name);
        this.setType("main dish");
    }
}