import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
 	All data keep in this class.
 */
public class UniOnline {
	private ArrayList announcements;
	private ArrayList announcement_student;
	public ArrayList assignments;
	private ArrayList submittedassignments;
	private ArrayList assignments_student;
	private ArrayList courses;
	private ArrayList messages;
	private ArrayList students;
	private ArrayList student_course;
	private ArrayList teacher;
	private ArrayList teacher_course;
	private ArrayList post;
	private ArrayList comments;
	private Map<String,String> persons;
	public UniOnline() {
		this.announcements = new ArrayList<Announcement>();
		this.announcement_student = new ArrayList<AnnouncementStudent>();
		this.assignments = new ArrayList<Assignment>();
		this.assignments_student = new ArrayList<AssignmentStudent>();
		this.courses = new ArrayList<Course>();
		this.messages = new ArrayList<Messages>();
		this.students = new ArrayList<Student>();
		this.student_course = new ArrayList<StudentCourse>();
		this.teacher = new ArrayList<Teacher>();
		this.teacher_course = new ArrayList<TeacherCourse>();
		this.submittedassignments = new ArrayList<String>();
		this.post = new ArrayList<Post>();
		this.comments = new ArrayList<Comment>();
		this.persons= new HashMap<String,String>();
		
	}

	//// insert from database
	public void insertAnnouncement(Announcement a) {
		System.out.println(a.toString());
		announcements.add(a);
	}

	public void insertAnnouncementStudent(AnnouncementStudent a) {
		System.out.println(a.toString());
		this.announcement_student.add(a);
	}

	public void insertAssignments(Assignment a) {
		System.out.println(a.toString());
		assignments.add(a);
	}

	public void insertAssignmentStudent(AssignmentStudent a) {
		System.out.println(a.toString());
		this.assignments_student.add(a);
	}

	public void insertCourses(Course c) {
		System.out.println(c);
		this.courses.add(c);
	}

	public void insertMessages(Messages m) {
		System.out.println(m);
		this.messages.add(m);
	}

	public void insertStudent(Student s) {
		System.out.println(s);
		persons.put(s.getStudent_number(), s.getName()+" "+s.getSurname());
		this.students.add(s);
	}

	public void insertStudentCourse(StudentCourse s) {
		System.out.println(s);
		this.student_course.add(s);
	}

	public void insertTeacher(Teacher t) {
		System.out.println(t);
		persons.put(t.getTeacher_id() ,t.getName()+" "+t.getSurname());
		this.teacher.add(t);
	}

	public void insertTeacherCourse(TeacherCourse t) {
		System.out.println(t);
		this.teacher_course.add(t);
	}
	
	public void insertPost(Post p) {
		System.out.println(p);
		this.post.add(p);
	}
	public void insertComment(Comment c) {
		System.out.println(c);
		this.comments.add(c);
	}

	/// adding Java and databases
	public void add(String table_name, Object obj) {

		switch (table_name) {
		case "announcement":
			this.announcements.add((Announcement) obj);
			break;
		case "announcement_student":
			this.announcement_student.add((AnnouncementStudent) obj);
			break;
		case "assignment":
			this.assignments.add((Assignment) obj);
			break;
		case "assignment_student":
			try {
				this.assignments_student.add((AssignmentStudent) obj);
			} catch (Exception ex) {
				System.out.println(ex);
				return;
			}
			break;
		case "course":
			this.courses.add((Course) obj);
			break;
		case "messages":
			this.messages.add((Messages) obj);
			break;
		case "student":
			persons.put(((Student) obj).getStudent_number(), ((Student) obj).getName()+" "+((Student) obj).getSurname());
			this.students.add((Student) obj);
			break;
		case "teacher":
			persons.put(((Teacher) obj).getTeacher_id(), ((Teacher) obj).getName()+" "+((Teacher) obj).getSurname());

			this.teacher.add((Teacher) obj);
			break;
		case "student_course":

			try {
				this.student_course.add((StudentCourse) obj);
			} catch (Exception ex) {
				System.out.println(ex);
				return;
			}
			break;
		case "teacher_course":
			this.teacher_course.add((TeacherCourse) obj);
			break;
		case "post":
			this.post.add((Post) obj);
			break;
		case "comment":
			this.comments.add((Comment) obj);
			break;
		}

		Main.db.insert(table_name, obj);
	}
	
	

	public Map<String, String> getPersons() {
		return persons;
	}

	public void setPersons(Map<String, String> code_name) {
		this.persons = code_name;
	}

	public ArrayList getAnnouncements() {
		return announcements;
	}

	public void setAnnouncements(ArrayList announcements) {
		this.announcements = announcements;
	}

	public ArrayList getAnnouncement_student() {
		return announcement_student;
	}

	public void setAnnouncement_student(ArrayList announcement_student) {
		this.announcement_student = announcement_student;
	}

	public ArrayList getAssignments() {
		return assignments;
	}

	public void setAssignments(ArrayList assignments) {
		this.assignments = assignments;
	}

	public ArrayList getSubmittedassignments() {
		return submittedassignments;
	}

	public void setSubmittedassignments(ArrayList submittedassignments) {
		this.submittedassignments = submittedassignments;
	}

	public ArrayList getAssignments_student() {
		return assignments_student;
	}

	public void setAssignments_student(ArrayList assignments_student) {
		this.assignments_student = assignments_student;
	}

	public ArrayList getCourses() {
		return courses;
	}

	public void setCourses(ArrayList courses) {
		this.courses = courses;
	}

	public ArrayList getMessages() {
		return messages;
	}

	public void setMessages(ArrayList messages) {
		this.messages = messages;
	}

	public ArrayList getStudents() {
		return students;
	}

	public void setStudents(ArrayList students) {
		this.students = students;
	}

	public ArrayList getStudent_course() {
		return student_course;
	}

	public void setStudent_course(ArrayList student_course) {
		this.student_course = student_course;
	}

	public ArrayList getTeacher() {
		return teacher;
	}

	public void setTeacher(ArrayList teacher) {
		this.teacher = teacher;
	}

	public ArrayList getTeacher_course() {
		return teacher_course;
	}

	public void setTeacher_course(ArrayList teacher_course) {
		this.teacher_course = teacher_course;
	}

	public ArrayList getPost() {
		return post;
	}

	public void setPost(ArrayList post) {
		this.post = post;
	}

	public ArrayList getComments() {
		return comments;
	}

	public void setComments(ArrayList comments) {
		this.comments = comments;
	}
	
	

}