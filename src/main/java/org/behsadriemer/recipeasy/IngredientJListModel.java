package org.behsadriemer.recipeasy;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

//Custom JListmodel that is used for displaying the ingredients of a particular org.behsadriemer.recipeasy.recipe.
public class IngredientJListModel extends AbstractListModel {
	private final ArrayList<Ingredient> ingredientsList;
	Recipe recipe;
  
	//Constructor: Initializes the list of ingredients and the org.behsadriemer.recipeasy.recipe in which they reside.
	public IngredientJListModel(Recipe recipe) {
		this.ingredientsList = recipe.getFullIngredientList();
		this.recipe = recipe;
	}
  
	//Returns the org.behsadriemer.recipeasy.ingredient at a given index.
	public Ingredient getElementAt(int index) {
	  return ingredientsList.get(index);
	}

	//Returns the name of an org.behsadriemer.recipeasy.ingredient at a given index.
	public String getString(int index){
		return ingredientsList.get(index).getName();
	}
  
	//Returns the number of ingredients that have been added to the org.behsadriemer.recipeasy.recipe
	public int getSize() {
	  	return ingredientsList.size();
	}
  }