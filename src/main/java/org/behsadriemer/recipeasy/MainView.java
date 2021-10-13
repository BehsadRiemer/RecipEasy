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

import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.LinkedList;

public class MainView {
	JFrame frame;

	public MainView(LinkedList recipeList) {
		initialize(recipeList);
	}

	private void initialize(LinkedList recipeList) {
		frame = new JFrame();
		frame.setBounds(100, 100, 951, 605);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel recipePanel = new JPanel();
		recipePanel.setBounds(0, 0, 250, 605);
		recipePanel.setBackground(Color.decode("#FFFFFF"));
		recipePanel.setLayout(null);
		frame.add(recipePanel);

		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 951, 605);
		mainPanel.setBackground(Color.decode("#FFFFFF"));
		mainPanel.setLayout(null);
		frame.add(mainPanel);

		JPanel newPanel = new JPanel();
		newPanel.setBounds(0, 0, 951, 605);
		newPanel.setBackground(Color.GREEN);
		newPanel.setLayout(null);

		//TODO: Alter Resource Loading Function
		ImageIcon addIcon = new ImageIcon("Icons/add.png");
		Image addImage = addIcon.getImage().getScaledInstance( 50, 50, java.awt.Image.SCALE_SMOOTH );  
		addIcon = new ImageIcon(addImage);

		//TODO: Alter Resource Loading Function
		ImageIcon addIconClicked = new ImageIcon("Icons/add_clicked.png");
		Image addImageClicked = addIconClicked.getImage().getScaledInstance( 50, 50, java.awt.Image.SCALE_SMOOTH );  
		addIconClicked = new ImageIcon(addImageClicked);

		//TODO: Alter Resource Loading Function
		ImageIcon searchIcon = new ImageIcon("Icons/search.png");
		Image searchImage = searchIcon.getImage().getScaledInstance( 50, 50, java.awt.Image.SCALE_SMOOTH );  
		searchIcon = new ImageIcon(searchImage);

		//TODO: Alter Resource Loading Function
		ImageIcon searchIconClicked = new ImageIcon("Icons/search_clicked.png");
		Image searchImageClicked = searchIconClicked.getImage().getScaledInstance( 50, 50, java.awt.Image.SCALE_SMOOTH );  
		searchIconClicked = new ImageIcon(searchImageClicked);
		
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
		
		JSeparator buttonSeparator = new JSeparator();
		buttonSeparator.setBounds(567, 10, 18, 50);
		buttonSeparator.setForeground(Color.decode("#323150"));
		mainPanel.add(buttonSeparator);
		buttonSeparator.setOrientation(SwingConstants.VERTICAL);

