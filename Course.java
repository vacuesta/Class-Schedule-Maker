import java.time.LocalTime;

public class Course {
	private String courseDeptNumber, professor;
	private char meetingDay1, meetingDay2, meetingDay3;
	private LocalTime startTime, endTime;
	
	//default
	public Course(){
		courseDeptNumber = "";
		professor = "";
		startTime = LocalTime.MAX; //for calculations later
		endTime = LocalTime.MIN;
		meetingDay1 = ' '; //replaced later
		meetingDay2 = ' ';
		meetingDay3 = ' ';
	}
	
	public Course(String cDcN, String p, String sT, String eT, String mD1, String mD2, String mD3){
		courseDeptNumber = cDcN;
		professor = p;
		if(sT.length() == 4){
			startTime = LocalTime.parse("0" + sT);
		}else{
			startTime = LocalTime.parse(sT);
		}
		if(eT.length() == 4){
			endTime = LocalTime.parse("0" + eT);
		}else{
			endTime = LocalTime.parse(eT);
		}
		meetingDay1 = mD1.toUpperCase().charAt(0);
		if(mD2.length() == 0){
			meetingDay2 = meetingDay1;
		}else{
			meetingDay2 = mD2.toUpperCase().charAt(0);
		}
		if(mD3.length() == 0){
			meetingDay3 = meetingDay1;
		}else{
			meetingDay3 = mD3.toUpperCase().charAt(0);
		}

	}
	
	public String getCourseDeptNumber(){
		return courseDeptNumber;
	}
	
	public void setCourseDeptNumber(String cDN){
		courseDeptNumber = cDN;
	}
	
	public void setProf(String p){
		professor = p;
	}
	
	public void setMeetingDay1(char md1){
		meetingDay1 = md1;
	}
	
	public void setMeetingDay2(char md2){
		meetingDay2 = md2;
	}
	
	public void setMeetingDay3(char md3){
		meetingDay3 = md3;
	}
	
	public String whenIsClass(){
		if(meetingDay2 == meetingDay1){
			return(courseDeptNumber + " with " + professor + " is from " + startTime + " to " + endTime + " and meets on " + meetingDay1 + ".\n");
		}else if(meetingDay3 == meetingDay1){
			return(courseDeptNumber + " with " + professor + " is from " + startTime + " to " + endTime + " and meets on " + meetingDay1 + meetingDay2 + ".\n");
		}else{
			return(courseDeptNumber + " with " + professor + " is from " + startTime + " to " + endTime + " and meets on " + meetingDay1 + meetingDay2 + meetingDay3 + ".\n");
		}
	}
	
	public String toString(){
		return courseDeptNumber + " " + professor + " " + startTime + " " + endTime + " " + meetingDay1 + meetingDay2 + meetingDay3;
	}
	
	public LocalTime getStartTime(){
		return startTime;
	}
	
	public LocalTime getEndTime(){
		return endTime;
	}
	
	public void setStartTime(LocalTime sST){
		startTime = sST;
	}
	
	public void setEndTime(LocalTime sET){
		endTime = sET;
	}
	
	public char getDayOfWeek1(){
		return meetingDay1;
	}
	
	public char getDayOfWeek2(){
		return meetingDay2;
	}
	
	public char getDayOfWeek3(){
		return meetingDay3;
	}

}
