public class App {
    //Main method which is the first method that is run when the solution is compiled.
    public static void main(String[] args){
        linkedList newList = serialize.convertJsonToLinkedList(); //Deserializes the JSON document, converting it into recipe and ingredient Objects, and adding them to the linked list.
        mainView window = new mainView(newList); //Instantiates a mainView
        window.frame.setVisible(true); //Makes the mainView visible to the user.
    }   
}