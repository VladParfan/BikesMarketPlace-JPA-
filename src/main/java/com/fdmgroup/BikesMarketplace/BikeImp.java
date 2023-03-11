package com.fdmgroup.BikesMarketplace;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import net.bytebuddy.asm.Advice.OffsetMapping.ForOrigin.Renderer.ForReturnTypeName;




public class BikeImp {
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("bikesMarketPlace");
	private static EntityManager em = emf.createEntityManager();
	private static EntityTransaction tx = em.getTransaction();
	

	
	public void createNewBike(String name, String model, String type, double price) {
		
		System.out.println(" ------------ creating ------------" + "\n");
		Bike newBike = new Bike(name, model, type, price);
		
	  tx.begin();
      em.persist(newBike);
      
      System.out.println("New Bike ID " + newBike.getBike_id());
      tx.commit();
	}


	public Bike findById(int id) {
		System.out.println(" ----------------  ID search -------------- " + "\n");
		return em.find(Bike.class, id);
		
	}
	
	public void updateABike(Bike bike, String newName, String newModel, String newType, double newPrice) {
		
		System.out.println(" ---------------- updating -------------- " + "\n");
		tx.begin();
		bike.setBike_name(newName);
		bike.setBike_model(newModel);
		bike.setBike_type(newType);
		bike.setBike_price(newPrice);
		tx.commit();
		
		System.out.println(" ---------------- Bike updated -------------- " + "\n");
	}
	
	public void deleteBike(Bike bike) {
		tx.begin();
		em.remove(bike);
		tx.commit();
	}
	
	public void getAllBikes() {
		System.out.println(" ---------------- All list -------------- ");
		EntityManager emFindAll = emf.createEntityManager();
		String jpql = "SELECT d FROM Bike d";
		TypedQuery<Bike> query = emFindAll.createQuery(jpql, Bike.class);
		List<Bike> results = query.getResultList();
		System.out.println("=============================");
        System.out.println("The list of bikes: ");
        System.out.println("=============================");
		System.out.println(results.toString());
	}
	public void getSpecificBikes(String name) {
		System.out.println(" ---------------- All list -------------- ");
		EntityManager emFindAll = emf.createEntityManager();
		String jpql = "SELECT d FROM Bike d WHERE bike_name=\'"+name+"\'";
		TypedQuery<Bike> query = emFindAll.createQuery(jpql, Bike.class);
		List<Bike> results = query.getResultList();
		System.out.println("=============================");
        System.out.println("The list of bikes: ");
        System.out.println("=============================");
		System.out.println(results.toString());
	}
	
	public void Menu() {

        char option = '\0';
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------------------------------");
        System.out.println("You are on BikesMarketPlace.com" + '\n' + "\n" + "Please, choose the needed option");
        System.out.println("---------------------------");
        System.out.println(" ");
        System.out.println("See all bikes:      	press B");
        System.out.println("Specify bikes search:   press S");
        System.out.println("Add new bike:      	    press A");
        System.out.println("Update a bike:       	press U");
        System.out.println("Delete a bike:       	press D");
        System.out.println("Exit                	press E");
        System.out.println(" ");

        do {
            System.out.println("Enter an option: ");
            char option1 = scanner.next().charAt(0);
            option = Character.toUpperCase(option1);
            System.out.println();

            switch (option) {
                case 'B':
                	getAllBikes();
                	System.out.println();
                	System.out.println();
                    break;
                case 'S':
                	System.out.println("Enter the name of the bike: ");
                    String specificBike = scanner.next();
                	getSpecificBikes(specificBike);
                	System.out.println();
                	System.out.println();
                    break;
                case 'A':
                    System.out.println("Enter the name : ");
                    String bikeName = scanner.next();
                    System.out.println("Enter the Model : ");
                    String bikeModel = scanner.next();
                    System.out.println("Enter the type : ");
                    String bikeType = scanner.next();
                    System.out.println("Enter the price : ");
                    int bikePrice = scanner.nextInt();
                    createNewBike(bikeName, bikeModel, bikeType, bikePrice);
                    
                    System.out.println("---------------------------");
                    System.out.println("You've successfully added " + bikeName + "!");
                    System.out.println("---------------------------");
                    System.out.println();
                    System.out.println();
                    break;
                case 'U':
                    System.out.println("Enter and ID of bike you'd like to update: ");
                    int bikeID = scanner.nextInt();
                    System.out.println("Enter a name to update : ");
                    String newName = scanner.next();
                    System.out.println("Enter a model to update : ");
                    String newModel = scanner.next();
                    System.out.println("Enter a type to update : ");
                    String newType = scanner.next();
                    System.out.println("Enter a price to update : ");
                    int newPrice = scanner.nextInt();
                    Bike bike = findById(bikeID);
                    updateABike(bike, newName, newModel, newType, newPrice);
                    
                    System.out.println("------------------------------");
                    System.out.println("You've successfully updated a bike of ID " + bikeID + "!");
                    System.out.println("------------------------------");
                    System.out.println();
                    System.out.println();

                    break;
                case 'D':
                	System.out.println("Enter ID of bike you'd like to delete: ");
                	int bikeDeleteID = scanner.nextInt();
                	Bike bikeToDeleteBike = findById(bikeDeleteID);
                	System.out.println("Do you want definitely to delete? Y / N");
                	String yesOrNo = scanner.next();
                	if(yesOrNo.equals("y")) {
                		deleteBike(bikeToDeleteBike);
                	} else {
                		System.out.println("N and other letters means NO.");
                	}
                	System.out.println("---------------------------------");
                    System.out.println("You've successfully Deleted a bike of ID " + bikeDeleteID + "!");
                    System.out.println("------------------------------------");
                    System.out.println();
                    System.out.println();
                    break;
                case 'E':
                    System.out.println("---------------------------------------");
                    break;
                default:
                    System.out.println("Error. you did not selected the correct option. Try again");
            }
        } while (option != 'E');
        System.out.println("------------------------------------------------");
        System.out.println("GoodBye");
        System.out.println("-------------------------------------------------");
        System.out.println();
        emf.close();
    }
	
}