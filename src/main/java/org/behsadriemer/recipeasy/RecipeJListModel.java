package org.behsadriemer.recipeasy;

import javax.swing.AbstractListModel;
import java.util.LinkedList;

//Custom JListmodel that is used for displaying the ingredients of a particular org.behsadriemer.recipeasy.recipe.
public class RecipeJListModel extends AbstractListModel<Recipe> {
	private final LinkedList<Recipe> recipeList;
  
	//Initializes the linked list of recipes that is used.
	public RecipeJListModel(LinkedList<Recipe> recipeList) {
		this.recipeList = recipeList;
	}
  
	//Returns the org.behsadriemer.recipeasy.recipe at a given index.
	public Recipe getElementAt(int index) {
		return recipeList.get(index);
	}
  
	//Returns the number of recipes that the user has created.
	public int getSize() {
		return recipeList.size();
	}
  }