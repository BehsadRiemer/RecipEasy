package org.behsadriemer.recipeasy;

import java.util.LinkedList;

public class App {
    public static void main(String[] args){
        LinkedList newList = Serializer.convertJsonToLinkedList();
        MainView window = new MainView(newList);
        window.frame.setVisible(true);
    }   
}