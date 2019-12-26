import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;

public class csvToJavaObject {
	private String csvAddress;
	List<Course> courseList  = new ArrayList<Course>();
	
	public csvToJavaObject(String csvA){
		csvAddress = csvA;
	}
	
	public void convertCsvToJava(){
		String csvFileToRead = csvAddress;
		BufferedReader br = null;
		String line = "";
		String splitBy = ",";
		
		try{
			br = new BufferedReader(new FileReader(csvFileToRead));
			while ((line = br.readLine()) != null){
				String[] course = line.split(splitBy);
				Course courseObject = new Course();
				
				courseObject.setCourseDeptNumber(course[0]);
				courseObject.setProf(course[1]);

				if(course[2].length() == 4){
					courseObject.setStartTime(LocalTime.parse("0" + course[2]));
				}else{
					courseObject.setStartTime(LocalTime.parse(course[2]));
				}
				
				if(course[3].length() == 4){
					courseObject.setEndTime(LocalTime.parse("0" + course[3]));
				}else{
					courseObject.setEndTime(LocalTime.parse(course[3]));
				}
					
				if(course[4].length() == 3){
					courseObject.setMeetingDay1(course[4].charAt(0));
					courseObject.setMeetingDay2(course[4].charAt(1));
					courseObject.setMeetingDay3(course[4].charAt(2));
				}else if(course[4].length() == 2){
					courseObject.setMeetingDay1(course[4].charAt(0));
					courseObject.setMeetingDay2(course[4].charAt(1));
					courseObject.setMeetingDay3(course[4].charAt(0));
				}else if(course[4].length() == 1){
					courseObject.setMeetingDay1(course[4].charAt(0));
					courseObject.setMeetingDay2(course[4].charAt(0));
					courseObject.setMeetingDay3(course[4].charAt(0));
				}
				courseList.add(courseObject);
			}
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if(br != null){
				try{
					br.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
	}

	public Course getCoursefromcsv(int i){
		Course fromCourseList = courseList.get(i);
		return fromCourseList;
	}
}