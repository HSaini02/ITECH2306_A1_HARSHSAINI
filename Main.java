/*
 * Created by [Harsh Saini]
 * Student ID: 30414383
 * Subject: ITECH2306 Agile Coding
 */
package main;

// This is the starting point of the application where
// The Share Registry System must be initialized and started by the Main class.
public class Main {
    public static void main(String[] args) {
        // Here we have Created an example of the ShareRegistrySystem
        ShareRegistrySystem system = new ShareRegistrySystem();
        
        // To see the system's main menu, use the mainMenu method.
        // The user interacts with the program here.
        system.mainMenu();
    }
}