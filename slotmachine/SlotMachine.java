import java.util.Random;
import java.io.*;
import java.util.Scanner;


public class SlotMachine {

	private static final String slots[] = {"🍇", "🍈", "🍉", "🍊", "🍋", "🍌", "🍍", "🍎", "🍏", "🍐", "🍑", "🍒", "🍓", "🍅","🍆","🌽", "🌶️","🍄"};
	private static final double victoryChance = 0.2;

	private static Random random = new Random();	
	private static int credits = 20;

	private static String[] getRandomSlots() {

		String[] slots = new String[3];	

		for (int i = 0; i < 3; ++i) {			
			int iSlot = random.nextInt(SlotMachine.slots.length);
			slots[i] = SlotMachine.slots[iSlot];
		}

		return slots;
	}
	
	private static Boolean areSlotsEqual(String[] slots) {
		return slots[0].equals(slots[1]) && slots[0].equals(slots[2]);
	}
	
	private static String[] getRandomSlots(double victoryProbability) {

		if (random.nextDouble() < victoryProbability) {
			int iSlot = random.nextInt(SlotMachine.slots.length);
	    		String[] slots = new String[3];	

			for (int i = 0; i < 3; ++i) {
				slots[i] = SlotMachine.slots[iSlot];
			}

			return slots;
		} else {
			String[] slots = getRandomSlots();
		
			while (areSlotsEqual(slots)){
				slots = getRandomSlots();
			}
			return slots;
		}
	}

	private static void printSlots(String[] slots) {

		for (int i = 0; i < slots.length; ++i) {
			System.out.print(slots[i]);
			if (i < slots.length - 1) System.out.print("  ");
		}

		System.out.println();
	}

	private static void printRandomSlots() {
		printSlots(getRandomSlots());
	}

	private static void clearScreen() {
		System.out.print("\033\143");
	}

	private static void clearLine() {
		System.out.print(String.format("\033[%dA",1)); // Move up
		System.out.print("\033[2K"); // Erase line content
	}
	
	private static void waitForEnter() {
	    System.out.println("Press ENTER to WIN");
   		readEnter(); 
		clearLine();
		clearLine();
	}
	
	private static Boolean playRound(Boolean isFirstRound) throws InterruptedException {

		clearScreen();
		System.out.println(".:: Slot Machine ::.");
		System.out.format("Your credits: %d%n", credits);
   		
   		if (isFirstRound) {
		    waitForEnter();
		    --credits;
		    clearLine();
		    System.out.format("Your credits: %d%n", credits);
   		}
		
		for (int i = 0; i < 20; ++i) {
			printRandomSlots();
			Thread.sleep(20);
			clearLine();
		}

		String[] slots = getRandomSlots(SlotMachine.victoryChance);
		printSlots(slots);
		
		if (areSlotsEqual(slots)) {
			credits += 10;		
			System.out.println("You won!");		
		}

		
		if (credits == 0) {
			System.out.println("You lose!");
			return true;		
		}

		waitForEnter();
		--credits;
		
		return false;
	}

	private static void readEnter() {
		Scanner scanner = new Scanner(System.in);
		String readString = scanner.nextLine();
	}		

	public static void main (String args[]) {
	    
        	Boolean gameOver = false;
	        Boolean isFirstRound = true;
		try {
			while (gameOver == false) {
	    			gameOver = playRound(isFirstRound);
		    		isFirstRound = false;
		    	}
		} catch (Exception e) {}
	}
}
