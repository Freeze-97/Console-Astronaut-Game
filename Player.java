package astronauts.classes;

import java.util.Arrays;

/**
 * Abstract class of Player
 * Comparable used to compare suslevel
 * Crew wins if all impostors are frozen
 * Impostors win if they are half or more of the total crew (unfrozen)
 */
public abstract class Player implements Comparable<Player> {
	protected String name;
	protected int susLevel;
	protected boolean frozen;
	protected static Player[] players = new Player[0];
	
	public Player(String name, int susLevel) {
		this.name = name;
		this.susLevel = susLevel;
		this.frozen = false;
		
		// Add new player to the static array
        Player[] temp = Arrays.copyOf(players, players.length + 1);
        temp[players.length] = this;
        players = temp;
	}
	
	public void setsusLevel(int susLevel) {
		this.susLevel = susLevel;
	}
	
	public void setFrozen(boolean frozen) {
		this.frozen = frozen;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getSusLevel() {
		return susLevel;
	}
	
	public boolean getFrozen() {
		return frozen;
	}
	
	public String getName() {
		return name;
	}
	
	public void checkGameOver() {
		int impostorCount = 0;
		int unfrozenCount = 0;
		
		for(Player p : players) {
			if(!p.getFrozen()) {
				unfrozenCount++;
				if(p instanceof Impostor) {
					impostorCount++;
				}
			}
		}
		
		// Crew wins if all impostors are frozen
		if(impostorCount == 0) {
			System.out.println("Crewmates win! All impostors have been frozen.");
			System.exit(0);
		}
		
		// If impostors are 50% or more of the total unfrozen crew, they win
		if(impostorCount >= unfrozenCount / 2.0) {
			System.out.println("Crewmates win! All impostors have been frozen.");
			System.exit(0);
		}
	}
	
	public abstract void emergencyMeeting();
	 @Override
	 public String toString() {
		 return "My name is " + name + ", and I have a suslevel of " + susLevel + ". I am currently " + 
	               (frozen ? "frozen" : "not frozen") + ".";
	 }
	
	// Return negative if this player is less than other, 0 if even, positive if more
	@Override
	public int compareTo(Player player) {
		return Integer.compare(this.susLevel, player.susLevel);
	}
}
