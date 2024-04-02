
public class Teacher extends Person{
	
	private String teacher_id;

	public Teacher(String teacher_id,String name, String surname, String mail, String phone ,String personal_id) {
		super(name, surname, personal_id, mail, phone);
		this.teacher_id = teacher_id;
	}

	public String getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}
	
	@Override
	public String toString() {
		return teacher_id + "\t|" + super.toString();
	}

	
}
