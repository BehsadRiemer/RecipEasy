//Subclass of recipe - sideDish
public class sideDish extends recipe{

    // Constructor: Allows for instantiating objects of the sideDish class.
    // Initialises the sideDish type and calls the super constructor, recipe.
    public sideDish(String name){
        super(name);
        this.setType("side dish");
    }

}