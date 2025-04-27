/*
 * Created by Harsh Saini
 * Student ID: 30414383
 * Subject: ITECH2306 Agile Coding
 */
package main;

import java.util.ArrayList;

/**
 * This class represents a company. It manages everything from its founder and investors,
 * to shares, dividends, and company-wide voting.
 */
public class Company {

    // Basic info about the company
    private String comapnyName;                      // The company's name
    private Shareholder founder;              // The person who started the company
    private int totalSharesIssued;            // How many shares have been given out so far
    private int availableShares;              // Shares that are still available to sell
    private double priceForEachShare;             // Cost of each share
    private int minimumSharesPerInvestor;         // The fewest shares someone is allowed to buy
    private int maximumSharesPerInvestor;         // The most shares someone can buy at once

    // Dividend details
    private double dividendAmountPerShare;          // Amount paid per share
    private String dividendPaymentDate;              // When the dividend will be paid

    // Voting system
    private String activeVoteTopic;                 // What shareholders are voting on
    private boolean isVotingSessionOpen;                // Whether voting is currently allowed
    private int totalVotesYes = 0;                 // Total "yes" votes (weighted by shares)
    private int totalVotesNo = 0;                  // Total "no" votes (weighted by shares)
    private ArrayList<String> shareholderWhoVoted = new ArrayList<>();  // Names of shareholders who've already voted

    // List of everyone who owns shares
    private ArrayList<Shareholder> listOfInvestors = new ArrayList<>();

    // Set a cap on total shares a company can issue
    private final int MAX_TOTAL_SHARES = 10000;
    
    /**
     * Constructor to create a new company with initial details and founder's shares.
     *
     * @param companyname                         Name of the company
     * @param founder                             Founder of the company
     * @param founderShares                       Number of shares owned by the founder
     * @param availableShares                     Number of shares available for investors
     * @param priceForEachShare                   Price per share
     * @param minimumSharePerInvestor             Minimum shares an investor can purchase
     * @param maximumSharePerInvestor             Maximum shares an investor can purchase
     */
    /**
     * Constructor: When we create a company, we pass in the founder and initial settings.
     */
    public Company(String companyName, Shareholder founder, int founderShares,
                   int availableShares, double price, int minimumSharesPerInvestor, int maximumSharesPerInvestor) {
        this.comapnyName = companyName;
        this.founder = founder;
        this.totalSharesIssued = founderShares;
        this.availableShares = availableShares;
        this.priceForEachShare = price;
        this.minimumSharesPerInvestor = minimumSharesPerInvestor;
        this.maximumSharesPerInvestor = maximumSharesPerInvestor;
        listOfInvestors.add(founder); // Add the founder to the list of investors
    }

    /**
     * Get the name of the company.
     */
    public String getName() {
        return comapnyName;
    }

    /**
     * Find out how many shares have been issued.
     */
    public int getTotalIssuedShares() {
        return totalSharesIssued;
    }

    /**
     * Get the full list of investors (people who own shares).
     */
    public ArrayList<Shareholder> getInvestors() {
        return listOfInvestors;
    }

    /**
     * Add a new investor to the company — but only if we don’t exceed our max share limit.
     */
    public void addInvestor(Shareholder s, int shares) {
        if ((totalSharesIssued + shares) > MAX_TOTAL_SHARES) {
            System.out.println("You can't add that many shares — it would exceed the limit of " + MAX_TOTAL_SHARES + ".");
            return;
        }
        listOfInvestors.add(s); // this will add a shareholder to the list
        totalSharesIssued += shares; // Update the total Issued Shares
        availableShares -= shares; // Reduce the available shares
    }

    /**
     * Check if there are enough shares left to sell to a new investor.
     */
    public boolean hasShareAvailability(int requestedShares) {
        return requestedShares <= availableShares;
    }

    /**
     * Find out what each share costs.
     */
    public double getPricePerShare() {
        return priceForEachShare;
    }

    /**
     * See the minimum number of shares someone must buy.
     */
    public int getMinSharesPerInvestor() {
        return minimumSharesPerInvestor;
    }

