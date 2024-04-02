import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Assignment {

	
	int id;
	String name, info, courseID, taID;
	
	public Assignment(int id, String name, String info, String courseID, String taID) {
		this.id = id;
		this.name = name;
		this.info = info;
		this.courseID = courseID;
		this.taID = taID;
		
	}
	public int getID(){
		 return id;
	}

	public String getName(){
		 return name;
	}
	public String getInfo(){
		 return info;
	}
	public String getCourseID(){
		 return courseID;
	}
	public String getTaID(){
		 return taID;
	}
	
	public void setID(int newid){
		  id = newid;
	}

	public void setName(String newname){
		  name = newname;
	}
	public void setInfo(String newinfo){
		  info = newinfo;
	}
	public void setCourseID(String newcid){
		  courseID = newcid;
	}
	public void setTaID(String newtid){
		  taID = newtid;
	}
	
	public String toString(){ 
		return (this.getID()+"\t|"+this.getName()+"\t|"+this.getInfo()+"\t|\t"+this.getCourseID()+"\t|\t"+this.getTaID()+"\t|"); 
    } 

}
