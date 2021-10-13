package org.behsadriemer.recipeasy;

import javax.swing.AbstractListModel;

//Custom JListmodel that is used for displaying the ingredients of a particular org.behsadriemer.recipeasy.recipe.
public class recipeJListModel extends AbstractListModel {
	private final linkedList recipeList;
  
	//Initializes the linked list of recipes that is used.
	public recipeJListModel(linkedList recipeList) {
		this.recipeList = recipeList;
	}
  
	//Returns the org.behsadriemer.recipeasy.recipe at a given index.
	public recipe getElementAt(int index) {
		return recipeList.returnNodeAtIndex(index).getData();
	}

	//Returns the name of a org.behsadriemer.recipeasy.recipe at a given index.
	public String getString(int index){
		return recipeList.returnNodeAtIndex(index).getData().getName();
	}
  
	//Returns the number of recipes that the user has created.
	public int getSize() {
		return recipeList.size();
	}
  }