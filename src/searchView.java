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

//View for using the USDA Food data central API to search for ingredients.
public class searchView {

	JFrame frame;

	String[] results = new String[50]; 
	JList recipeJList = new JList();

	//Constructor for instantiating the Swing components
	public searchView(linkedList recipeList, int selectedRecipeIndex) {
		initialize(recipeList, selectedRecipeIndex);
		recipeJList.setModel(new recipeJListModel(recipeList));
		recipeJList.setSelectedIndex(selectedRecipeIndex);
	}

	//All the swing components in the searchView
	private void initialize(linkedList recipeList, int selectedRecipeIndex) {
		frame = new JFrame();
		frame.setBounds(100, 100, 951, 605);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel recipePanel = new JPanel();
		recipePanel.setBounds(0, 0, 250, 605);
		recipePanel.setBackground(Color.decode("#FFFFFF"));
		recipePanel.setLayout(null);
		frame.add(recipePanel);

		//Renders the list of recipes that the user has created
		JList recipeJList = new JList(new recipeJListModel(recipeList));
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
					recipe currRecipe = (recipe) recipeJList.getSelectedValue();
					currRecipe.compileNutrients();
					
				}
			}
		});

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

		//Allows the user to enter a keyword that they would like to query using the Food data central usda api
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

		//Renders the list of results that have been parsed using the HTTPResponse from the USDA Food Data Central API
		JList searchResultsJList = new JList(new searchResultsJListModel(results));
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
					ingredient ingr = foodApiFunctions.returnIngredientGivenSearchedIndex(searchIndex, searchTextField.getText());
					if(ingr == null){
						JOptionPane warning = new JOptionPane();
								warning.showMessageDialog(frame,
								"The ingredient you have chosen does not contain sufficient information to be used. Please choose a different one.",
								"Issue",
								JOptionPane.WARNING_MESSAGE);
						searchResultsJList.setSelectedIndex(-1);
					}
					else{
						ingredientView iView = new ingredientView(recipeList, searchIndex,selectedRecipeIndex, ingr);
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

				frame.dispose();
				mainView menu = new mainView(recipeList);
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

		//Allows the user to scroll through the list of search results
		JScrollPane ingredientsScrollPane = new JScrollPane(searchResultsJList);
		ingredientsScrollPane.setLocation(287, 150);
		ingredientsScrollPane.setSize(625, 380);
		mainPanel.add(ingredientsScrollPane);

		//Uses the keyword to query using the food data central API
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
							"There are no results for your search. Please search for a different ingredient.",
							"Issue",
							JOptionPane.WARNING_MESSAGE);
				}
				else{
					results = foodApiFunctions.query(searchTextField.getText());
					searchResultsJList.setModel(new searchResultsJListModel(results));
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

	//Used so that the user does not create a nameless recipe.
	public boolean isEmpty(String name){
		char[] arr = name.toCharArray();
		if(arr.length<2){
			return true;
		} else{
			return false;
		}
	}
}

