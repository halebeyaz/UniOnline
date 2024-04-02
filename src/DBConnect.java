
import com.mysql.jdbc.*;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnect {
	private static final String String = null;
	public Connection con;
	public Statement create_st;
	public ResultSet rs;
	public PreparedStatement preparedStmt;
	public UniOnline uni;

	public DBConnect() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/unionline?serverTimezone=UTC", "root",
					"toor");
			create_st = con.createStatement();
			uni = new UniOnline();
			 
			/////////////////////////////////Reading database rows///////////////////////////////////////// 
			/// Announcement
			String query = "select * from announcement";
			rs = create_st.executeQuery(query);
			while (rs.next()) {
				uni.insertAnnouncement(new Announcement(rs.getInt("id"), rs.getString("name"), rs.getString("info"),
						rs.getString("course_id"), rs.getString("teacher_id")));
			}
			/// Announcement Student
			query = "select * from announcement_student";
			rs = create_st.executeQuery(query);
			while (rs.next()) {
				uni.insertAnnouncementStudent(
						new AnnouncementStudent(rs.getInt("ann_id"), rs.getString("student_id")));
			}
			/// Assignment
			query = "select * from assignment";
			rs = create_st.executeQuery(query);
			while (rs.next()) {
				uni.insertAssignments(new Assignment(rs.getInt("id"), rs.getString("name"), rs.getString("info"),
						rs.getString("course_id"), rs.getString("teacher_id")));
			}
			/// Assignment Student
			query = "select * from assignment_student";
			rs = create_st.executeQuery(query);
			while (rs.next()) {
				uni.insertAssignmentStudent(new AssignmentStudent(rs.getInt("id"), rs.getString("student_id"),
						rs.getString("assn_status"), rs.getString("assn_text")));
			}
			/// Course
			query = "select * from course";
			rs = create_st.executeQuery(query);
			while (rs.next()) {
				uni.insertCourses(
						new Course(rs.getString("course_code"), rs.getString("name"), rs.getInt("capacity")));
			}
			/// Message
			query = "select * from messages";
			rs = create_st.executeQuery(query);
			while (rs.next()) {
				uni.insertMessages(new Messages(rs.getInt("id"), rs.getString("senderID"), rs.getString("receiverID"),
						rs.getString("msgtext")));
			}
			/// Student
			query = "select * from student";
			rs = create_st.executeQuery(query);
			while (rs.next()) {
				uni.insertStudent(new Student(rs.getString("student_number"), rs.getString("first_name"),
						rs.getString("last_name"), rs.getString("email"), rs.getString("phone"),
						rs.getString("personal_id")));
			}
			/// Student Course
			query = "select * from student_course";
			rs = create_st.executeQuery(query);
			while (rs.next()) {
				uni.insertStudentCourse(new StudentCourse(rs.getString("student_id"), rs.getString("course_id"),
						rs.getString("status")));
			}
			/// Teacher
			query = "select * from teacher";
			rs = create_st.executeQuery(query);
			while (rs.next()) {
				uni.insertTeacher(new Teacher(rs.getString("teacher_id"), rs.getString("first_name"),
						rs.getString("last_name"), rs.getString("email"), rs.getString("phone"),
						rs.getString("personal_id")));
			}
			/// Teacher Course
			query = "select * from teacher_course";
			rs = create_st.executeQuery(query);
			while (rs.next()) {
				uni.insertTeacherCourse(new TeacherCourse(rs.getString("teacher_id"), rs.getString("course_id")));
			}
			
			/// Post
			query = "select * from post";
			rs = create_st.executeQuery(query);
			while (rs.next()) {
				uni.insertPost(new Post(rs.getInt("post_id"), rs.getString("course_code"),rs.getString("teacher_id"),rs.getString("text")));
			}
			
			/// Comment
			query = "select * from comment";
			rs = create_st.executeQuery(query);
			while (rs.next()) {
				uni.insertComment(new Comment(rs.getInt("comment_id"), rs.getInt("post_id"),rs.getString("user_id"),rs.getString("text")));
			}
			
			rs.close();
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {

						Login window = new Login(con);
						window.frmLogIn.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
	}
	/* Insert to database*/
	public void insert(String table_name, Object obj) {
		String query = "";
		switch (table_name) {
		case "announcement":
			Announcement an = (Announcement) obj;
			query = "insert into announcement (id,name,info,course_id,teacher_id) values(";
			query += an.getID() + ",";
			query += "\"" + an.getName() + "\",";
			query += "\"" + an.getInfo() + "\",";
			query += "\"" + an.getCourseID() + "\",";
			query += "\"" + an.getTaID() + "\");";
			System.out.println(query);
			break;
		case "announcement_student":
			AnnouncementStudent ann_s = (AnnouncementStudent) obj;
			query = "insert into announcement_student (ann_id,student_id) values(";
			query += ann_s.getAnnounce_id() + ",";
			query += "\"" + ann_s.getStudent_id() + "\");";
			System.out.println(query);
			break;
		case "assignment":
			Assignment assignment = (Assignment) obj;
			query = "insert into assignment (id,name,info,course_id,teacher_id) values(";
			query += assignment.getID() + ",";
			query += "\"" + assignment.getName() + "\",";
			query += "\"" + assignment.getInfo() + "\",";
			query += "\"" + assignment.getCourseID() + "\",";
			query += "\"" + assignment.getTaID() + "\");";
			System.out.println(query);
			break;
		case "assignment_student":
			AssignmentStudent assign_s = (AssignmentStudent) obj;
			query = "insert into assignment_student (id,student_id,assn_status,assn_text) values(";
			query += assign_s.getAssign_id() + ",";
			query += "\"" + assign_s.getStudent_id() + "\",";
			query += "\"" + assign_s.getStatus() + "\",";
			query += "\"" + assign_s.getText() + "\");";
			System.out.println(query);
			break;
		case "course":
			Course course = (Course) obj;
			query = "insert into course (course_code,name,capacity) values(";
			query += "\"" + course.getCode() + "\",";
			query += "\"" + course.getName() + "\",";
			query += course.getCapacity();
			query += ");";
			System.out.println(query);
			break;
		case "messages":
			Messages message = (Messages) obj;
			query = "insert into messages (id,senderID,receiverID,msgtext) values(";
			query += message.getID() + ",";
			query += "\"" + message.getSenderID() + "\",";
			query += "\"" + message.getReceiverID() + "\",";
			query += "\"" + message.getText() + "\");";
			System.out.println(query);
			break;
		case "student":
			Student stdnt = (Student) obj;
			query = "insert into student (student_number,first_name,last_name,email,phone,personal_id) values(";
			query += "\"" + stdnt.getStudent_number() + "\",";
			query += "\"" + stdnt.getName() + "\",";
			query += "\"" + stdnt.getSurname() + "\",";
			query += "\"" + stdnt.getMail() + "\",";
			query += "\"" + stdnt.getPhone() + "\",";
			query += "\"" + stdnt.getPersonal_id() + "\");";
			System.out.println(query);
			break;
		case "teacher":
			Teacher teacher = (Teacher) obj;
			query = "insert into teacher (teacher_id,first_name,last_name,email,phone,personal_id) values(";
			query += "\"" + teacher.getTeacher_id() + "\",";
			query += "\"" + teacher.getName() + "\",";
			query += "\"" + teacher.getSurname() + "\",";
			query += "\"" + teacher.getMail() + "\",";
			query += "\"" + teacher.getPhone() + "\",";
			query += "\"" + teacher.getPersonal_id() + "\");";
			System.out.println(query);
			break;
		case "student_course":
			StudentCourse s_course = (StudentCourse) obj;
			query = "insert into student_course (student_id,course_id,status) values(";
			query += "\"" + s_course.getStudent() + "\",";
			query += "\"" + s_course.getCourse() + "\",";
			query += "\"" + s_course.getStatus() + "\");";
			System.out.println(query);
			break;
		case "teacher_course":
			TeacherCourse t_course = (TeacherCourse) obj;
			query = "insert into teacher_course (teacher_id,course_id) values(";
			query += "\"" + t_course.getTeacher() + "\",";
			query += "\"" + t_course.getCourse() + "\");";
			System.out.println(query);
			break;
		case "post":
			Post p = (Post) obj;
			query = "insert into post (post_id,course_code,teacher_id,text) values(";
			query += "\"" + p.getPost_id() + "\",";
			query += "\"" + p.getCourse_code() + "\",";
			query += "\"" + p.getTeacher_id()+ "\",";
			query += "\"" + p.getText()+ "\");";
			System.out.println(query);
			break;
		case "comment":
			Comment c = (Comment) obj;
			query = "insert into comment (comment_id,post_id,user_id,text) values(";
			query += "\"" + c.getComment_id() + "\",";
			query += "\"" + c.getPost_id() + "\",";
			query += "\"" + c.getUser_id() + "\",";
			query += "\"" +c.getText()+ "\");";
			System.out.println(query);
			break;
		}
		try {
			create_st.executeUpdate(query);
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
	}
	/*Delete from database*/
	public void delete(String table_name, Object obj_id) {
		String query = "";
		switch (table_name) {
		case "announcement": {
			Announcement an = (Announcement) obj_id;
			uni.getAnnouncements().remove(an);
			int id = (int) an.getID();
			try {
				delete("announcement_student", id);
				query = "delete from announcement where id = ?";
				preparedStmt = con.prepareStatement(query);
				preparedStmt.setInt(1, id);
				preparedStmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println(query);
			break;
		}
		case "announcement_course": {

			String id = (String)obj_id;
			ArrayList<Announcement> a=uni.getAnnouncements();
			for(int i=0;i<a.size();i++)
			{
				if(a.get(i).getCourseID().equals(id))
				{
					a.remove(a.get(i));
				}
			}
			try {
				query = "SELECT * FROM announcement WHERE course_id=\"" + id + "\"";
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					int id_ann = rs.getInt("id");
					delete("announcement_student", id_ann);
				}
				st.close();
				query = "delete from announcement where course_id = ?";
				preparedStmt = con.prepareStatement(query);
				preparedStmt.setString(1, id);
				preparedStmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println(query);
			break;
		}
		case "announcement_student": {
			
			//AnnouncementStudent as = (AnnouncementStudent) obj_id; 
			int id =Integer.parseInt(obj_id.toString());
			ArrayList<AnnouncementStudent> a=uni.getAnnouncement_student();
			for(int i=0;i<a.size();i++)
			{
				if(a.get(i).getAnnounce_id() == id)
				{
					a.remove(a.get(i));
				}
			}
			try {
				query = "delete from announcement_student where ann_id = ?";
				preparedStmt = con.prepareStatement(query);
				preparedStmt.setInt(1, id);
				preparedStmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println(query);
			break;
		}
		case "assignment": {
			Assignment assn =(Assignment) obj_id; 
			uni.getAssignments().remove(assn);
			int id = assn.getID();
			try {
				delete("assignment_student", id);
				query = "delete from assignment where id = ?";
				preparedStmt = con.prepareStatement(query);
				preparedStmt.setInt(1, id);
				preparedStmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println(query);
			break;
		}
		case "assignment_course": {

			String id = (String)obj_id;
			ArrayList<Assignment> a=uni.getAssignments();
			for(int i=0;i<a.size();i++)
			{
				if(a.get(i).getCourseID().equals(id))
				{
					a.remove(a.get(i));
				}
			}
			
			try {
				query = "SELECT * FROM assignment WHERE course_id=\"" + id + "\"";
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					int id_ann = rs.getInt("id");
					delete("assignment_student", id_ann);
				}
				st.close();
				query = "delete from assignment where course_id = ?";
				preparedStmt = con.prepareStatement(query);
				preparedStmt.setString(1, id);
				preparedStmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println(query);
			break;
		}
		case "assignment_student": {
			int id =Integer.parseInt(obj_id.toString());
			ArrayList<AssignmentStudent> a=uni.getAssignments_student();
			for(int i=0;i<a.size();i++)
			{
				if(a.get(i).getAssign_id() == id)
				{
					a.remove(a.get(i));
				}
			}
			try {
				query = "delete from assignment_student where id = ?";
				preparedStmt = con.prepareStatement(query);
				preparedStmt.setInt(1, id);
				preparedStmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println(query);
			break;
		}
		case "course": {
			Course c = (Course) obj_id;
			uni.getCourses().remove(c);
			String id = c.getCode();
			try {
				delete("announcement_course", id);
				delete("assignment_course", id);
				delete("student_course_c", id);
				delete("teacher_course_c", id);
				query = "delete from course where course_code = ?";
				preparedStmt = con.prepareStatement(query);
				preparedStmt.setString(1, id);
				preparedStmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println(query);
			break;
		}
		case "messages": {
			Messages m = (Messages) obj_id;
			uni.getMessages().remove(m);
			int id = m.getID();
			try {
				query = "delete from messages where id = ?";
				preparedStmt = con.prepareStatement(query);
				preparedStmt.setInt(1, id);
				preparedStmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println(query);
			break;
		}
		case "messages_people": {
			String id = (String) obj_id;
			ArrayList<Messages> m=uni.getMessages();
			for(int i=0;i<m.size();i++)
			{
				if(m.get(i).getSenderID().equals(id) || m.get(i).getReceiverID().equals(id))
				{
					m.remove(m.get(i));
				}
			}
			try {
				query = "delete from messages where senderID = ?";
				preparedStmt = con.prepareStatement(query);
				preparedStmt.setString(1, id);
				preparedStmt.execute();
				query = "delete from messages where receiverID = ?";
				preparedStmt = con.prepareStatement(query);
				preparedStmt.setString(1, id);
				preparedStmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println(query);
			break;
		}
		case "student": {
			Student s = (Student) obj_id;
			uni.getPersons().remove(s.getStudent_number());
			uni.getStudents().remove(s);
			String id = s.getStudent_number();
			try {
				query = "SELECT * FROM announcement_student WHERE student_id=\"" + id + "\"";
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					int id_ann = rs.getInt("ann_id");
					delete("announcement_student", id_ann);
				}
				query = "SELECT * FROM assignment_student WHERE student_id=\"" + id + "\"";
				st = con.createStatement();
				rs = st.executeQuery(query);
				while (rs.next()) {
					int id_ann = rs.getInt("id");
					delete("assignment_student", id_ann);
				}
				st.close();
				delete("student_course_s", id);
				delete("messages_people", id);
				query = "delete from student where student_number = ?";
				preparedStmt = con.prepareStatement(query);
				preparedStmt.setString(1, id);
				preparedStmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println(query);
			break;
		}
		case "student_course_s": {
			String id = (String)obj_id;
			ArrayList<StudentCourse> stu=uni.getStudent_course();
			for(int i=0;i<stu.size();i++)
			{
				if(stu.get(i).getStudent().equals(id))
				{
					stu.remove(stu.get(i));
				}
			}
			try {
				query = "delete from student_course where student_id = ?";
				preparedStmt = con.prepareStatement(query);
				preparedStmt.setString(1, id);
				preparedStmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println(query);
			break;
		}
		case "student_course_c": {
			String id = (String) obj_id;
			ArrayList<StudentCourse> stu=uni.getStudent_course();
			for(int i=0;i<stu.size();i++)
			{
				if(stu.get(i).getCourse().equals(id))
				{
					stu.remove(stu.get(i));
				}
			}
			try {
				query = "delete from student_course where course_id = ?";
				preparedStmt = con.prepareStatement(query);
				preparedStmt.setString(1, id);
				preparedStmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println(query);
			break;
		}
		case "teacher": {
			Teacher t = (Teacher) obj_id;
			uni.getPersons().remove(t.getTeacher_id());
			uni.getTeacher().remove(t);
			String id = t.getTeacher_id();
			try {
				query = "SELECT * FROM announcement WHERE teacher_id=\"" + id + "\"";
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					
					delete("announcement", new Announcement(rs.getInt("id"), rs.getString("name"), rs.getString("info"),
							rs.getString("course_id"), rs.getString("teacher_id")));
				}
				query = "SELECT * FROM assignment WHERE teacher_id=\"" + id + "\"";
				st = con.createStatement();
				rs = st.executeQuery(query);
				while (rs.next()) {
					delete("assignment", new Assignment(rs.getInt("id"), rs.getString("name"), rs.getString("info"),
							rs.getString("course_id"), rs.getString("teacher_id")));
				}
				st.close();
				delete("teacher_course_t", id);
				delete("messages_people", id);
				query = "delete from teacher where teacher_id = ?";
				preparedStmt = con.prepareStatement(query);
				preparedStmt.setString(1, id);
				preparedStmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println(query);
			break;
		}
		case "teacher_course_t": {
			String id = (String)obj_id;	
			ArrayList<TeacherCourse> t=uni.getTeacher_course();
			for(int i=0;i<t.size();i++)
			{
				if(t.get(i).getTeacher().equals(id))
				{
					t.remove(t.get(i));
				}
			}
			try {
				query = "delete from teacher_course where teacher_id = ?";
				preparedStmt = con.prepareStatement(query);
				preparedStmt.setString(1, id);
				preparedStmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println(query);
			break;
		}
		case "teacher_course_c": {
			String id = (String)obj_id;
			ArrayList<TeacherCourse> t=uni.getTeacher_course();
			for(int i=0;i<t.size();i++)
			{
				if(t.get(i).getCourse().equals(id))
				{
					t.remove(t.get(i));
				}
			}
			try {
				query = "delete from teacher_course where course_id = ?";
				preparedStmt = con.prepareStatement(query);
				preparedStmt.setString(1, id);
				preparedStmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println(query);
			break;
		}
		case "post": {
			Post p = (Post)obj_id;
			uni.getPost().remove(p);
			try {
				query = "SELECT * FROM comment WHERE post_id=\"" + p.getPost_id() + "\"";
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					delete("comment", new Comment(rs.getInt("comment_id"), rs.getInt("post_id"),rs.getString("user_id"),rs.getString("text")));
				}
				
				query = "delete from post where post_id = ?";
				preparedStmt = con.prepareStatement(query);
				preparedStmt.setInt(1, p.getPost_id());
				preparedStmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println(query);
			break;
		}
		case "comment": {
			Comment c = (Comment) obj_id;
			int id = c.getComment_id();
			uni.getComments().remove(c);
			try {
				query = "delete from comment where comment_id = ?";
				preparedStmt = con.prepareStatement(query);
				preparedStmt.setInt(1, id);
				preparedStmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println(query);
			break;
		}
		}
	}
	/*Update database*/
	public void update(String table_name, Object obj) {
		String query = "";
		switch (table_name) {
		case "announcement": {
			Announcement an = (Announcement) obj;
			query = "update announcement set name = ?  , info = ? where id = ?";
			try {
				preparedStmt = con.prepareStatement(query);
				preparedStmt.setString(1, an.getName());
				preparedStmt.setString(2, an.getInfo());
				preparedStmt.setInt(3, an.getID());
				preparedStmt.executeUpdate();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			System.out.println(query);
			break;
		}
		case "assignment": {
			Assignment assn = (Assignment) obj;
			query = "update assignment set name = ?  , info = ? where id = ?";
			try {
				preparedStmt = con.prepareStatement(query);
				preparedStmt.setString(1, assn.getName());
				preparedStmt.setString(2, assn.getInfo());
				preparedStmt.setInt(3, assn.getID());
				preparedStmt.executeUpdate();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			System.out.println(query);
			break;
		}
		case "assignment_student": {
			AssignmentStudent assn_sut = (AssignmentStudent) obj;
			query = "update assignment_student set assn_status = ?  , assn_text = ? where id = ? and student_id = ?";
			try {
				preparedStmt = con.prepareStatement(query);
				preparedStmt.setString(1, assn_sut.getStatus());
				preparedStmt.setString(2, assn_sut.getText());
				preparedStmt.setInt(3, assn_sut.getAssign_id());
				preparedStmt.setString(4, assn_sut.getStudent_id());
				preparedStmt.executeUpdate();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			System.out.println(query);
			break;
		}
		case "course": {
			Course course = (Course) obj;
			query = "update course set name = ?  , capacity = ? where course_code = ?";
			try {
				preparedStmt = con.prepareStatement(query);
				preparedStmt.setString(1, course.getName());
				preparedStmt.setInt(2, course.getCapacity());
				preparedStmt.setString(3, course.getCode());
				preparedStmt.executeUpdate();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			System.out.println(query);
			break;
		}
		case "student": {
			Student s = (Student) obj;
			query = "update student set first_name = ?  , last_name = ? , email = ?, phone = ? where student_number = ?";
			try {
				preparedStmt = con.prepareStatement(query);
				preparedStmt.setString(1, s.getName());
				preparedStmt.setString(2, s.getSurname());
				preparedStmt.setString(3, s.getMail());
				preparedStmt.setString(4, s.getPhone());
				preparedStmt.setString(5, s.getStudent_number());
				preparedStmt.executeUpdate();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			System.out.println(query);
			break;
		}
		case "teacher": {
			Teacher t = (Teacher) obj;
			query = "update teacher set first_name = ?  , last_name = ? , email = ?, phone = ? where teacher_id = ?";
			try {
				preparedStmt = con.prepareStatement(query);
				preparedStmt.setString(1, t.getName());
				preparedStmt.setString(2, t.getSurname());
				preparedStmt.setString(3, t.getMail());
				preparedStmt.setString(4, t.getPhone());
				preparedStmt.setString(5, t.getTeacher_id());
				preparedStmt.executeUpdate();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			System.out.println(query);
			break;
		}
		case "post": {
			Post p = (Post) obj;
			query = "update post set  text = ? where comment_id = ?";
			try {
				preparedStmt = con.prepareStatement(query);
				preparedStmt.setString(1, p.getText());
				preparedStmt.setInt(2, p.getPost_id());
				preparedStmt.executeUpdate();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			System.out.println(query);
			break;
		}
		}
	}

}
