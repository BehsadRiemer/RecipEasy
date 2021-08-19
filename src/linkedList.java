/*
    Sources: 
    Linked List Implementation: 
    - https://itnext.io/linkedlist-in-swift-code-a-linkedlist-data-structure-in-swift-playgrounds-97fe2ed9b8f1
    - https://www.raywenderlich.com/947-swift-algorithm-club-swift-linked-list-data-structure
    

    Merge Sort Implementation: 
    1. mergeSortedLists Method:  https://www.educative.io/edpresso/how-to-sort-a-linked-list-using-merge-sort
    2. mergeSort Method: https://www.youtube.com/watch?v=pNTc1bM1z-4&ab_channel=NickWhite

    Merge Sort Understanding: 
    1. https://www.youtube.com/watch?v=KF2j-9iSf4Q&ab_channel=HackerRank

*/

//Linked List class: Used to hold nodes of recipes.
public class linkedList{
    //Variable Declarations
    private node head;
    
    //Constructor used for instantiating an empty linked list.
    public linkedList(){

    }

    //Encapsulation
    //Getters: Methods that return the data or next a node instance 
    public node getHead(){
        return this.head;
    }

    //Setters: Change the value of data or next node held by an instance by passing in a argument
    public void setHead(node newHead){
        this.head = newHead;
    }

    //Returns the node in the middle
    public node returnMidNode(){
        int half = (this.size()/2)-1; 
        return this.returnNodeAtIndex(half);
    }

    //Returns a clone of the list.
    public linkedList copyList(){
        linkedList copiedList = new linkedList();
        if(this.head == null){
            return copiedList;
        }
        node currentNode = this.getHead();
        for(int i = 0; i < this.size(); i++){
            copiedList.append(currentNode);
            currentNode = currentNode.getNext();
        }
        return copiedList;
    }

    //Methods
    //Inserts a new node at the end of the list (i.e. tail): Traverses through linkedList until node has no node set to setNext
    public void append(node tail){
        node currentNode = this.head;
        //If the list only contains a head node
        if(this.getHead() == null){
            this.setHead(tail);
        }
        else if(this.head.getNext() == null){
            head.setNext(tail);
        }
        //If the list is empty  
        else{
            while(true){
                if(currentNode.getNext()==null){
                    currentNode.setNext(tail);
                    break;
                }
                currentNode = currentNode.getNext();
            }
        }
    }

    //Insert a new head
    public void prepend(node newHead){
        if(this.head == null){
            this.head = newHead;
        }
        else{
            newHead.setNext(this.head);
            this.head = newHead;
        }
    }

    //Removes the head and lets the second node become the head.
    public void removeHead(){
        if(this.size() == 1){
            this.clearList();
        }
        if(this.getHead() != null && this.getHead().getNext() != null){
            node tempNode = this.head.getNext();
            this.head = tempNode;
        }
    }

    //Inserts a node at a particular index by traversing through the linkedlist until
    //The index is reached.
    public void insertAtIndex(int index, node newNode){
        if(index >= this.size()){
            System.out.println("Index too high - Appending...");
            append(newNode);
        }
        else if(index < 0){
            System.out.println("Index " + index + " too small - Prepending...");
            prepend(newNode);
        }
        else{
            node currentNode = this.head;
            int count = 0; 
            while(true){
                if(count == index-1){
                    newNode.setNext(currentNode.getNext());
                    currentNode.setNext(newNode);
                    break;
                }
                currentNode = currentNode.getNext();
                count +=1;
            }
        }
    }

    //Returns the size of the linked list (i.e. the number of nodes contained in the linkedlist)
    //Ex. linked list contains: [0,1,2,3,4] the size is 5 because there are 5 nodes.
    public int size(){
        node currentNode = this.head;
        int count = 0;

        if(this.head == null){
            return 0;
        }
        
        while(currentNode.getNext() != null){
            count += 1;
            currentNode = currentNode.getNext();
        }
        return count+1;
    }

