package ch.nostromo.luckynumbers;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/**
 * Base application of the lucky numbers challenge. Stupid on purpouse ;)
 * 
 * @author Bernhard von Gunten <bvg@nostromo.ch>
 *
 */
public class LuckyNumbers {

	Random r = new Random();

	public static String MESSAGE_0 = "Hey Gang!";
	public static String MESSAGE_1 = "Well, I do agree ... knowing the (non obfuscated) code does help ... quite a bit.";
	public static String MESSAGE_2 = "Though, try to not override the whole application and remember that the most elegant approach wins ;)";
	public static String MESSAGE_3 = "GL & HF! 3b";
	public static String MESSAGe_4 = "PS: Yes I know, the code is stupid, but why ...";

	LuckyNumbers() {
		System.out.println("Welcome to Lucky Numbers (6 out of 42) ! ");
	}

	public final void trueNinjasDoNotModifyThisMethod() {

		Scanner scanner = new Scanner(System.in);
		try {

			System.out.println("Please enter your six numbers of choice divided by a blank. \n(eg: 4 6 10 15 23 32)\n");
			System.out.print(">");
			Set<Integer> entries = null;
			while (true) {
				entries = parseInput(scanner.nextLine());
				if (entries != null && entries.size() == 6) {
					break;
				}
			}

			System.out.println("Your numbers: " + entries);

			Set<Integer> drawnNumbers = new HashSet<Integer>();
			for (int i = 0; i < 6; i++) {
				drawnNumbers.add(drawNextNumber(drawnNumbers));
			}

			if (drawnNumbers.size() != 6) {
				System.out.println("OMG, Somebody manipulated the machine ...");
				System.exit(-1);
			}

			System.out.println("Drawn numbers: " + drawnNumbers);

			int hits = 0;
			for (Integer entry : entries) {
				if (drawnNumbers.contains(entry)) {
					hits++;
				}
			}

			if (hits != 6) {
				System.out.println("\nUnlucky, you guessed only " + hits + " numbers right! Better luck next time ;)");
			} else {
				System.out.println("\n** AWESOME ** You win!\nFor your certificate of awesomeness contact bernhard.vongunten@swisslog.com.");
			}

			
		} catch (Exception e) {
			System.out.println(
					"Well, that's funny ... an unexpected - that should never happend - exception occured... better luck next time ;)");
			e.printStackTrace();
		} finally {
			System.out.println("\nPress Enter to finish Lucky Numbers ...");
			scanner.nextLine();

			scanner.close();
		}

	}

	private final Integer drawNextNumber(Set<Integer> knownNumbers) {
		while (true) {
			Integer number = r.nextInt(42);
			if (!knownNumbers.contains(number)) {
				knownNumbers.add(number);
				return number;
			}
		}
	}

	private final Set<Integer> parseInput(String numberLine) {
		String[] result = numberLine.split("\\s");
		if (result.length != 6) {
			System.out.println("Unable to parse your entry. Not 6 numbers");
			return null;
		}

		Set<Integer> numbers = new HashSet<Integer>();

		for (String number : result) {
			try {
				Integer num = Integer.valueOf(number);
				if (numbers.contains(num)) {
					System.out.println("Unable to parse your entry. Duplicate number.");
					return null;
				}

				numbers.add(num);

			} catch (NumberFormatException ex) {
				System.out.println("Unable to parse your entry. Number format incorrect.");
				return null;
			}
		}

		return numbers;
	}

	public static void main(String... args) {
		new LuckyNumbers().trueNinjasDoNotModifyThisMethod();
	}

}
