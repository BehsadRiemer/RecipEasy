import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.MouseListener;

import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import java.awt.event.MouseEvent;
//View that displays all the information about a particular ingredient (eg. mass, name, nutrients)
public class ingredientView {

	JFrame frame;

	//Construcot for instantiating Swing components
	public ingredientView(linkedList recipeList, int ingredientIndex, int recipeIndex ,ingredient ingredient) {
		initialize(recipeList, ingredientIndex, recipeIndex, ingredient);
	}

	//All swing components of the ingredientView
	private void initialize(linkedList recipeList, int ingredientIndex, int recipeIndex, ingredient ingredient) {
		frame = new JFrame();
		frame.setBounds(100, 100, 951, 605);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel recipePanel = new JPanel();
		recipePanel.setBounds(0, 0, 250, 605);
		recipePanel.setBackground(Color.decode("#FFFFFF"));
		recipePanel.setLayout(null);
		frame.add(recipePanel);

		//Renders the list of recipes that the user has created.
		JList recipeJList = new JList(new recipeJListModel(recipeList));
		recipeJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		recipeJList.setFixedCellHeight(50);
		recipeJList.setFont(new Font("Helvetica", Font.BOLD, 20));
		recipeJList.setBounds(290, 0, 233, 577);
		recipeJList.setForeground(Color.decode("#FFFFFF"));
		recipeJList.setBackground(Color.decode("#d7003a"));
		recipeJList.setSelectionBackground(Color.decode("#f30041"));
		recipeJList.setSelectedIndex(recipeIndex);

		//Allows the user to scroll through the list of recipes
		JScrollPane scrollPane = new JScrollPane(recipeJList);
		scrollPane.setLocation(0, 0);
		scrollPane.setSize(255, 577);
		recipePanel.add(scrollPane);

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
		
		JLabel ingredientsTitle = new JLabel("Ingredient");
		ingredientsTitle.setForeground(Color.decode("#323150"));
		ingredientsTitle.setHorizontalAlignment(SwingConstants.CENTER);
		ingredientsTitle.setFont(new Font("Helvetica", Font.PLAIN, 21));
		ingredientsTitle.setBounds(330, 123, 146, 31);
		mainPanel.add(ingredientsTitle);
		
		JLabel nutrientsTitle = new JLabel("Nutrients");
		nutrientsTitle.setForeground(Color.decode("#323150"));
		nutrientsTitle.setHorizontalAlignment(SwingConstants.CENTER);
		nutrientsTitle.setFont(new Font("Helvetica", Font.PLAIN, 21));
		nutrientsTitle.setBounds(689, 123, 146, 31);
		mainPanel.add(nutrientsTitle);
		
		//Sorts the recipes in descending order of their balance index.
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
				recipeList.callMergeSort();
				recipeJList.setModel(new recipeJListModel(recipeList));
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

		//Navigates the user back to the mainView
		JButton homeButton = new JButton(homeIcon);
		homeButton.setBounds(537, 0, 66, 62);
		mainPanel.add(homeButton);
		homeButton.setOpaque(false);
		homeButton.setContentAreaFilled(false);
		homeButton.setBorderPainted(false);
		homeButton.addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent e) {
				ImageIcon homeIconClicked = new ImageIcon("Icons/home clicked.png");
				Image homeImageClicked = homeIconClicked.getImage().getScaledInstance( 50, 50, java.awt.Image.SCALE_SMOOTH );  
				homeIconClicked = new ImageIcon(homeImageClicked);
				homeButton.setIcon(homeIconClicked);
			}
		  
