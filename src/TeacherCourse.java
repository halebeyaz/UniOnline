
public class TeacherCourse {
	private String teacher;
	private String course;
	public TeacherCourse(String teacher, String course) {
		this.teacher = teacher;
		this.course = course;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	
	@Override
	public String toString() {
		return teacher + "\t|" + course + "\t|";
	}
	
	
	
}
