package org.behsadriemer.recipeasy;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

public class SearchResultsJListModel extends AbstractListModel {
	private String[] results = new String[50];

	public SearchResultsJListModel(String[] results) {
		this.results = results;
	}
  
	public String getElementAt(int index) {
	  return results[index];
	}
  
	public int getSize() {
	  return results.length;
	}
  }