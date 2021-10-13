package org.behsadriemer.recipeasy;

//Subclass of org.behsadriemer.recipeasy.recipe - org.behsadriemer.recipeasy.sideDish
public class sideDish extends recipe{

    // Constructor: Allows for instantiating objects of the org.behsadriemer.recipeasy.sideDish class.
    // Initialises the org.behsadriemer.recipeasy.sideDish type and calls the super constructor, org.behsadriemer.recipeasy.recipe.
    public sideDish(String name){
        super(name);
        this.setType("side dish");
    }

}