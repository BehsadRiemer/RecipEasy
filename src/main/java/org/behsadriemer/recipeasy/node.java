package org.behsadriemer.recipeasy;

//Node class: Instances of this that link to another org.behsadriemer.recipeasy.node make up singly linked lists using org.behsadriemer.recipeasy.linkedList class.
public class node{
    //Variable and Constant Declarations
    private recipe data = null;
    private node next = null;

    //Methods

    //Constructor: Instantiates a org.behsadriemer.recipeasy.node that holds an org.behsadriemer.recipeasy.ingredient instance
    public node(recipe data){
        this.data = data;
    }

    //Encapsulation
    //Getters: Methods that return the data or next a org.behsadriemer.recipeasy.node instance
    public recipe getData(){
        return this.data;
    }
    public node getNext(){
        return this.next;
    }

    //Setters: Change the value of data or next org.behsadriemer.recipeasy.node held by an instance by passing in a argument
    public void setData(recipe newData){
        this.data = newData;
    }
    public void setNext(node newNext){
        this.next = newNext;
    }
}

