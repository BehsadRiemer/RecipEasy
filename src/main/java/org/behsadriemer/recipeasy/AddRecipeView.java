package org.behsadriemer.recipeasy;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.border.Border;
import javax.swing.JTextField;

import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.LinkedList;

public class AddRecipeView {

	JFrame frame;

	String[] types = new String[]{"org.behsadriemer.recipeasy.starter", "org.behsadriemer.recipeasy.beverage", "main dish", "side dish", "org.behsadriemer.recipeasy.dessert"};

	public AddRecipeView(LinkedList<Recipe> recipeList) {
		initialize(recipeList);
	}

	private void initialize(LinkedList<Recipe> recipeList) {
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

		ImageIcon homeIcon = new ImageIcon("Icons/home.png");
		Image homeImage = homeIcon.getImage().getScaledInstance( 50, 50, java.awt.Image.SCALE_SMOOTH );  
		homeIcon = new ImageIcon(homeImage);		
		
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

		JSeparator recipeSectionSeparator = new JSeparator();
		recipeSectionSeparator.setOrientation(SwingConstants.VERTICAL);
		recipeSectionSeparator.setForeground(Color.decode("#323150"));
		recipeSectionSeparator.setBounds(567, 155, 18, 394);
		mainPanel.add(recipeSectionSeparator);

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
					Ingredient currentIngredient = (Ingredient) ingredientsJList.getSelectedValue();
				}
			}
		});

		JLabel nameLabel = new JLabel("Name");
		nameLabel.setFont(new Font("Helvetica", Font.BOLD, 25));
		nameLabel.setForeground(Color.decode("#150a41"));
		nameLabel.setBounds(645, 235, 120, 25);
		mainPanel.add(nameLabel);

		JTextField nameTextField = new JTextField(""){
			@Override public void setBorder(Border border) {

			}};
		nameTextField.setFont(new Font("Helvetica", Font.PLAIN, 17));
		nameTextField.setForeground(Color.decode("#150a41"));
		nameTextField.setBackground((Color.decode("#FFFFFF")));
		nameTextField.setBounds(720, 239, 175, 17);
		mainPanel.add(nameTextField);

		JSeparator textFieldBorder = new JSeparator();
		textFieldBorder.setOrientation(SwingConstants.HORIZONTAL);
		textFieldBorder.setForeground(new Color(39, 80, 58));
		textFieldBorder.setBounds(720, 254, 175, 50);
		mainPanel.add(textFieldBorder);

		JLabel recipeTypeLabel = new JLabel("type");
		recipeTypeLabel.setFont(new Font("Helvetica", Font.BOLD, 25));
		recipeTypeLabel.setForeground(Color.decode("#150a41"));
		recipeTypeLabel.setBounds(645, 293, 120, 25);
		mainPanel.add(recipeTypeLabel);

		JComboBox typeSelector = new JComboBox(types);
		typeSelector.setSelectedIndex(0);
		typeSelector.setForeground(Color.decode("#150a41"));
		typeSelector.setFont(new Font("Helvetica", Font.PLAIN, 17));
		typeSelector.setBounds(720, 297, 175, 25);
		mainPanel.add(typeSelector);

		JButton homeButton = new JButton(homeIcon);
		homeButton.setBounds(537, 0, 66, 62);
		mainPanel.add(homeButton);
		homeButton.setOpaque(false);
		homeButton.setContentAreaFilled(false);
		homeButton.setBorderPainted(false);
		homeButton.addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent e) {
				ImageIcon homeIconClicked = new ImageIcon("Icons/home_clicked.png");
				Image homeImageClicked = homeIconClicked.getImage().getScaledInstance( 50, 50, java.awt.Image.SCALE_SMOOTH );  
				homeIconClicked = new ImageIcon(homeImageClicked);
				homeButton.setIcon(homeIconClicked);
			}
		  
			public void mouseReleased(MouseEvent e) {
				ImageIcon addIcon = new ImageIcon("Icons/home.png");
				Image addImage = addIcon.getImage().getScaledInstance( 50, 50, java.awt.Image.SCALE_SMOOTH );  
				addIcon = new ImageIcon(addImage);
				homeButton.setIcon(addIcon);

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
					currRecipe.compileNutrients();
					recipeName.setText(currRecipe.getName());
					recipeType.setText(currRecipe.getType().toString());
					nameTextField.setText(currRecipe.getName());
					ingredientsJList.setModel(new IngredientJListModel(currRecipe));
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane(recipeJList);
		scrollPane.setLocation(0, 0);
		scrollPane.setSize(255, 577);
		recipePanel.add(scrollPane);

		JButton createButton = new JButton("create");
		createButton.setFont(new Font("Helvetica", Font.BOLD, 20));
		createButton.setForeground(Color.decode("#FFFFFF"));
		createButton.setBackground(Color.decode("#150a41"));
		createButton.setOpaque(true);
		createButton.setBorderPainted(false);
		createButton.setBounds(689, 380, 150, 40);
		createButton.addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent e) {
				createButton.setBackground(Color.decode("#37499f"));
			}
		  
			public void mouseReleased(MouseEvent e) {
				Recipe newRecipe;
				if(isEmpty(nameTextField.getText())){
					createButton.setBackground(Color.decode("#150a41"));
				} else {
					Object type = typeSelector.getSelectedItem();
					newRecipe = new Recipe(
							nameTextField.getText(),
							type != null ? RecipeType.from(type.toString()) : RecipeType.SIDE
					);
					recipeList.add(newRecipe);
					recipeJList.setModel(new RecipeJListModel(recipeList));
					Serializer.writeRecipesFromLinkedList(recipeList);
					createButton.setBackground(Color.decode("#150a41"));

					MainView mView = new MainView(recipeList);
					mView.frame.setVisible(true);
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
		mainPanel.add(createButton);

		JButton removeButton = new JButton("remove");
		removeButton.setFont(new Font("Helvetica", Font.BOLD, 20));
		removeButton.setForeground(Color.decode("#FFFFFF"));
		removeButton.setBackground(Color.decode("#150a41"));
		removeButton.setOpaque(true);
		removeButton.setBorderPainted(false);
		removeButton.setBounds(689, 438, 150, 40);
		removeButton.addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent e) {
				removeButton.setBackground(Color.decode("#37499f"));
			}
		  
			public void mouseReleased(MouseEvent e) {
				removeButton.setBackground(Color.decode("#150a41"));

				int recipeIndex = recipeJList.getSelectedIndex();
				if(recipeIndex == 0 && recipeList.size() == 1){
					recipeList.removeFirst();
					recipeJList.setModel(new RecipeJListModel(recipeList));
					ingredientsJList.clearSelection();
					Serializer.writeRecipesFromLinkedList(recipeList);
					recipeType.setText("");
					nameTextField.setText("");
					recipeName.setText("");
				}
				if(recipeIndex == 0){
					recipeList.removeFirst();
					recipeJList.setModel(new RecipeJListModel(recipeList));
					ingredientsJList.clearSelection();
					Serializer.writeRecipesFromLinkedList(recipeList);
					recipeType.setText("");
					nameTextField.setText("");
					recipeName.setText("");
				}
				if(recipeIndex > 0){
					recipeList.remove(recipeIndex);
					recipeJList.setModel(new RecipeJListModel(recipeList));
					ingredientsJList.clearSelection();
					recipeJList.setSelectedIndex(-1);
					Serializer.writeRecipesFromLinkedList(recipeList);
					recipeType.setText("");
					nameTextField.setText("");
					recipeName.setText("");
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
		mainPanel.add(removeButton);

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

