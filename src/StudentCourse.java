
public class StudentCourse {
	
	private String student;
	private String course;
	private String status;
	public StudentCourse(String student, String course, String status) throws Exception {
		this.student = student;
		this.course = course;
		checkStatus(status);
	}
	private void checkStatus(String status) throws Exception {
		if(status.equalsIgnoreCase("elective") || status.equalsIgnoreCase("required"))
			this.status=status;
		else
			throw new Exception("Wrong entry in course");
	}
	public String getStudent() {
		return student;
	}
	public void setStudent(String student) {
		this.student = student;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return student + "\t|" + course + "\t|" + status + "\t|";
	}
	
	

}
