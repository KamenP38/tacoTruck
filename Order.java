
import java.util.Random;

public class Order {
	
	 private static int orderIDCount = 0;
	 private int orderID;
	 private int menuID;
	 private double cost;
	 private double price;
	 private double[] ingredients;
	 private Inventory inventory = new Inventory();
	
	public Order(int menuID, double[] ingredients) {
		orderIDCount++;
		orderID = orderIDCount;
		this.ingredients = new double[ingredients.length];
		for(int i = 0; i < ingredients.length; i++) {
			this.ingredients[i] = ingredients[i];
		}
		this.menuID = menuID;
		
		double[] costEachIngr = new double[inventory.getIngredientCosts().length];
		for(int i = 0; i < costEachIngr.length; i++) {
			costEachIngr[i] = inventory.getIngredientCosts()[i];
		}
		
	   
	    	for(int z = 0; z < 4; z++) {
	    	this.cost = this.cost + getRecipe(menuID)[z]*costEachIngr[z];
	    	}
	    	
	    this.price = 1.20 * cost;
	    
	}
	
	public int getOrderID() {
		return this.orderID;
	}
	
	public int getMenuID() {
		return this.menuID;
	}
	
	public double getCost() {
		return this.cost;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	
	//We have to get the recipes from inventory class and make the ingredients to be 10% more or less from what they are in the original recipe
    public double[] getRecipe(int menuItemID) {
    	   
        Random generator = new Random();
        double randomValue;
        
            if (menuItemID == 0 || menuItemID == 1 || menuItemID == 2 || menuItemID == 3 ){ 
         
            for(int i = 0; i < inventory.getRecipe(menuItemID).length; i++) {
            	randomValue = generator.nextDouble() + 0.1;
            	while(randomValue < 0.9) {
                    randomValue = generator.nextDouble() + 0.1;
               }
                inventory.getRecipe(menuItemID)[i] = inventory.getRecipe(menuItemID)[i] * randomValue;
            }
            return inventory.getRecipe(menuItemID);
        }
        else {
            return null;
        }
    }
}
