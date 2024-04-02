public class Course {

	private String code;
	private String name;
	private int capacity;

	
	public Course(String code, String name, int capacity ) {

		this.code = code;
		this.name = name;
		this.capacity = capacity;
		
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getCapacity() {
		return capacity;
	}


	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}


	@Override
	public String toString() {
		return code + "\t|" + name + "\t|" + capacity ;
	}


	
	
	
}
