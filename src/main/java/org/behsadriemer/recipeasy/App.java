package org.behsadriemer.recipeasy;

public class App {
    //Main method which is the first method that is run when the solution is compiled.
    public static void main(String[] args){
        linkedList newList = serialize.convertJsonToLinkedList(); //Deserializes the JSON document, converting it into org.behsadriemer.recipeasy.recipe and org.behsadriemer.recipeasy.ingredient Objects, and adding them to the linked list.
        mainView window = new mainView(newList); //Instantiates a org.behsadriemer.recipeasy.mainView
        window.frame.setVisible(true); //Makes the org.behsadriemer.recipeasy.mainView visible to the user.
    }   
}