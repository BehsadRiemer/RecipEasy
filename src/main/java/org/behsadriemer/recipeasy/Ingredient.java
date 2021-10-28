package org.behsadriemer.recipeasy;

public class Ingredient {
    private String name;
    private Double mass;
    private Double water;
    private Double kCals;
    private Double proteins;
    private Double carbohydrates;
    private Double fats;
    private Double sugars;

    public Ingredient(String name, Double mass, Double water, Double kCals, Double proteins, Double carbohydrates, Double fats, Double sugars){
        this.name = name;
        this.mass = mass;
        this.water = water;
        this.kCals = kCals;
        this.proteins = proteins;
        this.carbohydrates = carbohydrates;
        this.fats = fats;
        this.sugars = sugars;
    }

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

    public void changeName(String newName){
        this.name = newName;
    }
    public void changeMass(double newMass){
        this.mass = newMass;
    }

    public double multiplyingConstant(){
        if(this.mass != 100){
            double multiplyingConstant = this.mass/100;
            return multiplyingConstant;
        }else{
            return 1;
        }
    }

    @Override
    public String toString() {
        return this.name;
    }

}