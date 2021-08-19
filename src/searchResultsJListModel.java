import java.util.ArrayList;

import javax.swing.AbstractListModel;

public class searchResultsJListModel extends AbstractListModel {
	private String[] results = new String[50];

	public searchResultsJListModel(String[] results) {
		this.results = results;
	}
  
	public String getElementAt(int index) {
	  return results[index];
	}
  
	public int getSize() {
	  return results.length;
	}
  }