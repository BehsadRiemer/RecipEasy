/*
Ingredient objects are important because they will be written to a text file as part of a recipe. The information
for each ingredient object is fetched from the API. The user is also given the option to create their own ingredient.
*/
public class ingredient{
    //Variable and Constant Declarations
    private String name;
    private Double mass;
    private Double water;
    private Double kCals;
    private Double proteins;
    private Double carbohydrates;
    private Double fats;
    private Double sugars;

    //Constructor: Allows for instantiating objects of the recipe class. Initialises some variables
    public ingredient(String name, Double mass, Double water, Double kCals, Double proteins, Double carbohydrates, Double fats, Double sugars){
        this.name = name;
        this.mass = mass;
        this.water = water;
        this.kCals = kCals;
        this.proteins = proteins;
        this.carbohydrates = carbohydrates;
        this.fats = fats;
        this.sugars = sugars;
    }
    
    //Encapsulation
    //Getters: Methods that return the name, mass and nutrients of an instance 
    public String getName(){
        return this.name;
    }
    public double getMass(){
        return this.mass;
    }
    public double getWater(){
        if(this.mass == 100){
            return this.water;
        }
        else{
            return (this.water*multiplyingConstant());
        }
    }
    public double getkCals(){
        if(this.mass == 100){
            return this.kCals;
        }
        else{
            return (this.kCals*multiplyingConstant());
        }
    }
    public double getProteins(){
        if(this.mass == 100){
            return this.proteins;
        }
        else{
            return (this.proteins*multiplyingConstant());
        }
    }
    public double getCarbohydrates(){
        if(this.mass == 100){
            return this.carbohydrates;
        }
        else{
            return (this.carbohydrates*multiplyingConstant());
        }
    }
    public double getFats(){
        if(this.mass == 100){
            return this.fats;
        }
        else{
            return (this.fats*multiplyingConstant());
        }
    }
    public double getSugars(){
        if(this.mass == 100){
            return this.sugars;
        }
        else{
            return (this.sugars*multiplyingConstant());
        }
    }
    
    //Setters: Change the value of the amount of mass, water, calories and proteins of an instance
    //The reason there are still setters for the variables is because if the user creates their own ingredient, they might want to change
    //specific values without having to create a new one.
    public void changeName(String newName){
        this.name = newName;
    }
    public void changeMass(double newMass){
        this.mass = newMass;
    }
    public void changeWater(double amountOfWater){
        this.water = amountOfWater;
    }
    public void changekCals(double amountOfkCals){
        this.kCals = amountOfkCals;
    }
    public void changeProteins(double amountOfProteins){
        this.proteins = amountOfProteins;
    }
    public void changeCarbohydrates(double amountOfCarbohydrates){
        this.proteins = amountOfCarbohydrates;
    }
    public void changeFats(double amountOfFats){
        this.fats = amountOfFats;
    }
    public void changeSugars(double amountOfSugars){
        this.sugars = amountOfSugars;
    }

    /*Because the api returns nutrient values for only 100g and 100ml, and the user
    may choose to use a different amount - this method calculates a constant
    which is used to multiply by all nutrients.*/
    public double multiplyingConstant(){
        if(this.mass != 100){
            double multiplyingConstant = this.mass/100;
            return multiplyingConstant;
        }else{
            return 1;
        }
    }
    
    //Makes the method toString return the name of the ingredient instance. 
    @Override
    public String toString() {
        return this.name;
    }

}