			public void mouseReleased(MouseEvent e) {
				ImageIcon addIcon = new ImageIcon("Icons/home.png");
				Image addImage = addIcon.getImage().getScaledInstance( 50, 50, java.awt.Image.SCALE_SMOOTH );  
				addIcon = new ImageIcon(addImage);
                homeButton.setIcon(addIcon);
                mainView mView = new mainView(recipeList);
                frame.dispose();
                mView.frame.setVisible(true);
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

		JLabel waterAmount = new JLabel(String.format("%.2f", ingredient.getWater()), SwingConstants.RIGHT);
		waterAmount.setFont(new Font("Helvetica", Font.BOLD, 15));
		waterAmount.setForeground(Color.decode("#d7003a"));
		waterAmount.setBounds(750, 232, 70, 20);
		mainPanel.add(waterAmount);

		JLabel calorieAmount = new JLabel(String.format("%.2f", ingredient.getkCals()), SwingConstants.RIGHT);
		calorieAmount.setFont(new Font("Helvetica", Font.BOLD, 15));
		calorieAmount.setForeground(Color.decode("#d7003a"));
		calorieAmount.setBounds(750, 276, 70, 20);
		mainPanel.add(calorieAmount);

		JLabel proteinAmount = new JLabel(String.format("%.2f", ingredient.getProteins()), SwingConstants.RIGHT);
		proteinAmount.setFont(new Font("Helvetica", Font.BOLD, 15));
		proteinAmount.setForeground(Color.decode("#d7003a"));
		proteinAmount.setBounds(750, 320, 70, 20);
		mainPanel.add(proteinAmount);

		JLabel carbohydrateAmount = new JLabel(String.format("%.2f", ingredient.getCarbohydrates()), SwingConstants.RIGHT);
		carbohydrateAmount.setFont(new Font("Helvetica", Font.BOLD, 15));
		carbohydrateAmount.setForeground(Color.decode("#d7003a"));
		carbohydrateAmount.setBounds(750, 364, 70, 20);
		mainPanel.add(carbohydrateAmount);

		JLabel fatsAmount = new JLabel(String.format("%.2f", ingredient.getFats()), SwingConstants.RIGHT);
		fatsAmount.setFont(new Font("Helvetica", Font.BOLD, 15));
		fatsAmount.setForeground(Color.decode("#d7003a"));
		fatsAmount.setBounds(750, 408, 70, 20);
		mainPanel.add(fatsAmount);

		JLabel sugarAmount = new JLabel(String.format("%.2f", ingredient.getSugars()), SwingConstants.RIGHT);
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

        JLabel nameLabel = new JLabel("name");
		nameLabel.setFont(new Font("Helvetica", Font.BOLD, 25));
		nameLabel.setForeground(Color.decode("#150a41"));
		nameLabel.setBounds(270, 230, 150, 25);
		mainPanel.add(nameLabel);
		
		JLabel issueLabel = new JLabel("");
		issueLabel.setFont(new Font("Helvetica", Font.BOLD, 15));
		issueLabel.setForeground(Color.decode("#150a41"));
		issueLabel.setBounds(310, 500, 300, 17);
        mainPanel.add(issueLabel);
        
        JTextField nameTextField = new JTextField(ingredient.getName()){
			@Override public void setBorder(Border border) {

			}};
		nameTextField.setFont(new Font("Helvetica", Font.PLAIN, 17));
		nameTextField.setForeground(Color.decode("#150a41"));
		nameTextField.setBackground((Color.decode("#FFFFFF")));
		nameTextField.setBounds(390, 232, 130, 17);
        mainPanel.add(nameTextField);
        
        JSeparator textFieldBorder = new JSeparator();
		textFieldBorder.setOrientation(SwingConstants.HORIZONTAL);
		textFieldBorder.setForeground(new Color(39, 80, 58));
		textFieldBorder.setBounds(390, 247, 130, 50);
        mainPanel.add(textFieldBorder);
        
        JLabel amountLabel = new JLabel("amount");
		amountLabel.setFont(new Font("Helvetica", Font.BOLD, 25));
		amountLabel.setForeground(Color.decode("#150a41"));
		amountLabel.setBounds(270, 278, 150, 25);
        mainPanel.add(amountLabel);
	
        JTextField amounTextField = new JTextField(String.valueOf((int)(ingredient.getMass()))){
			@Override public void setBorder(Border border) {

			}};
        amounTextField.setFont(new Font("Helvetica", Font.PLAIN, 17));
        amounTextField.setForeground(Color.decode("#150a41"));
        amounTextField.setBackground((Color.decode("#FFFFFF")));
		amounTextField.setBounds(390, 278, 130, 17);
        mainPanel.add(amounTextField);
        
        JSeparator amountTextFieldBorder = new JSeparator();
		amountTextFieldBorder.setOrientation(SwingConstants.HORIZONTAL);
		amountTextFieldBorder.setForeground(new Color(39, 80, 58));
		amountTextFieldBorder.setBounds(390, 293, 130, 50);
		mainPanel.add(amountTextFieldBorder);

		//Navigates the user to the addView
		JButton addButton = new JButton("add");
		addButton.setBounds(320, 350, 150, 40);
		addButton.setFont(new Font("Helvetica", Font.BOLD, 20));
		addButton.setForeground(Color.decode("#FFFFFF"));
		addButton.setBackground(Color.decode("#150a41"));
		addButton.setOpaque(true);
		addButton.setBorderPainted(false);
		addButton.addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent e) {
				addButton.setBackground(Color.decode("#37499f"));
			}
		  
