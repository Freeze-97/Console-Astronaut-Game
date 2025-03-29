package astronauts.classes;

/**
 * This Java file is a driver, meaning it will run the simulation.
 * Some basic application of the Astronaut classes are shown here
 * This is for testing
 */
public class Gameplay {

	public static void main(String[] args) {
		// Create astronauts
		BlueAstronaut b1 = new BlueAstronaut("Bob", 20, 6, 30);
		BlueAstronaut b2 = new BlueAstronaut("Heath", 30, 3, 21);
		BlueAstronaut b3 = new BlueAstronaut("Albert", 44, 2, 0);
		BlueAstronaut b4 = new BlueAstronaut("Angel", 0, 1, 0);
		RedAstronaut r1 = new RedAstronaut("Liam", 19, "experienced");
		RedAstronaut r2 = new RedAstronaut("Suspicious Person", 100, "expert");
		
		// Astronaut actions
		r1.sabotage(b1);
		System.out.println(b1.getName() + " - Sus Level: " + b1.getSusLevel() 
		+ " - Frozen: " + b1.getFrozen());
		
		r1.freeze(r2); // Nothing should happen
		r1.freeze(b3);
		System.out.println(r1.getName() + " - Sus Level: " + r1.getSusLevel() 
		+ " - Frozen: " + r1.getFrozen());
		
		b3.emergencyMeeting(); // Nothing should happen, he is frozen
		r2.emergencyMeeting(); // Should be a tie
		b1.emergencyMeeting();
		System.out.println(r2.getName() + " - Frozen: " + r2.getFrozen());
		
		b2.completeTask();
		System.out.println(b2.getName() + " - number of tasks left: " + b2.getNumTasks());
		
		b2.completeTask(); // Should be done with all tasks and have suslevel 15
		System.out.println(b2.getName() + " - number of tasks left: " + b2.getNumTasks() 
		+ " - Sus Level: " + b2.getSusLevel());
		
		b2.completeTask(); // nothing should happen
		r1.freeze(b4);
		System.out.println(b4.getName() + " - Frozen: " + b4.getFrozen());
		System.out.println(r1.getName() + " - Sus Level: " + r1.getSusLevel());
		
		r1.sabotage(b1);
		System.out.println(b1.getName() + " - Sus Level: " + b1.getSusLevel());
		
		r1.sabotage(b1);
		System.out.println(b1.getName() + " - Sus Level: " + b1.getSusLevel());
		
		r1.freeze(b1);
		System.out.println(b1.getName() + " - Frozen: " + b1.getFrozen());
		
		b4.emergencyMeeting();
		System.out.println(r1.getName() + " - Frozen: " + r1.getFrozen());
		
	}

}
