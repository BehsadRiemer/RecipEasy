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

//View for choosing between adding recipes or ingredients.
public class addView {

	JFrame frame;
	Boolean savePressed = false;

	//Construcot for instantiating Swing components
	public addView(linkedList recipeList) {
		initialize(recipeList);
	}

	//All Swing components in the addView
	private void initialize(linkedList recipeList) {
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

		//Navigates the user to adding the addRecipeView
        JButton addRecipe = new JButton("Recipe");
        addRecipe.setFont(new Font("Helvetica", Font.BOLD, 30));
        addRecipe.setBounds(610, 230, 250, 250);
		addRecipe.setForeground(Color.decode("#FFFFFF"));
		addRecipe.setBackground(Color.decode("#150a41"));
		addRecipe.setOpaque(true);
		addRecipe.setBorderPainted(false);
		addRecipe.addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent e) {
				addRecipe.setBackground(Color.decode("#37499f"));
			}
		  
			public void mouseReleased(MouseEvent e) {
                addRecipe.setBackground(Color.decode("#150a41"));
                addRecipeView addRecView = new addRecipeView(recipeList);
                frame.dispose();
                addRecView.frame.setVisible(true);
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
        mainPanel.add(addRecipe);

		//Navigates the user back to the mainViews
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
		
		//Renders the list of recipes that the user has created
		JList recipeJList = new JList(new recipeJListModel(recipeList));
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
					recipe currRecipe = (recipe) recipeJList.getSelectedValue();
				}
			}
		});

		//Allows for scrolling through the list of recipes.
		JScrollPane scrollPane = new JScrollPane(recipeJList);
		scrollPane.setLocation(0, 0);
		scrollPane.setSize(255, 577);
		recipePanel.add(scrollPane);

		JLabel addQuestion = new JLabel("What would you like to add?", SwingConstants.CENTER);
		addQuestion.setForeground(Color.decode("#d7003a"));
		addQuestion.setFont(new Font("Helvetica", Font.BOLD, 25));
		addQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		addQuestion.setBounds(400, 93, 350, 37);
		mainPanel.add(addQuestion);

		//Navigates the user to the searchView for adding and searching for new ingredients.
        JButton addIngredient = new JButton("Ingredient");
        addIngredient.setFont(new Font("Helvetica", Font.BOLD, 30));
        addIngredient.setBounds(287, 230, 250, 250);
		addIngredient.setForeground(Color.decode("#FFFFFF"));
		addIngredient.setBackground(Color.decode("#150a41"));
		addIngredient.setOpaque(true);
		addIngredient.setBorderPainted(false);
		addIngredient.addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent e) {
				addIngredient.setBackground(Color.decode("#37499f"));
			}
		  
			public void mouseReleased(MouseEvent e) {
				addIngredient.setBackground(Color.decode("#150a41"));
				if(recipeJList.getSelectedValue() == null){
					JOptionPane warning = new JOptionPane();
					warning.showMessageDialog(frame,
								"Please Select a Recipe that you would like to add an ingredient to. If you do not have a recipe yet, either create one, or click on search.",
								"Issue",
								JOptionPane.WARNING_MESSAGE);
				}
				else{
					searchView sView = new searchView(recipeList, recipeJList.getSelectedIndex());
					frame.dispose();
					sView.frame.setVisible(true);
					sView.recipeJList.setSelectedIndex(recipeJList.getSelectedIndex());
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
		mainPanel.add(addIngredient);
		
		//Sorts the recipes in descending order of the balance index.
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


	}
}

