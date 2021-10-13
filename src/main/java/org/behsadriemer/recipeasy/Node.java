package org.behsadriemer.recipeasy;

//Node class: Instances of this that link to another org.behsadriemer.recipeasy.node make up singly linked lists using org.behsadriemer.recipeasy.linkedList class.
@Deprecated(since = "1.0.0") // Will be removed in coming commits!
public class Node {
    //Variable and Constant Declarations
    private Recipe data = null;
    private Node next = null;

    //Methods

    //Constructor: Instantiates a org.behsadriemer.recipeasy.node that holds an org.behsadriemer.recipeasy.ingredient instance
    public Node(Recipe data){
        this.data = data;
    }

    //Encapsulation
    //Getters: Methods that return the data or next a org.behsadriemer.recipeasy.node instance
    public Recipe getData(){
        return this.data;
    }
    public Node getNext(){
        return this.next;
    }

    //Setters: Change the value of data or next org.behsadriemer.recipeasy.node held by an instance by passing in a argument
    public void setData(Recipe newData){
        this.data = newData;
    }
    public void setNext(Node newNext){
        this.next = newNext;
    }
}

