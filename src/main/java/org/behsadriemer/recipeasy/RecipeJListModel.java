package org.behsadriemer.recipeasy;

import javax.swing.AbstractListModel;
import java.util.LinkedList;

public class RecipeJListModel extends AbstractListModel<Recipe> {
	private final LinkedList<Recipe> recipeList;

	public RecipeJListModel(LinkedList<Recipe> recipeList) {
		this.recipeList = recipeList;
	}

	public Recipe getElementAt(int index) {
		return recipeList.get(index);
	}

	public int getSize() {
		return recipeList.size();
	}
  }