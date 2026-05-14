import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Sorted List Program - Week 3 Assignment
 * Ebony Morrow
 * CPT 307 Data Structures and Algorithms
 * Professor Joel Short
 * December 23, 2024
 */

//Used to sort compareList
class Equipment implements Comparable<Equipment> {
	String EquipChoice;
	String CalcROI;
	
	Equipment(String equipChoice, String calcROI) {
		this.EquipChoice = equipChoice;
		this.CalcROI = calcROI;
	}
	
	@Override
	public int compareTo(Equipment s) {
		if (CalcROI.compareTo(s.CalcROI ) > 0) {
			return 1;
		}
		else if (CalcROI == s.CalcROI) {
			return 0;
		}
		else {
			return -1;
		}
	}	
}

public class calculateReturnInvestmentProgram {
	

	public static void main(String[] args) {
		
		// List that will be used to sort in the calculateReturnInvestmentProgram 
		LinkedList<String> investReturnList = new LinkedList<>();
		
		// List to add connecting equipment and calcROI as nested list
		LinkedList<String> compareList = new LinkedList<>();
		
		//Reads user input
		Scanner scanner2 = new Scanner(System.in);
		
		String calcMoreEquip = "Y";
		
		while (!calcMoreEquip.equals("N")) {
			
			if (compareList.isEmpty()) {
				
				calculateReturnInvestmentProgram.equipmentUserList(compareList);
			}
			
			for (int i = 0; i < compareList.size(); i += 2) {
				if (i + 1 < compareList.size()) {
					investReturnList.addAll(Arrays.asList(compareList.get(i), compareList.get(i + 1)));
				}
				else {
					investReturnList.addAll(Arrays.asList(compareList.get(i)));
				}
			}
			
			System.out.println("The return on investment is listed in order of best to worse.");
				
			Collections.sort(compareList, Comparator.comparing(sublist -> compareList.get(1)));
			
			
			for (int i = 0; i < compareList.size(); i++) {
					System.out.println(compareList.get(i));
			}
						
			// Prepare the list for a new set of equipment to compare
			compareList.clear();
			
			System.out.println("Compare another list? [Y]/[N]: ");
			calcMoreEquip = scanner2.next().toUpperCase().trim();
			
		}
		scanner2.close();

	}

	private static void equipmentUserList(LinkedList<String> compareList) {

		//Reads user input
		Scanner scanner2 = new Scanner(System.in);
		
		Double equipCost = 0.00;
		Double equipGain = 0.00;
		String userEquipName = "";

		// Changing original line type to String and formatting.
		NumberFormat currency = NumberFormat.getCurrencyInstance();
		
		// Runs equipmentUserList class until user is finished building equipment comparison list
		String buildList;
		
		System.out.print("Create equipment comparison list? [Y]/[N]: ");
		buildList = scanner2.next().toUpperCase().trim();
		System.out.println();
				
		// Equipment list Menu options
		ArrayList<String> equipList = new ArrayList<>();

		equipList.add("CISCO 2900 Series Router C2921-VSEC/K9 Integrated Services Router");
		equipList.add("Ubiquiti ERPOE-5 EdgeRouter PoE Advanced 5-Port Router");
		equipList.add("Adtran 4243908F1 Network Equipment");
		equipList.add("Equipment not listed");

		// Starts program for creating the equipment list for the calculateReturnInvestmentProgram
		
		while (!buildList.equals("N")) {
			try {
				/**
				 * Helps remove white space so the scanner can 
				 * keep reading employees after the first input
				 * employee in the program.
				 */
				scanner2.useDelimiter(System.lineSeparator());

				for (int i = 0; i < equipList.size(); i++) {
					System.out.println(i + ": " + equipList.get(i));
				}
				System.out.println();

				// Receive user input for the compareList
				System.out.print("Please choose a number to add the cost and gain amounts to. ");
				int equipChoice = scanner2.nextInt();

				// Begins switch statement for program to follow

				switch (equipChoice) {

				case 0:
					System.out.print("Please enter the cost of the equipment: ");
					equipCost = scanner2.nextDouble();

					System.out.print("Please enter the gain earned: ");
					equipGain = scanner2.nextDouble();
					System.out.println();

					break;

				case 1:
					System.out.print("Please enter the cost of the equipment: ");
					equipCost = scanner2.nextDouble();

					System.out.print("Please enter the gain earned: ");
					equipGain = scanner2.nextDouble();
					System.out.println();
					break;

				case 2:
					System.out.print("Please enter the cost of the equipment: ");
					equipCost = scanner2.nextDouble();

					System.out.print("Please enter the gain earned: ");
					equipGain = scanner2.nextDouble();
					System.out.println();

					break;

				case 3:
					// Custom input of Equipment not listed
					System.out.print("Please enter the name of the equipment: ");
					userEquipName = scanner2.next();

					System.out.print("Please enter the cost of the equipment: ");
					equipCost = scanner2.nextDouble();

					System.out.print("Please enter the gain earned: ");
					equipGain = scanner2.nextDouble();
					System.out.println();

					break;
				}
				
				// Calculating ROI = (Gain from Investment – Cost of Investment) / Cost of Investment
				final Double updateROI =  (equipGain - equipCost) / equipCost;
				String calcROI = String.valueOf(currency.format(updateROI));
				
				if (equipChoice == 0 || equipChoice == 1 || equipChoice == 2) {
					compareList.addAll(List.of(equipList.get(equipChoice), calcROI));
				} 
				else {
					if (equipChoice == 3) { 
						compareList.addAll(List.of(userEquipName, calcROI));
					}
					break;
				} 
				
				// Giving user the choice to finalize adding to list
				System.out.print("Add another equipment to the comparison list? [Y]/[N]: ");
				buildList = scanner2.next().toUpperCase().trim();
				System.out.println();
				
			} catch (InputMismatchException e) {
				System.out.println("Please read the request carefully and choose a number listed.");
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Please enter a number in decimal form (i.e. xxx.xx).");
			} catch (Exception e) {
				System.out.println("Input not understood. Please try again.");
				break;
			}
		}
		
		buildList.replace("N", "Y");		
	}	
}


