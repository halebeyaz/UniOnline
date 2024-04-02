
public class AssignmentStudent {
	private int assign_id;
	private String student_id;
	private String status;
	private String text;

	public AssignmentStudent(int assign_id, String student_id, String status, String text) throws Exception {
		this.assign_id = assign_id;
		this.student_id = student_id;
		this.text=text;
		checkStatus(status);
	}
	private void checkStatus(String status) throws Exception {
		if(status.equalsIgnoreCase("completed")||status.equalsIgnoreCase("uncompleted"))
			this.status=status;
		else
			throw new Exception("Wrong entry in assignment");
	}
	public int getAssign_id() {
		return assign_id;
	}

	public void setAssign_id(int assign_id) {
		this.assign_id = assign_id;
	}

	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	

	@Override
	public String toString() {
		return assign_id + "\t|" + student_id + "\t|" + status + "\t|" + text + "\t|";
	}
	
	
	
}