    /**
     * See the maximum number of shares someone is allowed to buy.
     */
    public int getMaxSharesPerInvestor() {
        return maximumSharesPerInvestor;
    }

    /**
     * This method distributes money (dividends) to all shareholders based on how many shares they own.
     */
    public void declareDividend(double amount, String paymentDate) {
        this.dividendAmountPerShare = amount;
        this.dividendPaymentDate = paymentDate;

        System.out.println("\n--- Dividend Report for " + comapnyName + " ---");
        System.out.println("Dividend per share: $" + dividendAmountPerShare); // this will print the dividend amount per share
        System.out.println("Payment Date: " + dividendPaymentDate); // this will print the date of dividend distribution
        System.out.println("Announced by: " + founder.getName());

        double totalPayment = 0;
        for (Shareholder s : listOfInvestors) {
            double payout = s.getSharesOwned() * amount;
            System.out.printf("$%.2f - %s%n", payout, s.getName());
            totalPayment += payout;
        }
        System.out.printf("Total paid: $%.2f%n", totalPayment); // Calculate pay out for each shareholder by accumulating the total payment
    }

    /**
     * This starts a voting session with a topic (like "Elect new CEO").
     */
    public void setupVote(String topic) {
        this.activeVoteTopic = topic;
        this.isVotingSessionOpen = true;
        System.out.println("Voting has been opened on: " + topic);
    }

    /**
     * Check if voting is currently open.
     */
    public boolean isVotingOpen() {
        return isVotingSessionOpen;
    }

    /**
     * Get the current topic that’s being voted on.
     */
    public String getVoteTopic() {
        return activeVoteTopic;
    }

    /**
     * Close the voting session.
     */
    public void closeVote() {
        isVotingSessionOpen = false;
    }

    /**
     * This method allows a shareholder to cast their vote.
     * Their voting power depends on how many shares they own.
     */
    public void recordVote(String shareholderName, boolean voteChoice, int sharesOwned) {
        if (!isVotingSessionOpen) {
            System.out.println("Voting hasn't started yet.");
            return;
        }

        if (shareholderWhoVoted.contains(shareholderName)) {
            System.out.println("This shareholder already voted.");
            return;
        }

        if (voteChoice) {
            totalVotesYes += sharesOwned;
        } else {
            totalVotesNo += sharesOwned;
        }

        shareholderWhoVoted.add(shareholderName);
        System.out.println("Vote recorded for " + shareholderName);
    }

    /**
     * When voting is over, this method shows the results and resets everything.
     */
    public void showVoteResults() {
        if (!isVotingSessionOpen && totalVotesYes == 0 && totalVotesNo == 0) {
            System.out.println("There was no vote session.");
            return;
        }

        System.out.println("\n--- Voting Results for " + comapnyName + " ---");
        System.out.println("Topic: " + activeVoteTopic);
        System.out.println("Yes votes: " + totalVotesYes);
        System.out.println("No votes: " + totalVotesNo);

        int totalVotes = totalVotesYes + totalVotesNo;
        if (totalVotes > 0) {
            double yesPercent = (totalVotesYes * 100.0) / totalVotes;
            double noPercent = (totalVotesNo * 100.0) / totalVotes;

            System.out.printf("Yes %%: %.2f%%\n", yesPercent);
            System.out.printf("No %%: %.2f%%\n", noPercent);
        }

        // Reset everything for the next vote
        closeVote();
        activeVoteTopic = "";
        shareholderWhoVoted.clear();
        totalVotesYes = 0;
        totalVotesNo = 0;

        System.out.println("Voting session ended.");
    }

    /**
     * This finds the shareholder who owns the most shares in the company.
     */
    public Shareholder getTopShareholder() {
        Shareholder topShareholder = null;
        for (Shareholder shareholder : listOfInvestors) {
            if (topShareholder == null || shareholder.getSharesOwned() > topShareholder.getSharesOwned()) {
                topShareholder = shareholder;
            }
        }
        return topShareholder;
    }
}
