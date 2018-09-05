package dsa2;

import java.util.Scanner;

/**
 * 
 * @author fritz
 * Console menu-based user interface
 */
public class Main {
	
	private Scanner sc = new Scanner(System.in);
	private Graph graph;

	public static void main(String[] args) {
		Main main = new Main();
		main.graph = new Graph();
		main.demo();
		main.menu();
	}
	
	private void menu()
	{
		System.out.println("Select an input from 1-6.");
		System.out.println("1) Find route from town A to town B.");
		System.out.println("2) Display all the towns listed.");
		System.out.println("3) Display all connections of every town.");
		System.out.println("4) Add new town.");
		System.out.println("5) Add a connection between two towns.");
		System.out.println("6) Exit.");
		System.out.println();
		System.out.println("Selection: ");

		String option = sc.next();
		
		
		switch(option)
		{
			case "1":
				optionOne();
				menu();
				break;
			case "2":
				graph.getAllTowns();
				System.out.println();
				menu();
				break;
			case "3":
				graph.getAllConnected();
				System.out.println();
				menu();
				break;
			case "4":
				System.out.println("Name of the town to add: ");
				String townName = sc.next();
				Town town = new Town(townName);
				graph.addTown(town);
				System.out.println();
				menu();
				break;
			case "5":
				System.out.println("Name of the first town to add: ");
				String town1 = sc.next();
				System.out.println("Name of the second town to add: ");
				String town2 = sc.next();
				System.out.println("Distance between the two towns: ");
				int dist = sc.nextInt();
				graph.addRoad(town1, town2, dist);
				menu();
				break;
			case "6":
				System.out.println("Exiting...");
				System.exit(0);

			default:
				System.out.println("Not a valid input!");
				System.out.println();
				menu();
		}
	}
	
	private void optionOne()
	{
		System.out.println("Find route from town A to town B.");

		System.out.println("Enter the name of Town A: ");
		String town1 = sc.next();
		System.out.println();
		System.out.println("Enter the name of Town B: ");
		String town2 = sc.next();
		System.out.println("Any preference of avoiding specific towns? (Y/N) ");
		String avoid = sc.next();
			
		if(avoid.toLowerCase().equals("yes") || avoid.toLowerCase().equals("y"))
		{
			System.out.println("How many towns do you want to avoid: ");
			int amount = sc.nextInt();
			String[] avoidTowns = new String[amount];
			for(int i = 0; i < amount; i++)
			{
				System.out.println();
				System.out.println("Town to avoid: ");
				System.out.println();
				String town = sc.next();
				avoidTowns[i] = town;
			}
			graph.getShortestRouteAvoid(town1, town2, avoidTowns);
			System.out.println();

		}
		else if(avoid.toLowerCase().equals("no") || avoid.toLowerCase().equals("n"))
		{
			graph.getShortestRoute(town1, town2);
			System.out.println();

		}
		else
		{
			System.out.println("Cannot determine answer... defaulting to no");
			graph.getShortestRoute(town1, town2);
			System.out.println();

		}
	}
	
	private void demo()
	{
		Town[] towns =  {
		new Town("Waterford"), 		//0
		new Town("Kilkenny"), 		//1
		new Town("Dungarvan"), 		//2 
		new Town("Youghal"), 		//3
		new Town("Castlemartyr"),	//4
		new Town("Kilmeaden"),		//5
		new Town("Kilmacthomas"),	//6
		new Town("Killeagh"),		//7
		new Town("Cork"),			//8
		new Town("Carrick-on-Suir"),//9
		new Town("Clonmel"),		//10
		new Town("New Ross"),		//11
		new Town("Mullinavat"),		//12
		new Town("Midleton")		//13


		};
		
		for(Town t : towns)
		{
			graph.addTown(t);
		}
	
		graph.addRoad(towns[0].getName(), towns[1].getName(), 52); 	//Wat - Kilk
		graph.addRoad(towns[0].getName(), towns[2].getName(), 47); 	//Wat - Dun
		graph.addRoad(towns[0].getName(), towns[11].getName(), 23); //Wat - NR
		graph.addRoad(towns[0].getName(), towns[5].getName(), 14);	//Wat - Kilm
		graph.addRoad(towns[0].getName(), towns[9].getName(), 27);	//Wat - CoS
		graph.addRoad(towns[1].getName(), towns[10].getName(), 53);	//Kil - Clon
		graph.addRoad(towns[1].getName(), towns[9].getName(), 46);	//Kil - CoS
		graph.addRoad(towns[1].getName(), towns[12].getName(), 36);	//kil - Mul
		graph.addRoad(towns[2].getName(), towns[10].getName(), 42);	//Dun - Clon
		graph.addRoad(towns[2].getName(), towns[9].getName(), 37);	//Dun - Cos
		graph.addRoad(towns[2].getName(), towns[3].getName(), 30);	//Dun - Youg
		graph.addRoad(towns[2].getName(), towns[7].getName(), 39);	//Dun - Kill
		graph.addRoad(towns[3].getName(), towns[7].getName(), 12);	//Youg - Kill
		graph.addRoad(towns[4].getName(), towns[7].getName(), 6);	//Castl - Kill
		graph.addRoad(towns[4].getName(), towns[13].getName(), 9);	//Castl - Midl
		graph.addRoad(towns[5].getName(), towns[6].getName(), 19);	//Kilm - Kilmac
		graph.addRoad(towns[5].getName(), towns[6].getName(), 38);	//Kilm - Kimac
		graph.addRoad(towns[6].getName(), towns[2].getName(), 39);	//Kilmac - Dun
		graph.addRoad(towns[8].getName(), towns[13].getName(), 31);	//Cork - Midl
		graph.addRoad(towns[9].getName(), towns[10].getName(), 21);	//CoS - Clon 
		graph.addRoad(towns[12].getName(), towns[1].getName(), 9);	//Mul - Kilk
		graph.addRoad(towns[12].getName(), towns[0].getName(), 18);	//Mul - NR
		graph.addRoad(towns[12].getName(), towns[11].getName(), 38);	//Mul - NR

	}

}
