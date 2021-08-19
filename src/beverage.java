//Subclass of recipe - beverage
public class beverage extends recipe{

    // Constructor: Allows for instantiating objects of the beverage class.
    // Initialises the beverage type and calls the super constructor, recipe.
    public beverage(String name){
        super(name);
        this.setType("beverage");
    }
}