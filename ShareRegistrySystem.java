/*
 * Created by [Harsh Saini]
 * Student ID: 30414383
 * Subject: ITECH2306 Agile Coding
 */

package main;

import java.util.ArrayList;
import java.util.Scanner;

// The ShareRegistrySystem class handles the core functionality of the application.
//It provides features for managing companies, investors, dividends, and voting processes.
public class ShareRegistrySystem {
    private Scanner scanner = new Scanner(System.in);// Scanner For User input
    private ArrayList<Company> companies = new ArrayList<>();// // List to store registered companies

    // FR-1: Display menu for user interaction
    // This method serves as the central hub for navigating the system's features.
    public void mainMenu() {
        System.out.println("Welcome to SmartShares Registry System");
        while (true) {
        	// Display the main menu options
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Register a New Company"); // This option can add a new company to the system 
            System.out.println("2. View All Registered Companies"); // This option can show list all registered companies
            System.out.println("3. Add a New Investor"); // This option is to add a new investor to a company
            System.out.println("4. View listed Investors for a Company"); // This option is to view investors of a specific company
            System.out.println("5. Announce a Dividend"); // This option is to declare dividends for a company
            System.out.println("6. Initate a Voting Topic"); // This option is to set up a voting topic for a company.
            System.out.println("7. Capture a shareholder Vote"); // This option is to make shareholder to cast votes
            System.out.println("8. Finalizing Voting & Show Results"); // This option to stop voting and show the end results
            System.out.println("9. View Investor Information"); // This option has the ability to examine comprehensive shareholder data
            System.out.println("10. Search Company by Name"); // This option makes user to find a company by its name
            System.out.println("11. Exit"); // This option  will help user to exit the application
            System.out.print("Select an option: ");

           // Read User input and handle invalid input
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character 

          // Here user can select options to perform different operations
            switch (option) {
            case 1: registerNewCompany(); break; // Add a new company
            case 2: displayCompanies(); break; // List all registered companies
            case 3: addNewInvestor(); break; // Add a new investor to a company
            case 4: displayInvestorsOfCompany(); break; // List all investors for a specific company
            case 5: announceDividend(); break; // Declare a dividend for a company
            case 6: initiateVotingTopic(); break; // Set up a voting topic for a company
            case 7: captureShareholderVote(); break; // Record a shareholder's vote
            case 8: finalizeVotingProcess(); break; // End voting and display results
            case 9: viewShareholderInformation(); break; // View detailed information about a shareholder
            case 10: searchCompanyByName(); break; // Search for a company by name
                case 11:
                    System.out.println("Exiting. Goodbye."); // Here User will Exit the application if user choose the input with a goodbye message
                    return; // Terminate the loop and exit
                default:
                    System.out.println("Invalid option. Try again."); // This message will be shown if user enter invalid menu selection
            }
        }
    }

    // FR-2: Allow the user to add a new company
    // This method adds user-provided company details to the registry.
    public void registerNewCompany() {
        if (companies.size() >= 6) { // we have limit the number of companies to 6 as we have asked
            System.out.println("Maximum company limit reached.");
            return;
        }

        System.out.println("\n--- Register a New Company ---");
        System.out.print("What's the Company's Name: ");
        String companyName = scanner.nextLine(); // The user will provide the company name 


        System.out.print("Who is the Founder?: ");
        String founderName = scanner.nextLine(); // The user will provide the founder's name

        System.out.print("How many shares will founder own?: ");
        int founderShares = scanner.nextInt(); // This will show number of shares owned by the Founder

        System.out.print("How many share will be available for Investors?: ");
        int availableShares = scanner.nextInt(); // This will show the number of shares available for investors

        System.out.print("Set the price per Share: ");
        double sharePrice = scanner.nextDouble(); // This will ask for price per share according to the company's founder

        System.out.print("Minimum Shares an Investor must buy: ");
        int min = scanner.nextInt(); // Here user will ask the minimum shares investor can purchase

        System.out.print("Maximum Shares an Investor must buy: ");
        int max = scanner.nextInt(); // Here user will ask the maximum shares investor can purchase
        scanner.nextLine();

        // Here user can add a new company in the system
        Shareholder founder = new Shareholder(founderName, founderShares);
        Company company = new Company(companyName, founder, founderShares, availableShares, sharePrice, min, max);
        companies.add(company);

        System.out.println("Company \"" + companyName + "\" added successfully."); // a confirmation message will be shown with comapany's name on successful addition
    }

