//Subclass of recipe - mainDish
public class mainDish extends recipe{

    // Constructor: Allows for instantiating objects of the mainDish class.
    // Initialises the mainDish type and calls the super constructor, recipe.
    public mainDish(String name){
        super(name);
        this.setType("main dish");
    }
}