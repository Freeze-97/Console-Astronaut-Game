package astronauts.classes;

import java.util.Arrays;

/**
 * A subclass of Player
 * Blue ones are the "good guys" trying to complete tasks
 * They can complete tasks
 */

public class BlueAstronaut extends Player implements Crewmate {
	private int numTasks;
	private int taskSpeed;
	private boolean firstTime; // First time completing all tasks

	public BlueAstronaut(String name, int susLevel, int numTasks, int taskSpeed) {
		super(name, susLevel);
		this.numTasks = numTasks;
		this.taskSpeed = taskSpeed;
		firstTime = true;
	}
	
	public BlueAstronaut(String name) {
		super(name, 15);
		numTasks = 6;
		taskSpeed = 10;
		firstTime = true;
	}

	@Override
	public void completeTask() {
		// Frozen cannot complete tasks
		if(frozen)
			return;
		
		if(taskSpeed > 20) {
			System.out.println("Number of tasks has decrease by 2.");
			numTasks -= 2;
		}
		else {
			System.out.println("Number of tasks has decrease by 1.");
			numTasks -= 1;
		}
		
		if(numTasks < 0) // num of tasks should not be below 0
			numTasks = 0;
		
		if(firstTime && numTasks == 0) {
			System.out.println("I have completed all my tasks.");
			susLevel *= 0.5;
			firstTime = false;
		}
	}
	
	public int getNumTasks() {
		return numTasks;
	}

	@Override
	public void emergencyMeeting() {
		if(frozen)
			return;
		
		Player mostSus = null;
		int highestSusLevel = -1;
		Arrays.sort(players);
		for(int i = players.length - 1; i >= 0; i--) {
			Player p = players[i];
			if(!p.getFrozen()) {
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
    public boolean equals(Object o) {
        if (o instanceof BlueAstronaut) {
            BlueAstronaut other = (BlueAstronaut) o;
            return this.name.equals(other.name) &&
                   this.frozen == other.frozen &&
                   this.susLevel == other.susLevel &&
                   this.numTasks == other.numTasks &&
                   this.taskSpeed == other.taskSpeed;
        }
        return false;
    }

    @Override
    public String toString() {
        String output = super.toString() + " I have " + numTasks + " tasks left over.";
        return susLevel > 15 ? output.toUpperCase() : output;
    }
}
