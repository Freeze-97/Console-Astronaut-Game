package astronauts.classes;

import java.util.Arrays;

/**
 * Player type that is an impostor
 * can sabotage and freeze players
 * "Bad guys"
 */
public class RedAstronaut extends Player implements Impostor{
	private String skill;
	
	
	public RedAstronaut(String name, int susLevel, String skill) {
		super(name, susLevel); // Constructor for Player
		this.skill = skill;
	}
	
	public RedAstronaut(String name) {
		super(name, 15);
		this.skill = "experienced";
	}
	
	public void setSkill(String skill) {
		this.skill = skill;
	}
	
	public String getSkill() {
		return skill;
	}

	@Override
	public void emergencyMeeting() {
		// Frozen players cannot call a meeting
		if(frozen)
			return;
		
		Player mostSus = null;
		int highestSusLevel = -1;
		Arrays.sort(players);
		for(int i = players.length - 1; i >= 0; i--) {
			Player p = players[i];
			if(!p.getFrozen() && p != this) {
				if(mostSus == null) { // Last player in the list has the most
					mostSus = p;
					highestSusLevel = p.getSusLevel();
				}
				else if(p.getSusLevel() == highestSusLevel) {
					// Found a tie
					System.out.println("There is a tie, no one will be voted off.");
					return;
				} else {
					// Next player has less sus, break, list is already sorted
					break;
				}
			}
		}
		
		if(mostSus != null) {
			mostSus.setFrozen(true);
			System.out.println(mostSus.getName() + " has been frozen in the emergency meeting!");
			
			// Check if the game should be over
			checkGameOver();
		}
	}

	@Override
	public void freeze(Player player) {
		// Cannot freeze another impostor or a player that is already frozen
		if(player instanceof Impostor || player.getFrozen()) {
			System.out.println("Cannot freeze.");
			return;
		}
		
		// Freeze is successful if red astronaut's susLevel is less than the Player’s
		if(susLevel < player.getSusLevel()) {
			player.setFrozen(true);
			System.out.println(player.getName() + " has been frozen!");
		}
		else { 
			susLevel *= 2;
			System.out.println("Your susLevel has doubled.");
		}
		
		// Check if game is over
		checkGameOver();
	}

	@Override
	public void sabotage(Player player) {
		// Cannot sabotage another impostor or sabotage anyone if you are frozen
		// or sabotage a player who is already frozen
		if(player instanceof Impostor || this.frozen || player.getFrozen()) {
			System.out.println("Cannot sabotage.");
			return;
		}
		
		// If the Impostor’s susLevel is under 20, 
		// they are able to increase the Crewmate’s susLevel by 50%
		if(susLevel < 20) {
			int newSusLevel = (int) (player.getSusLevel() * 1.5);
			player.setsusLevel(newSusLevel);
			System.out.println("Increased the Crewmate’s susLevel by 50%.");
		}
		else {
			int newSusLevel = (int) (player.getSusLevel() * 1.25);
			System.out.println("Increased the Crewmate’s susLevel by 25%.");
			player.setsusLevel(newSusLevel);
		}
	}
	
    @Override
    public boolean equals(Object o) {
        if (o instanceof RedAstronaut) {
            RedAstronaut other = (RedAstronaut) o;
            return this.name.equals(other.name) &&
                   this.frozen == other.frozen &&
                   this.susLevel == other.susLevel &&
                   this.skill.equals(other.skill);
        }
        return false;
    }

    @Override
    public String toString() {
        String output = super.toString() + " I am an " + skill + " player!";
        return susLevel > 15 ? output.toUpperCase() : output;
    }
}
