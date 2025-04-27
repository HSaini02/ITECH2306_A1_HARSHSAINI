/*
 * Created by [Harsh Saini]
 * Student ID: 30414383
 * Subject: ITECH2306 Agile Coding
 */

package main;

/**
 * This class represents an individual shareholder's vote on a company voting topic.
 * It keeps track of the shareholder's name, number of shares, and voting preference (yes/no).
 */
public class Vote {

    // Name of the shareholder voting
    private String voterName;

    // True if voted "yes", false if voted "no"
    private boolean votedYes;

    // Number of shares the voter owns (vote weight)
    private int ownedSharesForVote;

    /**
     * Constructor to create a vote for a shareholder.
     *
     * @param voterName Name of the shareholder
     * @param votedYes Vote choice (true for yes, false for no)
     * @param ownedSharesForVote Number of shares owned
     */
    public Vote(String voterName, boolean votedYes, int ownedSharesForVote) {
        this.voterName = voterName;
        this.votedYes = votedYes;
        this.ownedSharesForVote = ownedSharesForVote;
    }

    /**
     * @return the name of the voter
     */
    public String getVoterName() {
        return voterName;
    }

    /**
     * @return true if voted yes, false otherwise
     */
    public boolean hasVotedYes() {
        return votedYes;
    }

    /**
     * @return number of shares the voter owns
     */
    public int getOwnedSharesForVote() {
        return ownedSharesForVote;
    }
}
