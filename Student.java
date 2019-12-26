import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;


//the "container" object to store the "other" object of course
public class Student {
	private Course[] classOptions, newSchedule;
	private String name, semester, listStudentClasses = "", listNewSchedule = "";
	private int year;
	private HashMap<String, Integer> courseFrequency = new HashMap<String, Integer>();
	private String[] sortedCourseFrequency;
	
	public Student(){
		name = "";
		semester = "";
		year = 2018;
		Course[] cOs = new Course[0];
		classOptions = cOs.clone();
		newSchedule = null;
	}
	
	public Student(String n, String s, int y, int cO){
		name = n;
		semester = s;
		year = y;
		Course[] cOs = new Course[cO];
		classOptions = cOs.clone(); //for some reason doesn't work without a clone
		newSchedule = null;
	}
	
	public void setClassOptions(int j, Course dC){
		classOptions[j] = dC;
	}
	
	public String listCourseOptions(){
		for(int i = 0; i < classOptions.length; i++){
			listStudentClasses = listStudentClasses + classOptions[i].whenIsClass();
		}
		return name + " in " + semester + " " + year + " has the following classes available: \n" + listStudentClasses;
	}
	
	//gets frequency of courses and orders them from least occurring to most occurring
	public void calculateFrequency(){
		for(int i = 0; i < classOptions.length; i++){ 
			if(courseFrequency.containsKey(classOptions[i].getCourseDeptNumber())){
				courseFrequency.put(classOptions[i].getCourseDeptNumber(), courseFrequency.get(classOptions[i].getCourseDeptNumber()) + 1);	
			}else{
				courseFrequency.put(classOptions[i].getCourseDeptNumber(), 1);
			}
		}
		//sorts map by frequency of classes
		Map<Object, Object> sortedMap = courseFrequency.entrySet().stream().sorted(Entry.comparingByValue()).collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		String[] sCF = sortedMap.keySet().toArray(new String[0]); //makes array of course names that are sorted by frequency
		this.sortedCourseFrequency = sCF.clone();
		Course[] nS = Stream.generate(() -> new Course()).limit(sCF.length).toArray(Course[]::new);
		this.newSchedule = nS.clone(); //each student now has an empty newSchedule at length of classes
	}
	
	//calculates class schedule for no
	public String makeClassSchedule(){
		for(int i = 0; i < sortedCourseFrequency.length; i++){ //have only one of each course
		for(int j = 0; j < classOptions.length; j++){ //sorts through all course options
			if(sortedCourseFrequency[i].equals(classOptions[j].getCourseDeptNumber())){ //if a class options matches a class in course frequency
				for(int k = 0; k < newSchedule.length; k++){ //checks through all current entries in new schedule so they don't overlap
					if(classOptions[j].getStartTime().isBefore(newSchedule[k].getEndTime()) & newSchedule[k].getStartTime().isBefore(classOptions[j].getEndTime()) & classOptions[j].getDayOfWeek1() == newSchedule[k].getDayOfWeek1()){
					}else if(classOptions[j].getStartTime().isBefore(newSchedule[k].getEndTime()) & newSchedule[k].getStartTime().isBefore(classOptions[j].getEndTime()) & classOptions[j].getDayOfWeek1() == newSchedule[k].getDayOfWeek2()){
					}else if(classOptions[j].getStartTime().isBefore(newSchedule[k].getEndTime()) & newSchedule[k].getStartTime().isBefore(classOptions[j].getEndTime()) & classOptions[j].getDayOfWeek1() == newSchedule[k].getDayOfWeek3()){
					}else if(classOptions[j].getStartTime().isBefore(newSchedule[k].getEndTime()) & newSchedule[k].getStartTime().isBefore(classOptions[j].getEndTime()) & classOptions[j].getDayOfWeek2() == newSchedule[k].getDayOfWeek1()){
					}else if(classOptions[j].getStartTime().isBefore(newSchedule[k].getEndTime()) & newSchedule[k].getStartTime().isBefore(classOptions[j].getEndTime()) & classOptions[j].getDayOfWeek2() == newSchedule[k].getDayOfWeek2()){
					}else if(classOptions[j].getStartTime().isBefore(newSchedule[k].getEndTime()) & newSchedule[k].getStartTime().isBefore(classOptions[j].getEndTime()) & classOptions[j].getDayOfWeek2() == newSchedule[k].getDayOfWeek3()){
					}else if(classOptions[j].getStartTime().isBefore(newSchedule[k].getEndTime()) & newSchedule[k].getStartTime().isBefore(classOptions[j].getEndTime()) & classOptions[j].getDayOfWeek3() == newSchedule[k].getDayOfWeek1()){
					}else if(classOptions[j].getStartTime().isBefore(newSchedule[k].getEndTime()) & newSchedule[k].getStartTime().isBefore(classOptions[j].getEndTime()) & classOptions[j].getDayOfWeek3() == newSchedule[k].getDayOfWeek2()){
					}else if(classOptions[j].getStartTime().isBefore(newSchedule[k].getEndTime()) & newSchedule[k].getStartTime().isBefore(classOptions[j].getEndTime()) & classOptions[j].getDayOfWeek3() == newSchedule[k].getDayOfWeek3()){
					}else{
						newSchedule[i] = classOptions[j]; //add to newSchedule if passes overlap tests
						j = classOptions.length;
						k = newSchedule.length; //end adding class to newSchedule for next course in sortedCourseFrequency
					}
				}
			}
		}
		}

		for(int l = 0; l < newSchedule.length; l++){
			listNewSchedule = listNewSchedule + newSchedule[l].whenIsClass();
		}
		return name + " in " + semester + " " + year + " has the suggested schedule: \n" + listNewSchedule;
	}
	
}