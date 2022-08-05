

public class OrderList {
	
	private Order[] orders;
	//private Order anOrder;
	private Inventory inventory = new Inventory();
	private int[] menuItemsSold;
	private int totalOrdersPlaced;
	private double totalCost;
	private double totalProfit;
	private double totalSales;
	private int shellsLeft = inventory.getShells();
	
	public OrderList() {
		orders = new Order[200];
		menuItemsSold = new int[4];
		totalOrdersPlaced = 0;
		totalCost = 0.0d;
		totalProfit = 0.0d;
		totalSales = 0.0d;
	}	
	
	public void add(Order newOrder) {
		if(newOrder != null) {
			this.orders[newOrder.getOrderID() - 1] = newOrder;
			this.totalOrdersPlaced++;
			
			this.totalCost = this.totalCost + newOrder.getCost();
			this.totalSales = this.totalSales + newOrder.getPrice();

			
			menuItemsSold[newOrder.getMenuID()]++;
			shellsLeft = shellsLeft - 1;
		}	
	}
	
	public int getShellsLeft() {
		return this.shellsLeft;
	}
	
	public int getTotalOrdersPlaced() {
		return this.totalOrdersPlaced;
	}
	
	public double getTotalCost() {
		return this.totalCost;
	}
	
	public double getTotalProfit() {
		return 		this.totalProfit = (totalSales - totalCost);
	}
	
	public double getTotalSales() {
		return this.totalSales;
	}
	
	public int getOrdersByMenuItem(int menuItemID) {
		 if(menuItemID == 0 || menuItemID == 1 || menuItemID == 2 || menuItemID == 3) {
			return menuItemsSold[menuItemID];
		 }
		 else {
			 System.out.println("You put a wrong ID number!");
			 return 0;
		 }
	}
	}

