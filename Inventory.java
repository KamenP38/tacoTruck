
public class Inventory {
	private double[] ingredients;
	private String[] ingredientNames;
	private double[] ingredientCosts;
	private double[][] recipes;
	private int shells = 200;

	public Inventory(){
		ingredients = new double[5];
		ingredients[0] = 300.0d;
		ingredients[1] = 300.0d;
		ingredients[2] = 300.0d;
		ingredients[3] = 300.0d;
		ingredients[4] = 300.0d;
		
		
		ingredientNames = new String[5];
		ingredientNames[0] = "Bolts";
		ingredientNames[1] = "Screw";
		ingredientNames[2] = "Oil";
		ingredientNames[3] = "Tire";
		ingredientNames[4] = "Steering wheel";
		
		ingredientCosts = new double[5];
		ingredientCosts[0] = 0.35;
		ingredientCosts[1] = 0.42;
		ingredientCosts[2] = 0.54;
		ingredientCosts[3] = 0.15;
		ingredientCosts[4] = 0.86;
		
		 recipes = new double[4][5];
			recipes[0][0] = 1.0;
			recipes[0][1] = 1.0;
			recipes[0][2] = 0.0;
			recipes[0][3] = 2.0;
			recipes[0][4] = 1.0;
			
			recipes[1][0] = 2.0;
			recipes[1][1] = 2.0;
			recipes[1][2] = 1.0;
			recipes[1][3] = 0.0;
			recipes[1][4] = 1.0;
			
			recipes[2][0] = 0.0;
			recipes[2][1] = 1.0;
			recipes[2][2] = 1.0;
			recipes[2][3] = 2.0;
			recipes[2][4] = 0.0;
			
			recipes[3][0] = 1.0;
			recipes[3][1] = 0.0;
			recipes[3][2] = 2.0;
			recipes[3][3] = 1.0;
			recipes[3][4] = 1.0;
	}
	
	public int getShells() {
		return this.shells;
	}
	
	public String[] getIngredientNames() {
		return ingredientNames;
	}
	
	public double[] getIngredientCosts() {
		return ingredientCosts;
	}
	
	public double[] getIngredients() {
		return ingredients;
	}
	
	
	public double[] getRecipe(int tacoNumber){
		if(tacoNumber == 0 || tacoNumber == 1 || tacoNumber == 2 || tacoNumber == 3) {
			return recipes[tacoNumber];
		}
		//Here I am expecting that the person ordering will not order something that is not on the menu because it won't make any sense.
		else {
			return null;
		}
	}
	
	public boolean checkIfEnough(double[] ingredientsNeeded) {
		int x = 0;
		for(int i = 0; i < ingredientsNeeded.length; i++) {
		if(ingredientsNeeded[i] > ingredients[i]) {
			 x = 1;
		}
		}
		if (x == 1) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public void update (Order newOrder){   
		
		Order anOrder = newOrder;
		
		  // I am assuming that there is enough ingredients to create the order
		 
		// Check if the Order reference is not null (sanity check)
		 
		 if (newOrder != null){
		  // I am assuming you have a method getRecipe in your Order class and it returns a 1D array of doubles of the same size as the inventory array
		  double [] newOrderRecipe = newOrder.getRecipe(anOrder.getMenuID());
		  
		  // Another sanity check (a bit too much, but I don't know what is in yourInventory and Order classes
		  if (newOrderRecipe != null && this.ingredients != null){
		   // and another one (check if both arrays are of the same length
		   if (newOrderRecipe.length == this.ingredients.length){
		    for (int i = 0; i < newOrderRecipe.length; i++){
		     // Remove from inventory what is needed for the new order
		     this.ingredients[i] = this.ingredients[i] - newOrderRecipe[i];
		    }
		    
		    // You may need to add some details here depending on how you designed your class(es)
		   } 
		  } 
		 }
		}
	
	public void showSummary() {
		for(int i = 0; i<5; i++) {
			System.out.println("Amount of ingredient " + ingredientNames[i] + " used: " + (300.0 - ingredients[i]));
		}
	}
}
