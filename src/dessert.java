//Subclass of recipe - dessert
public class dessert extends recipe{

    // Constructor: Allows for instantiating objects of the dessert class.
    // Initialises the dessert type and calls the super constructor, recipe.
    public dessert(String name){
        super(name);
        this.setType("dessert");
    }
}