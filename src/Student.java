
public class Student extends Person {

	private String student_number;
	
	
	public Student( String student_number,String name, String surname,String mail, String phone,String personal_id ) {
		super(name, surname, personal_id, mail, phone);
		this.student_number = student_number;
	}

	public String getStudent_number() {
		return student_number;
	}

	public void setStudent_number(String student_number) {
		this.student_number = student_number;
	}

	@Override
	public String toString() {
		return student_number + "\t|" + super.toString();
	}

	
	
}
