import java.util.ArrayList;

import javax.swing.AbstractListModel;

//Custom JListmodel that is used for displaying the ingredients of a particular recipe.
public class ingredientJListModel extends AbstractListModel {
	private final ArrayList<ingredient> ingredientsList;
	recipe recipe;
  
	//Constructor: Initializes the list of ingredients and the recipe in which they reside.
	public ingredientJListModel(recipe recipe) {
		this.ingredientsList = recipe.getFullIngredientList();
		this.recipe = recipe;
	}
  
	//Returns the ingredient at a given index.
	public ingredient getElementAt(int index) {
	  return ingredientsList.get(index);
	}

	//Returns the name of an ingredient at a given index.
	public String getString(int index){
		return ingredientsList.get(index).getName();
	}
  
	//Returns the number of ingredients that have been added to the recipe
	public int getSize() {
	  	return ingredientsList.size();
	}
  }