		JButton addButton = new JButton(addIcon);
		addButton.setBounds(492, 0, 66, 62);
		mainPanel.add(addButton);
		addButton.setOpaque(false);
		addButton.setContentAreaFilled(false);
		addButton.setBorderPainted(false);
		addButton.addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent e) {
				//TODO: Alter Resource Loading Function
				ImageIcon addIconClicked = new ImageIcon("Icons/add_clicked.png");
				Image addImageClicked = addIconClicked.getImage().getScaledInstance( 50, 50, java.awt.Image.SCALE_SMOOTH );  
				addIconClicked = new ImageIcon(addImageClicked);
				addButton.setIcon(addIconClicked);
			}
		  
			public void mouseReleased(MouseEvent e) {
				//TODO: Alter Resource Loading Function
				ImageIcon addIcon = new ImageIcon("Icons/add.png");
				Image addImage = addIcon.getImage().getScaledInstance( 50, 50, java.awt.Image.SCALE_SMOOTH );  
				addIcon = new ImageIcon(addImage);
				addButton.setIcon(addIcon);
				AddView aView = new AddView(recipeList);
				frame.dispose();
				aView.frame.setVisible(true);
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
		
		JSeparator recipeSectionSeparator = new JSeparator();
		recipeSectionSeparator.setOrientation(SwingConstants.VERTICAL);
		recipeSectionSeparator.setForeground(Color.decode("#323150"));
		recipeSectionSeparator.setBounds(567, 150, 18, 394);
		mainPanel.add(recipeSectionSeparator);
		
		JLabel ingredientsTitle = new JLabel("Ingredients");
		ingredientsTitle.setForeground(Color.decode("#323150"));
		ingredientsTitle.setHorizontalAlignment(SwingConstants.CENTER);
		ingredientsTitle.setFont(new Font("Helvetica", Font.PLAIN, 21));
		ingredientsTitle.setBounds(340, 150, 146, 31);
		mainPanel.add(ingredientsTitle);
		
		JLabel nutrientsTitle = new JLabel("Nutrients");
		nutrientsTitle.setForeground(Color.decode("#323150"));
		nutrientsTitle.setHorizontalAlignment(SwingConstants.CENTER);
		nutrientsTitle.setFont(new Font("Helvetica", Font.PLAIN, 21));
		nutrientsTitle.setBounds(689, 150, 146, 31);
		mainPanel.add(nutrientsTitle);

		JLabel creditsLabel = new JLabel("Icons by Google, obtained from material.io", SwingConstants.CENTER);
		creditsLabel.setForeground(Color.decode("#323150"));
		creditsLabel.setFont(new Font("Helvetica", Font.BOLD, 15));
		creditsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		creditsLabel.setBounds(400, 550, 350, 37);
		mainPanel.add(creditsLabel);

		JLabel waterLabel = new JLabel("Water");
		waterLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
		waterLabel.setForeground(Color.decode("#d7003a"));
		waterLabel.setBounds(602, 232, 120, 20);
		mainPanel.add(waterLabel);
		
		JLabel calorieLabel = new JLabel("Calories");
		calorieLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
		calorieLabel.setForeground(Color.decode("#d7003a"));
		calorieLabel.setBounds(602, 276, 120, 20);
		mainPanel.add(calorieLabel);
		
		JLabel proteinLabel = new JLabel("Proteins");
		proteinLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
		proteinLabel.setForeground(Color.decode("#d7003a"));
		proteinLabel.setBounds(602, 320, 150, 20);
		mainPanel.add(proteinLabel);
		
		JLabel carbohydratesLabel = new JLabel("Carbohydrates");
		carbohydratesLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
		carbohydratesLabel.setForeground(Color.decode("#d7003a"));
		carbohydratesLabel.setBounds(602, 364, 150, 20);
		mainPanel.add(carbohydratesLabel);
		
		JLabel fatsLabel = new JLabel("Fats");
		fatsLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
		fatsLabel.setForeground(Color.decode("#d7003a"));
		fatsLabel.setBounds(602, 408, 120, 20);
		mainPanel.add(fatsLabel);
		
		JLabel sugarsLabel = new JLabel("Sugars");
		sugarsLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
		sugarsLabel.setForeground(Color.decode("#d7003a"));
		sugarsLabel.setBounds(602, 452, 120, 20);
		mainPanel.add(sugarsLabel);

		JLabel waterAmount = new JLabel("0", SwingConstants.RIGHT);
		waterAmount.setFont(new Font("Helvetica", Font.BOLD, 15));
		waterAmount.setForeground(Color.decode("#d7003a"));
		waterAmount.setBounds(750, 232, 70, 20);
		mainPanel.add(waterAmount);

		JLabel calorieAmount = new JLabel("0", SwingConstants.RIGHT);
		calorieAmount.setFont(new Font("Helvetica", Font.BOLD, 15));
		calorieAmount.setForeground(Color.decode("#d7003a"));
		calorieAmount.setBounds(750, 276, 70, 20);
		mainPanel.add(calorieAmount);

		JLabel proteinAmount = new JLabel("0", SwingConstants.RIGHT);
		proteinAmount.setFont(new Font("Helvetica", Font.BOLD, 15));
		proteinAmount.setForeground(Color.decode("#d7003a"));
		proteinAmount.setBounds(750, 320, 70, 20);
		mainPanel.add(proteinAmount);

		JLabel carbohydrateAmount = new JLabel("0", SwingConstants.RIGHT);
		carbohydrateAmount.setFont(new Font("Helvetica", Font.BOLD, 15));
		carbohydrateAmount.setForeground(Color.decode("#d7003a"));
		carbohydrateAmount.setBounds(750, 364, 70, 20);
		mainPanel.add(carbohydrateAmount);

		JLabel fatsAmount = new JLabel("0", SwingConstants.RIGHT);
		fatsAmount.setFont(new Font("Helvetica", Font.BOLD, 15));
		fatsAmount.setForeground(Color.decode("#d7003a"));
		fatsAmount.setBounds(750, 408, 70, 20);
		mainPanel.add(fatsAmount);

		JLabel sugarAmount = new JLabel("0", SwingConstants.RIGHT);
		sugarAmount.setFont(new Font("Helvetica", Font.BOLD, 15));
		sugarAmount.setForeground(Color.decode("#d7003a"));
		sugarAmount.setBounds(750, 452, 70, 20);
		mainPanel.add(sugarAmount);
		
		JLabel waterUnit = new JLabel("ml", SwingConstants.LEFT);
		waterUnit.setFont(new Font("Helvetica", Font.BOLD, 20));
		waterUnit.setForeground(Color.decode("#d7003a"));
		waterUnit.setBounds(830, 232, 70, 20);
		mainPanel.add(waterUnit);

		JLabel calorieUnit = new JLabel("kCal", SwingConstants.LEFT);
		calorieUnit.setFont(new Font("Helvetica", Font.BOLD, 20));
		calorieUnit.setForeground(Color.decode("#d7003a"));
		calorieUnit.setBounds(830, 276, 70, 20);
		mainPanel.add(calorieUnit);

		JLabel proteinUnit = new JLabel("g", SwingConstants.LEFT);
		proteinUnit.setFont(new Font("Helvetica", Font.BOLD, 20));
		proteinUnit.setForeground(Color.decode("#d7003a"));
		proteinUnit.setBounds(830, 320, 70, 20);
		mainPanel.add(proteinUnit);

		JLabel carbohydrateUnit = new JLabel("g", SwingConstants.LEFT);
		carbohydrateUnit.setFont(new Font("Helvetica", Font.BOLD, 20));
		carbohydrateUnit.setForeground(Color.decode("#d7003a"));
		carbohydrateUnit.setBounds(830, 364, 70, 20);
		mainPanel.add(carbohydrateUnit);

		JLabel fatsUnit = new JLabel("g", SwingConstants.LEFT);
		fatsUnit.setFont(new Font("Helvetica", Font.BOLD, 20));
		fatsUnit.setForeground(Color.decode("#d7003a"));
		fatsUnit.setBounds(830, 408, 70, 20);
		mainPanel.add(fatsUnit);

		JLabel sugarsUnit = new JLabel("g", SwingConstants.LEFT);
		sugarsUnit.setFont(new Font("Helvetica", Font.BOLD, 20));
		sugarsUnit.setForeground(Color.decode("#d7003a"));
		sugarsUnit.setBounds(830, 452, 70, 20);
		mainPanel.add(sugarsUnit);
	
		JLabel recipeName = new JLabel("", SwingConstants.CENTER);
		recipeName.setForeground(Color.decode("#323150"));
		recipeName.setFont(new Font("Helvetica", Font.BOLD, 25));
		recipeName.setHorizontalAlignment(SwingConstants.CENTER);
		recipeName.setBounds(430, 87, 280, 37);
		mainPanel.add(recipeName);

		JLabel recipeType = new JLabel("", SwingConstants.CENTER);
		recipeType.setHorizontalAlignment(SwingConstants.CENTER);
		recipeType.setForeground(Color.decode("#323150"));
		recipeType.setFont(new Font("Helvetica", Font.PLAIN, 18));
		recipeType.setBounds(445, 117, 250, 37);
		mainPanel.add(recipeType);

		JList ingredientsJList = new JList();
		JScrollPane ingredientsScrollPane = new JScrollPane(ingredientsJList);
		ingredientsScrollPane.setLocation(287, 230);
		ingredientsScrollPane.setSize(250, 250);
		mainPanel.add(ingredientsScrollPane);

		JList recipeJList = new JList(new RecipeJListModel(recipeList));
		recipeJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		recipeJList.setFixedCellHeight(50);
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
					recipeName.setText(currRecipe.getName());
					currRecipe.compileNutrients();
					waterAmount.setText(String.format("%.2f", currRecipe.getNutrient("water")));
					calorieAmount.setText(String.format("%.2f", currRecipe.getNutrient("kCals")));
					proteinAmount.setText(String.format("%.2f", currRecipe.getNutrient("proteins")));
					carbohydrateAmount.setText(String.format("%.2f", currRecipe.getNutrient("carbohydrates")));
					fatsAmount.setText(String.format("%.2f", currRecipe.getNutrient("fats")));
					sugarAmount.setText(String.format("%.2f", currRecipe.getNutrient("sugars")));
					recipeType.setText(currRecipe.getType().toString());
					ingredientsJList.setModel(new IngredientJListModel(currRecipe));
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane(recipeJList);
		scrollPane.setLocation(0, 0);
		scrollPane.setSize(255, 577);
		recipePanel.add(scrollPane);

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

		ingredientsJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ingredientsJList.setFixedCellHeight(20);
		ingredientsJList.setFont(new Font("Helvetica", Font.BOLD, 15));
		ingredientsJList.setBounds(287, 230, 250, 250);
		ingredientsJList.setForeground(Color.decode("#FFFFFF"));
		ingredientsJList.setBackground(Color.decode("#d7003a"));
		ingredientsJList.setSelectionBackground(Color.decode("#f30041"));
		ingredientsJList.addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent e){
				int ingredientIndex = ingredientsJList.getSelectedIndex();
				if(ingredientIndex >= 0){
					Ingredient ingr = (Ingredient) ingredientsJList.getSelectedValue();
					int index = recipeJList.getSelectedIndex();
					EditIngredientView editIView = new EditIngredientView(recipeList, ingredientIndex, index, ingr);
					editIView.frame.setVisible(true);
					frame.dispose();
				}
			}
		});

		JButton searchButton = new JButton(searchIcon);
		searchButton.setBounds(591, 0, 66, 62);
		mainPanel.add(searchButton);
		searchButton.setOpaque(false);
		searchButton.setContentAreaFilled(false);
		searchButton.setBorderPainted(false);
		searchButton.addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent e) {
				//TODO: Alter Resource Loading Function
				ImageIcon searchIconClicked = new ImageIcon("Icons/search_clicked.png");
				Image searchImageClicked = searchIconClicked.getImage().getScaledInstance( 50, 50, java.awt.Image.SCALE_SMOOTH );  
				searchIconClicked = new ImageIcon(searchImageClicked);
				searchButton.setIcon(searchIconClicked);
			}
		  
			public void mouseReleased(MouseEvent e) {
				//TODO: Alter Resource Loading Function
				ImageIcon searchIconClicked = new ImageIcon("Icons/search.png");
				Image searchImageClicked = searchIconClicked.getImage().getScaledInstance( 50, 50, java.awt.Image.SCALE_SMOOTH );  
				searchIconClicked = new ImageIcon(searchImageClicked);
				searchButton.setIcon(searchIconClicked);

				SearchView sView = new SearchView(recipeList, recipeJList.getSelectedIndex());
				frame.dispose();
				sView.frame.setVisible(true);
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

		JButton editButton = new JButton("Edit");
		editButton.setFont(new Font("Helvetica", Font.BOLD, 20));
		editButton.setForeground(Color.decode("#FFFFFF"));
		editButton.setBackground(Color.decode("#150a41"));
		editButton.setOpaque(true);
		editButton.setBorderPainted(false);
		editButton.setBounds(843, 90, 102, 37);
		editButton.addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent e) {
				editButton.setBackground(Color.decode("#37499f"));
			}
		  
			public void mouseReleased(MouseEvent e) {
				editButton.setBackground(Color.decode("#150a41"));
				if(recipeJList.getSelectedIndex() == -1){
					JOptionPane warning = new JOptionPane();
					warning.showMessageDialog(frame,
								"Please Select A Recipe that you would like to edit.",
								"Issue",
								JOptionPane.WARNING_MESSAGE);
				}
				else{
					EditRecipeView edit = new EditRecipeView(recipeList, recipeJList.getSelectedIndex());
					edit.frame.setVisible(true);
					frame.dispose();
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
		mainPanel.add(editButton);
	}
}

