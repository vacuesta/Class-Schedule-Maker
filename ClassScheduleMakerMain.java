/* User inputs a .csv or inputs manually characteristics of potential courses
 * 	Convert all time to military
 *  Have three options for days to meet; if meet less than 3, more days are set to the first day
 * Create array of course objects
 *  Each student has one, as long with their name and semester planning for
 * Two arrays: all class times and output/suggested schedule class times
 * Prioritize class by number of available times
 * 	If class occurs once, automatically output to new array
 * Check if time and day is overlapping
 * 	If does not overlap, add to new schedule
 * 	Add to new schedule until all courses are fulfilled
 * 	  If cannot be fulfilled... idk
 */

import java.util.Arrays;
import java.util.Scanner;

public class ClassScheduleMakerMain {
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("What is the student's name?");
		String sN = keyboard.nextLine();
		System.out.println("What semester are they planning for: spring, summer, fall, or winter?");
		String sem = keyboard.nextLine();
		System.out.println("What year are they planning for?");
		int y = keyboard.nextInt();
		System.out.println("How many class options do they have in total?");
		int howManyClasses = keyboard.nextInt();
		Student newStudent = new Student(sN, sem, y, howManyClasses);
		keyboard.nextLine();
		System.out.println("If you would like to upload a .csv, type \"Y\". If you would like to enter data manually, press anything else.");
		String chooseInput = keyboard.nextLine();
		
		if(chooseInput.equalsIgnoreCase("Y")){
			System.out.println("Enter the address of the .csv you would like to upload.");
			String csvAddress = keyboard.nextLine();
			csvToJavaObject newAdd = new csvToJavaObject(csvAddress);
			newAdd.convertCsvToJava();
			for(int j = 0; j < newAdd.courseList.size(); j++){
				Course demoClass = newAdd.getCoursefromcsv(j);
				newStudent.setClassOptions(j, demoClass);
			}
			System.out.println(".csv has been successfully uploaded.\n");
		}else{
			for(int i = 0; i < howManyClasses; i++){
				System.out.println("What is the class department and code number? (ex. COMP170)"); //okay why is it skipping?
				String cDcN = keyboard.nextLine();
				System.out.println("Who is the professor?");
				String prof = keyboard.nextLine();
				System.out.println("When does the class start? (military time)");
				String sT = keyboard.nextLine();
				System.out.println("When does the class end? (military time)");
				String eT = keyboard.nextLine();
				System.out.println("For the next questions, please enter days of the week as follows: \nMonday = \"M\"\nTuesday = \"T\"\nWednesday = \"W\"\nThursday = \"R\", and \nFriday = \"F\"");
				System.out.println("What day does the class meet first?");
				String mT1 = keyboard.nextLine();
				System.out.println("What day does the class meet second? If it does not meet a second day, hit enter.");
				String mT2 = keyboard.nextLine();
				System.out.println("What day does the class meet third? If it does not meet a third day, hit enter.");
				String mT3 = keyboard.nextLine();
		
				Course demoClass = new Course(cDcN, prof, sT, eT, mT1, mT2, mT3);
				newStudent.setClassOptions(i, demoClass);
				System.out.println("You have entered " + (i + 1) + " out of " + howManyClasses + " classes.\n");
			}
		}

		//calculations derived from input
		System.out.println(newStudent.listCourseOptions().toString());
		newStudent.calculateFrequency();
		System.out.println(newStudent.makeClassSchedule());
	}
}

/*sample Rome input:
* Angela, fall, 2017, 12
* 1. ITAL101, Conestabile, 12:15, 13:10, MTR
* 2. ITAL101, Curioso, 13:00, 13:55, MTR
* 3. ITAL101, Faramondi, 14:00, 14:55, MTR
* 4. ITAL101, Gurtner, 16:00, 16:55, MTR
* 5. lit, Geoghegan, 09:30, 12:00, R
* 6. lit, Mitrano, 14:00, 16:30, W
* 7. lit, Castaldo, 15:40, 16:55, MW
* 8. PHIL181, Giachetti, 17:00, 18:15, MW
* 9. PHIL181, Rocchi, 17:00, 18:15, TR
* 10. PHIL277R, Giachetti, 15:40, 16:55, MW
* 11. theo, Kutschera, 09:30, 12:30, W
* 12. theo, Sawyer, 14:00, 16:30, W
*/
