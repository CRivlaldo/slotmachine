import java.util.Random;

public class SlotMachine {

	private static String sectors[] = {"🍇", "🍈", "🍉", "🍊", "🍋", "🍌", "🍍", "🍎", "🍏", "🍐", "🍑", "🍒", "🍓", "🥝", "🍅", "🥑", "🍆", "🥔", "🥕", "🌽", "🌶️", "🥒", "🍄"};

	private static void printRandomSectors(Random random) {
		for (int i = 0; i < 3; ++i) {
			int iSector = random.nextInt(SlotMachine.sectors.length);
			System.out.print (SlotMachine.sectors[iSector]);
			if (i < 2) System.out.print("  ");
		}

		System.out.println();
	}

	private static void clearScreen() {
		System.out.print("\033\143");
	}

	private static void clearLine() {
		System.out.print(String.format("\033[%dA",1)); // Move up
		System.out.print("\033[2K"); // Erase line content
	}

	public static void main (String args[]) throws InterruptedException {
		System.out.println(".:: Slot Machine ::.");
		Random random = new Random();
		for (int i = 0; i < 100; ++i) {
			printRandomSectors(random);
			Thread.sleep(100);
			clearLine();
		}
	}
}