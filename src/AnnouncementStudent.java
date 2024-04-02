
public class AnnouncementStudent {
	private int announce_id;
	private String student_id;
	public AnnouncementStudent(int announce_id, String student_id) {
		this.announce_id = announce_id;
		this.student_id = student_id;
	}
	
	public int getAnnounce_id() {
		return announce_id;
	}

	public void setAnnounce_id(int announce_id) {
		this.announce_id = announce_id;
	}


	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}

	@Override
	public String toString() {
		return  announce_id + "\t|" + student_id + "\t|";
	}
	
	
	
}