			public void mouseReleased(MouseEvent e) {
				addButton.setBackground(Color.decode("#150a41"));
				if(recipeJList.getSelectedValue() == null){
					JOptionPane warning = new JOptionPane();
					warning.showMessageDialog(frame,
								"Please Select a Recipe that you would like to add this ingredient to.",
								"Issue",
								JOptionPane.WARNING_MESSAGE);
				}
				else{
					if(amounTextField.getText() == ""){
						ingredient.changeMass(0.0);
						ingredient.changeName(nameTextField.getText());
						waterAmount.setText(String.format("%.2f", ingredient.getWater()));
						calorieAmount.setText(String.format("%.2f", ingredient.getkCals()));
						proteinAmount.setText(String.format("%.2f", ingredient.getProteins()));
						carbohydrateAmount.setText(String.format("%.2f", ingredient.getCarbohydrates()));
						fatsAmount.setText(String.format(String.format("%.2f", ingredient.getFats())));
						sugarAmount.setText(String.format(String.format("%.2f", ingredient.getSugars())));
						recipeList.returnNodeAtIndex(recipeJList.getSelectedIndex()).getData().appendIngredient(ingredient);
						recipeJList.setModel(new recipeJListModel(recipeList));
						serialize.writeRecipesFromLinkedList(recipeList);
						mainView mView = new mainView(recipeList);
						mView.frame.setVisible(true);
						frame.dispose();
					}
					else if(Double.parseDouble(amounTextField.getText())<0 || Integer.parseInt(amounTextField.getText())<0){
						issueLabel.setText("Cannot give value below 0. ");
					}
					else{
						ingredient.changeMass(Double.valueOf(Integer.parseInt(amounTextField.getText())));
						ingredient.changeName(nameTextField.getText());
						waterAmount.setText(String.format("%.2f", ingredient.getWater()));
						calorieAmount.setText(String.format("%.2f", ingredient.getkCals()));
						proteinAmount.setText(String.format("%.2f", ingredient.getProteins()));
						carbohydrateAmount.setText(String.format("%.2f", ingredient.getCarbohydrates()));
						fatsAmount.setText(String.format(String.format("%.2f", ingredient.getFats())));
						sugarAmount.setText(String.format(String.format("%.2f", ingredient.getSugars())));
						recipeList.returnNodeAtIndex(recipeJList.getSelectedIndex()).getData().appendIngredient(ingredient);
						recipeJList.setModel(new recipeJListModel(recipeList));
						serialize.writeRecipesFromLinkedList(recipeList);
						mainView mView = new mainView(recipeList);
						mView.frame.setVisible(true);
						frame.dispose();
					}
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
		mainPanel.add(addButton);

		//Calculates the nutrient values for the amount that the user has entered.
		JButton refreshButton = new JButton("refresh");
		refreshButton.setBounds(320, 410, 150, 40);
		refreshButton.setFont(new Font("Helvetica", Font.BOLD, 20));
		refreshButton.setForeground(Color.decode("#FFFFFF"));
		refreshButton.setBackground(Color.decode("#150a41"));
		refreshButton.setOpaque(true);
		refreshButton.setBorderPainted(false);
		refreshButton.addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent e) {
				refreshButton.setBackground(Color.decode("#37499f"));
			}
		  
			public void mouseReleased(MouseEvent e) {
				refreshButton.setBackground(Color.decode("#150a41"));
				if(amounTextField.getText() == ""){
					ingredient.changeMass(0.0);
					waterAmount.setText(String.format("%.2f", ingredient.getWater()));
					calorieAmount.setText(String.format("%.2f", ingredient.getkCals()));
					proteinAmount.setText(String.format("%.2f", ingredient.getProteins()));
					carbohydrateAmount.setText(String.format("%.2f", ingredient.getCarbohydrates()));
					fatsAmount.setText(String.format(String.format("%.2f", ingredient.getFats())));
					sugarAmount.setText(String.format(String.format("%.2f", ingredient.getSugars())));
				}
				else if(Double.parseDouble(amounTextField.getText())<0 || Integer.parseInt(amounTextField.getText())<0){
					issueLabel.setText("Cannot give value below 0. ");
				}
				else{
					if(Integer.parseInt(amounTextField.getText()) >= 0){
						amounTextField.setText(String.valueOf((Integer.parseInt(amounTextField.getText()))));
					}
					ingredient.changeMass(Double.valueOf(amounTextField.getText()));
					waterAmount.setText(String.format("%.2f", ingredient.getWater()));
					calorieAmount.setText(String.format("%.2f", ingredient.getkCals()));
					proteinAmount.setText(String.format("%.2f", ingredient.getProteins()));
					carbohydrateAmount.setText(String.format("%.2f", ingredient.getCarbohydrates()));
					fatsAmount.setText(String.format(String.format("%.2f", ingredient.getFats())));
					sugarAmount.setText(String.format(String.format("%.2f", ingredient.getSugars())));
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
		mainPanel.add(refreshButton);

		JLabel amountUnit = new JLabel("g", SwingConstants.LEFT);
		amountUnit.setFont(new Font("Helvetica", Font.BOLD, 20));
		amountUnit.setForeground(Color.decode("#d7003a"));
		amountUnit.setBounds(520, 278, 70, 20);
		mainPanel.add(amountUnit);

		//Navigates the user back to the mainView
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Helvetica", Font.BOLD, 20));
		backButton.setForeground(Color.decode("#FFFFFF"));
		backButton.setBackground(Color.decode("#150a41"));
		backButton.setOpaque(true);
		backButton.setBorderPainted(false);
		backButton.setBounds(843, 90, 102, 37);
		backButton.addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent e) {
				backButton.setBackground(Color.decode("#37499f"));
			}
		  
			public void mouseReleased(MouseEvent e) {
				backButton.setBackground(Color.decode("#150a41"));
				searchView sView = new searchView(recipeList, -1);
				sView.frame.setVisible(true);
				frame.dispose();
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
		mainPanel.add(backButton);

		JLabel creditsLabel = new JLabel("Search results and information obtained using FoodData Central - USDA", SwingConstants.CENTER);
		creditsLabel.setForeground(Color.decode("#323150"));
		creditsLabel.setFont(new Font("Helvetica", Font.BOLD, 15));
		creditsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		creditsLabel.setBounds(300, 550, 550, 37);
		mainPanel.add(creditsLabel);
	}
}

