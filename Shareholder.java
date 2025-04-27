package main;

/**
 * This class is all about one person who owns shares in a company.
 * It keeps track of their name and how many shares they have.
 */
public class Shareholder {

    // The name of the person who owns the shares
    private String shareholderName;

    // How many shares this person owns
    private int ownedShares;

    /**
     * When we create a new shareholder, we give them a name and number of shares.
     *
     * @param name   The name of the shareholder (like "John Smith")
     * @param shares The number of shares this person owns when we add them
     */
    public Shareholder(String name, int shares) {
        this.shareholderName = name;
        this.ownedShares = shares;
    }

    /**
     * This just gives us the name of the shareholder when we need it.
     *
     * @return the name of the shareholder
     */
    public String getName() {
        return shareholderName;
    }

    /**
     * This tells us how many shares the person currently owns.
     *
     * @return number of shares
     */
    public int getSharesOwned() {
        return ownedShares;
    }

    /**
     * This calculates how much of the company this person owns,
     * based on how many total shares the company has given out.
     *
     * @param totalShares Total shares issued by the company
     * @return the percentage of company this person owns (as a decimal)
     */
    public double getOwnershipPercentage(int totalShares) {
        if (totalShares == 0) return 0.0; // avoid divide by zero
        return (ownedShares * 100.0) / totalShares;
    }
}