    //Returns the tail of the list (last node in the list)
    public node getTail(){
        node currentNode = this.head;
        while(true){
            if(currentNode.getNext() == null){
                break;
            }
            else{
                currentNode = currentNode.getNext();
            }
        }
        return currentNode;
    }

    //Removes a node at a given index.
    public void removeAtIndex(int index){
        if(this.size() == 1){
            this.clearList();
        }
        else if(index >= this.size()){ //Error handling
            System.out.println("Index " + index + " too high - doing nothing...");
        }
        else if(index < 0){ //Error handling
            System.out.println("Index " + index + " too small - doing nothing...");
        }
        //removes node at index by setting the previous node's pointer to the node after the one that is being removed
        else{
            node currentNode = this.head; 
            int count = 0; 
            node beforeIndex = null;
            while(true){
                if(count == index-1){
                    beforeIndex = currentNode;
                }
                if(count == index){
                    if(beforeIndex == null){
                        this.clearList();
                        break;
                    }
                    beforeIndex.setNext(currentNode.getNext());
                    currentNode.setNext(null);
                    break;
                }
                currentNode = currentNode.getNext();
                count +=1;
            }
        }
    }

    //Clears the list
    public void clearList(){
        this.head = null;
    }

    //Returns the node at a given index
    public node returnNodeAtIndex(int index){
        node currentNode = this.head;
        if(index >= this.size()-1){
            currentNode = this.getTail();
        }
        else{
            int counter = 0; 
            while(counter < index){
                currentNode = currentNode.getNext();
                counter +=1;
            }
        }
        return currentNode;
    }

    //Returns true or false to whether if the list is empty or not.
    public boolean isEmpty(){
        boolean isEmpty = false;
        if(this.head == null){
            isEmpty = true;
        }
        return isEmpty;
    }

    //Prints all the values in the linkedlist.
    public void printList(){
        node currentNode = this.head;
        int count = 0;
        while(true){
            if(currentNode == null){
                break;
            }
            else{
                System.out.println("Recipe " + count + ":" + currentNode.getData().getName());
                currentNode = currentNode.getNext();
                count +=1;
            }
        }
    }



    //Merges two sorted Lists together
    public node mergeSortedList(node leftHead, node rightHead){
        //Base cases
        if(leftHead == null){
            return rightHead;
        }
        if(rightHead == null){
            return leftHead;
        }
        //This will be returned to denote the head of the new sorted list.
        node headOfMergedList; 

        //Sorts and merges the nodes
        if(leftHead.getData().getBalanceIndex() >= rightHead.getData().getBalanceIndex()){
            headOfMergedList = leftHead;
            headOfMergedList.setNext(mergeSortedList(leftHead.getNext(), rightHead));
        }
        else{
            headOfMergedList = rightHead;
            headOfMergedList.setNext(mergeSortedList(rightHead.getNext(), leftHead));
        }
        return headOfMergedList; //returns the head of the sorted list
    }

	// Sorting linked list using merge sort.
	public node mergeSort(node head)
	{
		// Base case
		if (head == null || head.getNext() == null) {
			return head;
		}
       //The linked list is about to be split in half. These are the nodes required.
        node leftListTail = head; 
        node rightListHead = head;
        node rightListTail = head;
        //While iteration used to get each node to its position.
        while(rightListTail != null && rightListTail.getNext() != null){
            leftListTail = rightListHead;
            rightListHead = rightListHead.getNext();
            rightListTail = rightListTail.getNext();
            rightListTail = rightListTail.getNext();
        }
        //splits the list in half. 
        leftListTail.setNext(null); 
        //Each list is split further until it is one node.
	    node leftListHead = mergeSort(head);
        rightListHead = mergeSort(rightListHead);
        //Technically each node is now sorted since it is on its own. Now they are merged together.
		return mergeSortedList(leftListHead, rightListHead);
    }

    //Calls merge sort on the linked list and replaces the head of the list with the head of the sorted list.
    public void callMergeSort(){
        node currentHead = this.getHead();
        node sortednodeHead = mergeSort(currentHead);
        this.setHead(sortednodeHead);
    }
}
