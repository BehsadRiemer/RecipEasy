package org.behsadriemer.recipeasy;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.MouseListener;

import javax.swing.JSeparator;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.border.Border;
import javax.swing.JTextField;

import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.LinkedList;

public class SearchView {

	JFrame frame;

	String[] results = new String[50]; 
	JList recipeJList = new JList();

	public SearchView(LinkedList<Recipe> recipeList, int selectedRecipeIndex) {
		initialize(recipeList, selectedRecipeIndex);
		recipeJList.setModel(new RecipeJListModel(recipeList));
		recipeJList.setSelectedIndex(selectedRecipeIndex);
	}

	private void initialize(LinkedList recipeList, int selectedRecipeIndex) {
		frame = new JFrame();
		frame.setBounds(100, 100, 951, 605);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel recipePanel = new JPanel();
		recipePanel.setBounds(0, 0, 250, 605);
		recipePanel.setBackground(Color.decode("#FFFFFF"));
		recipePanel.setLayout(null);
		frame.add(recipePanel);

		JList recipeJList = new JList(new RecipeJListModel(recipeList));
		recipeJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		recipeJList.setFixedCellHeight(50);
		recipeJList.setSelectedIndex(selectedRecipeIndex);
		recipeJList.setFont(new Font("Helvetica", Font.BOLD, 20));
		recipeJList.setBounds(290, 0, 233, 577);
		recipeJList.setForeground(Color.decode("#FFFFFF"));
		recipeJList.setBackground(Color.decode("#d7003a"));
		recipeJList.setSelectionBackground(Color.decode("#f30041"));
		recipeJList.addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent e){
				int recipeIndex = recipeJList.getSelectedIndex();
				if(recipeIndex >= 0){
					Recipe currRecipe = (Recipe) recipeJList.getSelectedValue();
					currRecipe.compileNutrients();
					
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane(recipeJList);
		scrollPane.setLocation(0, 0);
		scrollPane.setSize(255, 577);
		recipePanel.add(scrollPane);

		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 951, 605);
		mainPanel.setBackground(Color.decode("#FFFFFF"));
		mainPanel.setLayout(null);
		frame.add(mainPanel);

		ImageIcon homeIcon = MediaLoader.getInstance().loadImage("/icons/home.png");
		
		JLabel recipTitle = new JLabel("Recip");
		recipTitle.setForeground(Color.decode("#323150"));
		recipTitle.setBounds(687, 1, 200, 70);
		mainPanel.add(recipTitle);
		recipTitle.setFont(new Font("Helvetica", Font.BOLD, 50));
		
		JLabel easyTitle = new JLabel("Easy");
		easyTitle.setForeground(Color.decode("#323150"));
		easyTitle.setBounds(780, 1, 200, 70);
		mainPanel.add(easyTitle);
		easyTitle.setHorizontalAlignment(SwingConstants.CENTER);
		easyTitle.setFont(new Font("Helvetica", Font.BOLD, 50));
		
		JSeparator verticalButtonSeparator = new JSeparator();
		verticalButtonSeparator.setBounds(280, 65, 630, 20);
		verticalButtonSeparator.setForeground(Color.decode("#323150"));
		mainPanel.add(verticalButtonSeparator);

		JSeparator titleSeparator = new JSeparator();
		titleSeparator.setOrientation(SwingConstants.VERTICAL);
		titleSeparator.setForeground(new Color(39, 80, 58));
		titleSeparator.setBounds(669, 10, 18, 50);
		mainPanel.add(titleSeparator);

		JSeparator sortButtonSeparator = new JSeparator();
		sortButtonSeparator.setOrientation(SwingConstants.VERTICAL);
		sortButtonSeparator.setForeground(new Color(39, 80, 58));
		sortButtonSeparator.setBounds(465, 10, 18, 50);
		mainPanel.add(sortButtonSeparator);

		JButton sortButton = new JButton("Sort");
		sortButton.setFont(new Font("Helvetica", Font.BOLD, 20));
		sortButton.setForeground(Color.decode("#FFFFFF"));
		sortButton.setBackground(Color.decode("#150a41"));
		sortButton.setOpaque(true);
		sortButton.setBorderPainted(false);
		sortButton.setBounds(300, 10, 120, 50);
		sortButton.addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent e) {
				sortButton.setBackground(Color.decode("#37499f"));
			}
		  
			public void mouseReleased(MouseEvent e) {
				sortButton.setBackground(Color.decode("#150a41"));
				Collections.sort(recipeList);
				recipeJList.setModel(new RecipeJListModel(recipeList));
			}

