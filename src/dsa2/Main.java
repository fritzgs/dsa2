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
			case "7":
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
				System.out.println("Town to avoid: ");
				String town = sc.nextLine();
				avoidTowns[i] = town;
			}
			graph.getShortestRouteAvoid(town1, town2, avoidTowns);
		}
		else if(avoid.toLowerCase().equals("no") || avoid.toLowerCase().equals("n"))
		{
			graph.getShortestRoute(town1, town2);
		}
		else
		{
			System.out.println("Cannot determine answer... defaulting to no");
			graph.getShortestRoute(town1, town2);
		}
	}
	

}
