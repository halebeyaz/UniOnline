
public class Post {

	private int post_id;
	private String course_code;
	private String teacher_id;
	private String text;

	
	
	public Post(int post_id, String course_code, String teacher_id, String text) {
		this.post_id = post_id;
		this.course_code = course_code;
		this.teacher_id = teacher_id;
		this.text = text;
	}

	public int getPost_id() {
		return post_id;
	}
	
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	
	public String getCourse_code() {
		return course_code;
	}
	
	public void setCourse_code(String course_code) {
		this.course_code = course_code;
	}
	
	public String getTeacher_id() {
		return teacher_id;
	}
	
	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}
	
	

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return  post_id + "|\t" + course_code + "|\t" + teacher_id +"|\t"+text;
	}
	
	
	
}
