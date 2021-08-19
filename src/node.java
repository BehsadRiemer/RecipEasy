//Node class: Instances of this that link to another node make up singly linked lists using linkedList class.
public class node{
    //Variable and Constant Declarations
    private recipe data = null;
    private node next = null;

    //Methods

    //Constructor: Instantiates a node that holds an ingredient instance
    public node(recipe data){
        this.data = data;
    }

    //Encapsulation
    //Getters: Methods that return the data or next a node instance 
    public recipe getData(){
        return this.data;
    }
    public node getNext(){
        return this.next;
    }

    //Setters: Change the value of data or next node held by an instance by passing in a argument
    public void setData(recipe newData){
        this.data = newData;
    }
    public void setNext(node newNext){
        this.next = newNext;
    }
}