    // FR-3: List all companies
    // This method will show a all the  companies which are currently registered in the system
    public void displayCompanies() {
        if (companies.isEmpty()) { //It will check if there are no companies
            System.out.println("No companies added yet.");
            return;
        }

        System.out.println("\n--- Registered Companies ---");
        for (int i = 0; i < companies.size(); i++) {
            Company c = companies.get(i);
            System.out.println((i + 1) + ". " + c.getName() + " - Total Shares: " + c.getTotalIssuedShares()); 
            // this option will display the Company's name and the total number of shares 
        }
    }

    // FR-4: Add a new investor to a selected company
    // This method allows the users to add an investors to a particular company
    public void addNewInvestor() {
        if (companies.isEmpty()) { //It will check if there are no companies
            System.out.println("No companies available.");
            return;
        }

        System.out.println("\n--- Add a New Investor ---");
        for (int i = 0; i < companies.size(); i++) { // This will display the list of companies for selection
            System.out.println((i + 1) + ". " + companies.get(i).getName());
        }

        System.out.print("Select Company Number: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume a newline Character

        if (choice < 1 || choice > companies.size()) {
            System.out.println("Invalid company selection.");
            return;
        }

        Company selected = companies.get(choice - 1); // This will get a new company

        System.out.print("Investor Name: ");
        String investorName = scanner.nextLine(); // this will help users to get investors name

        System.out.print("Number of Shares: ");
        int shares = scanner.nextInt();
        scanner.nextLine(); // consume newline character
        
        //validate the number of shares
        if (shares < selected.getMinSharesPerInvestor() || shares > selected.getMaxSharesPerInvestor()) {
            System.out.println("Shares must be between " + selected.getMinSharesPerInvestor() + " and " + selected.getMaxSharesPerInvestor());
            return;
        }

        if (!selected.hasShareAvailability(shares)) { // This will check if there is enough shares are available to purchase
            System.out.println("Not enough shares available.");
            return;
        }

        // Here users can add investor to the company
        Shareholder investor = new Shareholder(investorName, shares);
        selected.addInvestor(investor, shares);
        System.out.println("Investor added successfully.");
    }

    // FR-5: List all investors for a company
    // It displays all investors for a specified company. 
    public void displayInvestorsOfCompany() {
        if (companies.isEmpty()) { // this will show if there is any company listed
            System.out.println("No companies registered.");
            return;
        }

        System.out.println("\n--- List Investors ---");
        for (int i = 0; i < companies.size(); i++) {
        	 // this will display the list of companies for selection by the users
            System.out.println((i + 1) + ". " + companies.get(i).getName());
        }

        System.out.print("Choose company number: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline Character

        if (choice < 1 || choice > companies.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        Company c = companies.get(choice - 1); // this will get the company selected by the users
        ArrayList<Shareholder> list = c.getInvestors(); // This will show you the retrieve the list of investors in the company 

        System.out.println("Investors in " + c.getName() + ":");
        for (Shareholder s : list) {
        	// It will the shows the investor's name and the share holdings 
            System.out.println("- " + s.getName() + " (" + s.getSharesOwned() + " shares)");
        }
    }

    // FR-6: Declare and calculate dividends
    // This method will allow users to tell a dividend for a selected company
    public void announceDividend() {
        System.out.println("\n--- Declare Dividend ---");
        displayCompanies(); // displays the list of companies in the system
        System.out.print("Select a company: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the new line character

        if (choice < 1 || choice > companies.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        Company selected = companies.get(choice - 1);

        System.out.print("Dividend per share: "); // here the user will get the dividend amount per share
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Dividend payment date: ");
        String date = scanner.nextLine(); // this will show you the dividend payment date

        selected.declareDividend(amount, date); // this will declare the dividend of the company 
    }

    // FR-7: Setup a new voting topic
    // This approach lets users create voting topics for companies.
    public void initiateVotingTopic() {
        displayCompanies(); // this shows the list of companies in the system
        System.out.print("Choose a company to create a voting topic: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline character

        if (choice < 1 || choice > companies.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        Company selected = companies.get(choice - 1); // it will show the company selected by the users

        if (selected.isVotingOpen()) {
            System.out.println("A vote is already in progress.");
            return;
        }

        System.out.print("Enter voting topic: "); // here system will ask user to enter the voting topic
        String topic = scanner.nextLine();

        selected.setupVote(topic); // it will show the user's voting topic which is setup by the user
    }

    // FR-8: Allow a shareholder to cast a vote
    // this method will allow the investors to give  vote on a voting topic
    public void captureShareholderVote() {
        displayCompanies(); // this displays the list of companies in the system
        System.out.print("Choose company number to vote in: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline Character

        if (choice < 1 || choice > companies.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        Company c = companies.get(choice - 1);

        if (!c.isVotingOpen()) {
            System.out.println("Voting is not open.");
            return;
        }

        System.out.print("Enter shareholder name: ");
        String name = scanner.nextLine();

        Shareholder voter = null;
        for (Shareholder s : c.getInvestors()) {
        	// this finds a share holder in the company's investing list
            if (s.getName().equalsIgnoreCase(name)) {
                voter = s;
                break;
            }
        }

        if (voter == null) {
            System.out.println("Shareholder not found."); // here the system will check if the voter/share holder exits the application
            return;
        }

        System.out.print("Vote (yes/no): ");
        String input = scanner.nextLine().toLowerCase(); // here the user will vote in the form of yes/no

        if (input.equals("yes")) {
            c.recordVote(name, true, voter.getSharesOwned()); // record a "yes" vote
        } else if (input.equals("no")) {
            c.recordVote(name, false, voter.getSharesOwned()); // record a "No" vote
        } else {
            System.out.println("Invalid vote.");
        }
    }

    // FR-9: End voting and show results
    // This approach finishes voting for a corporation and presents the results. 
    public void finalizeVotingProcess() {
        displayCompanies(); // this displays the list of the companies listed 
        System.out.print("Select a company to end voting: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the new line character

        if (choice < 1 || choice > companies.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        Company c = companies.get(choice - 1); // It will show you the the selected companies
        if (!c.isVotingOpen()) {               // it will show you if the voting session is still active
            System.out.println("No voting session is active.");
            return;
        }

        c.showVoteResults(); // this will provide you the result of the voting in the end
    }

    // FR-10: View shareholder info and ownership percentage
    // In this method shows shareholder information in details 
    public void viewShareholderInformation() {
        displayCompanies(); // shows the list of companies listed in the system 
        System.out.print("Choose company number: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the new line character

        if (choice < 1 || choice > companies.size()) { // here it will check the companies selection 
            System.out.println("Invalid selection.");
            return;
        }

        Company c = companies.get(choice - 1); // it will display you the selected company
        System.out.print("Enter shareholder name: ");
        String name = scanner.nextLine(); // It will display you the shareholder's name

        Shareholder found = null;
        for (Shareholder s : c.getInvestors()) {
        	// it helps in finding the share holder listed in the companies investors list
            if (s.getName().equalsIgnoreCase(name)) {
                found = s;
                break;
            }
        }

        if (found == null) { // here again the system will checks if shareholders exits
            System.out.println("Shareholder not found.");
        } else {
        	// here it will provides you the full information of the shareholders
            System.out.println("Name: " + found.getName());
            System.out.println("Shares Owned: " + found.getSharesOwned());
            double percent = found.getOwnershipPercentage(c.getTotalIssuedShares());
            System.out.printf("Ownership: %.2f%%\n", percent);
            
            // it will shows you the top share holder for the company
            Shareholder top = c.getTopShareholder();
            System.out.println("Top Shareholder: " + top.getName() + " (" + top.getSharesOwned() + " shares)");
        }
    }

    // Bonus: Search company by name
    public void searchCompanyByName() {
        System.out.print("Enter company name or part of it: ");
        String keyword = scanner.nextLine().toLowerCase();

        boolean found = false;
        for (Company c : companies) {
            if (c.getName().toLowerCase().contains(keyword)) {
                System.out.println("Found: " + c.getName());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No companies matched your search.");
        }
    }
}