			@Override
			public void mouseClicked(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		mainPanel.add(sortButton);

		JTextField searchTextField = new JTextField(""){
			@Override public void setBorder(Border border) {

			}};
		searchTextField.setFont(new Font("Helvetica", Font.PLAIN, 20));
		searchTextField.setForeground(Color.decode("#150a41"));
		searchTextField.setBackground((Color.decode("#FFFFFF")));
		searchTextField.setBounds(478, 100, 250, 20);
		mainPanel.add(searchTextField);
		
		JSeparator searchTextFieldBorder = new JSeparator();
		searchTextFieldBorder.setOrientation(SwingConstants.HORIZONTAL);
		searchTextFieldBorder.setForeground(new Color(39, 80, 58));
		searchTextFieldBorder.setBounds(478, 117, 250, 50);
		mainPanel.add(searchTextFieldBorder);

		JList searchResultsJList = new JList(new SearchResultsJListModel(results));
		searchResultsJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		searchResultsJList.setFixedCellHeight(50);
		searchResultsJList.setFont(new Font("Helvetica", Font.BOLD, 25));
		searchResultsJList.setBounds(380, 150, 650, 300);
		searchResultsJList.setForeground(Color.decode("#FFFFFF"));
		searchResultsJList.setBackground(Color.decode("#150a41"));
		searchResultsJList.setSelectionBackground(Color.decode("#37499f"));
		searchResultsJList.addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent e){
				int searchIndex = searchResultsJList.getSelectedIndex();
				if(searchIndex >= 0){
					searchResultsJList.setSelectionBackground(Color.decode("#37499f"));
					Ingredient ingr = FoodApiFunctions.returnIngredientGivenSearchedIndex(searchIndex, searchTextField.getText());
					if(ingr == null){
						JOptionPane warning = new JOptionPane();
								warning.showMessageDialog(frame,
								"The org.behsadriemer.recipeasy.ingredient you have chosen does not contain sufficient information to be used. Please choose a different one.",
								"Issue",
								JOptionPane.WARNING_MESSAGE);
						searchResultsJList.setSelectedIndex(-1);
					}
					else{
						IngredientView iView = new IngredientView(recipeList, searchIndex,selectedRecipeIndex, ingr);
						iView.frame.setVisible(true);
						frame.dispose();
					}
				}
			}
		});

		JLabel creditsLabel = new JLabel("Search results and information obtained using FoodData Central - USDA", SwingConstants.CENTER);
		creditsLabel.setForeground(Color.decode("#323150"));
		creditsLabel.setFont(new Font("Helvetica", Font.BOLD, 15));
		creditsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		creditsLabel.setBounds(300, 550, 550, 37);
		mainPanel.add(creditsLabel);

		JButton homeButton = new JButton(homeIcon);
		homeButton.setBounds(537, 0, 66, 62);
		mainPanel.add(homeButton);
		homeButton.setOpaque(false);
		homeButton.setContentAreaFilled(false);
		homeButton.setBorderPainted(false);
		homeButton.addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent e) {
				homeButton.setIcon(MediaLoader.getInstance().loadImage("/icons/home_clicked.png"));
			}
		  
			public void mouseReleased(MouseEvent e) {
				homeButton.setIcon(MediaLoader.getInstance().loadImage("/icons/home.png"));

				frame.dispose();
				MainView menu = new MainView(recipeList);
				menu.frame.setVisible(true);
			}

			@Override
			public void mouseClicked(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});

		JScrollPane ingredientsScrollPane = new JScrollPane(searchResultsJList);
		ingredientsScrollPane.setLocation(287, 150);
		ingredientsScrollPane.setSize(625, 380);
		mainPanel.add(ingredientsScrollPane);

		JButton searchButton = new JButton("Search");
		searchButton.setFont(new Font("Helvetica", Font.BOLD, 17));
		searchButton.setForeground(Color.decode("#FFFFFF"));
		searchButton.setBackground(Color.decode("#150a41"));
		searchButton.setOpaque(true);
		searchButton.setBorderPainted(false);
		searchButton.setBounds(750, 93, 100, 32);
		searchButton.addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent e) {
				searchButton.setBackground(Color.decode("#37499f"));
			}
		  
			public void mouseReleased(MouseEvent e) {
				searchButton.setBackground(Color.decode("#150a41"));
				if(searchTextField.getText() == "" || searchTextField.getText() == null || searchTextField.getText() == " "){
					JOptionPane warning = new JOptionPane();
							warning.showMessageDialog(frame,
							"There are no results for your search. Please search for a different org.behsadriemer.recipeasy.ingredient.",
							"Issue",
							JOptionPane.WARNING_MESSAGE);
				}
				else{
					results = FoodApiFunctions.query(searchTextField.getText());
					searchResultsJList.setModel(new SearchResultsJListModel(results));
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		mainPanel.add(searchButton);
	
	}

	public boolean isEmpty(String name){
		char[] arr = name.toCharArray();
		if(arr.length<2){
			return true;
		} else{
			return false;
		}
	}
}

