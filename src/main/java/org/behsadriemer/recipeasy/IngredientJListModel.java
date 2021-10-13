package org.behsadriemer.recipeasy;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

public class IngredientJListModel extends AbstractListModel {
	private final ArrayList<Ingredient> ingredientsList;
	Recipe recipe;

	public IngredientJListModel(Recipe recipe) {
		this.ingredientsList = recipe.getFullIngredientList();
		this.recipe = recipe;
	}

	public Ingredient getElementAt(int index) {
	  return ingredientsList.get(index);
	}

	public int getSize() {
	  	return ingredientsList.size();
	}
  }