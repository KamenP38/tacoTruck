
import java.util.Scanner;
import java.util.Random;

public class TacoTruck {

	private int repeat = 0;
	private String name;
	private Inventory inventory;
	private OrderList orders;
	private Order anOrder;
	private String[] menuItems;

	public TacoTruck() {
		name = "Truck O'Bell";
		inventory = new Inventory();
		orders = new OrderList();

		menuItems = new String[4];
		menuItems[0] = "Bolts and Hammers";
		menuItems[1] = "Fuel Taco";
		menuItems[2] = "Broken Tooth";
		menuItems[3] = "Screw Taco";
	}

	public void run() {

		System.out.println("Welcome to Truck O'Bell! \n ======================");
		System.out.println("[1] Manual Order");
		System.out.println("[2] Simulated ordering");
		System.out.println("[3] Sales summary");
		System.out.println("[4] Quit \n");

		Scanner reader = new Scanner(System.in);
		int number = reader.nextInt();

		if (number == 1) {
			manual();
		}

		if (number == 2) {
			while (repeat != 2 && orders.getShellsLeft() > 0) {
				simulate();
			}
		}

		if (number == 3) {
			showSalesSummary();
		}

		if (number == 4) {
			System.out.println("Thank you for using this application!");
		}
	}

	private void manual() {
		System.out.println("Menu Items: \n");
		System.out.println("[1] " + menuItems[0] + ": " + inventory.getIngredientNames()[0] + ", "
				+ inventory.getIngredientNames()[1] + ", " + inventory.getIngredientNames()[3] + ", "
				+ inventory.getIngredientNames()[4]);
		System.out.println("[2] " + menuItems[1] + ": " + inventory.getIngredientNames()[0] + ", "
				+ inventory.getIngredientNames()[1] + ", " + inventory.getIngredientNames()[4]);
		System.out.println("[3] " + menuItems[2] + ": " + inventory.getIngredientNames()[0] + ", "
				+ inventory.getIngredientNames()[1] + ", " + inventory.getIngredientNames()[2] + ", "
				+ inventory.getIngredientNames()[3]);
		System.out.println("[4] " + menuItems[3] + ": " + inventory.getIngredientNames()[1] + ", "
				+ inventory.getIngredientNames()[3] + ", " + inventory.getIngredientNames()[4]);

		System.out.println("[5] Go back");

		Scanner reader2 = new Scanner(System.in);

		int choice = reader2.nextInt();
		if (choice == 5) {
			run();
		}

		if (choice == 1 || choice == 2 || choice == 3 || choice == 4) {
			if (inventory.checkIfEnough(inventory.getRecipe(choice - 1)) && orders.getShellsLeft() != 0) {
				anOrder = new Order(choice - 1, inventory.getIngredients());
				System.out.println(
						"Order " + anOrder.getOrderID() + " for " + menuItems[choice - 1] + " successfully placed");
				inventory.update(anOrder);
				orders.add(anOrder);
				System.out.println("\n");
				run();

				if (orders.getShellsLeft() == 0) {
					showSalesSummary();
				}
			}

			else {
				System.out.println("Order cannot be fulfilled. Not enough ingredients");

				int counter = 0;
				for (int i = 0; i < inventory.getIngredients().length; i++) {
					if (inventory.getIngredients()[i] < 2.2) {
						counter++;
					}
				}

				if (anOrder.getOrderID() == 200 || counter >= 2) {
					showSalesSummary();
				} else {
					System.out.println(
							"Try  to order a taco from different type. Not enough ingredients for this type of taco.");
					run();
				}
			}
		}

	}

	private void simulate() {
		Random generator = new Random();
		int randomChoice = generator.nextInt(4) + 1;

		int choice = randomChoice;

		if (choice == 1 || choice == 2 || choice == 3 || choice == 4) {
			if (inventory.checkIfEnough(inventory.getRecipe(choice - 1)) && orders.getShellsLeft() != 0) {
				anOrder = new Order(choice - 1, inventory.getIngredients());
				System.out.println(
						"Order " + anOrder.getOrderID() + " for " + menuItems[choice - 1] + " successfully placed!");
				inventory.update(anOrder);
				orders.add(anOrder);
				System.out.println("\n");
				if (orders.getShellsLeft() == 0) {
					showSalesSummary();
				}
			} else {
				repeat = 2;
				System.out.println("Order cannot be fulfilled. Not enough ingredients");
				int counter = 0;
				// WE ARE HERE !!!
				for (int i = 0; i < inventory.getIngredients().length; i++) {
					if (inventory.getIngredients()[i] < 2.2) {
						counter++;
					}
				}
				if (counter >= 2) {
					showSalesSummary();
				} else {
					System.out.println(
							"Try  to order a taco from different type. Not enough ingredients for this type of taco.");
					run();
				}
			}
		}

	}

	private void showSalesSummary() {
		System.out.println("Sales summary:");
		System.out.println("=============");
		System.out.println("Total number of tacos sold: " + orders.getTotalOrdersPlaced());
		System.out.println("Total number of " + menuItems[0] + " sold: " + orders.getOrdersByMenuItem(0));
		System.out.println("Total number of " + menuItems[1] + " sold: " + orders.getOrdersByMenuItem(1));
		System.out.println("Total number of " + menuItems[2] + " sold: " + orders.getOrdersByMenuItem(2));
		System.out.println("Total number of " + menuItems[3] + " sold: " + orders.getOrdersByMenuItem(3));
		inventory.showSummary();
		System.out.println("The total cost is: $" + orders.getTotalCost());
		System.out.println("The total sales are: $" + orders.getTotalSales());
		System.out.println("The total profit is: $" + orders.getTotalProfit());
	}
}
