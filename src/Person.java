
public class Person {
	private String name;
	private String surname;
	private String personal_id;
	private String mail;
	private String Phone;
	
	public Person(String name, String surname, String personal_id, String mail, String phone) {
		this.name = name;
		this.surname = surname;
		this.personal_id = personal_id;
		this.mail = mail;
		Phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getPersonal_id() {
		return personal_id;
	}
	public void setPersonal_id(String personal_id) {
		this.personal_id = personal_id;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	@Override
	public String toString() {
		return  name + "\t|" + surname + "\t|" + personal_id + "\t|" + mail
				+ "\t|" + Phone + "\t|";
	}
	
	
	
}
