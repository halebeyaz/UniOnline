import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
/*Login page*/
public class Login {

	// private static Connection con;
	private static Statement st;
	private static ResultSet rs;
	private Teacher teacher;
	private Student student;
	boolean foundT = false;
	boolean foundS = false;

	private JTextField txtUserName;
	private JPasswordField passwordField;
	private JButton btnLogIn;

	JFrame frmLogIn;

	static final String NUMBERS = "0123456789";
	public Connection con2; 
	
	public Login(Connection con) {

		con2 = con;
		try {
			st = con2.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		frmLogIn = new JFrame();
		frmLogIn.setTitle("Log in");
		frmLogIn.setBounds(100, 100, 316, 196);
		frmLogIn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogIn.getContentPane().setLayout(null);

		JLabel lblUserName = new JLabel("User Name: ");
		lblUserName.setBounds(55, 31, 78, 14);
		frmLogIn.getContentPane().add(lblUserName);

		txtUserName = new JTextField();
		txtUserName.setBounds(143, 28, 86, 20);
		txtUserName.setColumns(10);
		frmLogIn.getContentPane().add(txtUserName);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(55, 65, 78, 14);
		frmLogIn.getContentPane().add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(143, 59, 86, 20);
		frmLogIn.getContentPane().add(passwordField);

		btnLogIn = new JButton("Log in");
		btnLogIn.addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {

				boolean isPersonalID = false;
				for (int i = 0; i < NUMBERS.length(); i++) {
					if (txtUserName.getText().charAt(0) == NUMBERS.charAt(i)) {
						isPersonalID = true;
						System.out.println("personal id " + NUMBERS.charAt(i) + " " + i);

					}
				}

				if (isPersonalID) { //log in with personal ID
					int pID = Integer.parseInt(txtUserName.getText());

					login(pID);

				}

				else { //login with name
					String name = txtUserName.getText();
					login(name);
				}

			}
		});
		btnLogIn.setBounds(140, 105, 89, 23);
		frmLogIn.getContentPane().add(btnLogIn);

	}

	public void login(int pID) { // logging in with a personal id
		if (passwordField.getText().charAt(0) == 'T') {
			String query = "select * from teacher";
			try {
				rs = st.executeQuery(query);
				while (rs.next()) {
					String id = rs.getString("teacher_id");
					String firstname = rs.getString("first_name");
					String lastname = rs.getString("last_name");
					String email = rs.getString("email");
					String phone = rs.getString("phone");
					String personalID = rs.getString("personal_id");
					teacher = new Teacher(id, firstname, lastname, email, phone, personalID);

					if (txtUserName.getText().equals(teacher.getPersonal_id())
							&& passwordField.getText().equalsIgnoreCase(teacher.getTeacher_id())) {
						System.out.println("fdjvhvffdhvbdfhv");

						foundT = true;
						break;

					}

				}
			} catch (SQLException e) {

				e.printStackTrace();
			}
		} else {
			String query = "select * from student";
			try {
				rs = st.executeQuery(query);
				while (rs.next()) {
					String id = rs.getString("student_number");
					String firstname = rs.getString("first_name");
					String lastname = rs.getString("last_name");
					String email = rs.getString("email");
					String phone = rs.getString("phone");
					String personalID = rs.getString("personal_id");
					student = new Student(id, firstname, lastname, email, phone, personalID);

					if (txtUserName.getText().equals(student.getPersonal_id())
							&& passwordField.getText().equalsIgnoreCase(student.getStudent_number())) {
						System.out.println("fdjvhvffdhvbdfhv");

						foundS = true;
						break;

					}

				}
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}

		if (foundT) {
			MenuPage mp = new MenuPage(con2, teacher);
			mp.getFrame().setVisible(true);
			frmLogIn.setVisible(false);

		} else if (foundS) {
			MenuPage2 mp2 = new MenuPage2(con2, student);
			mp2.getFrame().setVisible(true);
			frmLogIn.setVisible(false);

		} else {
			txtUserName.setText("");
			passwordField.setText("");

			JOptionPane.showMessageDialog(frmLogIn, "Wrong password or user name");

		}
	

	}

	@SuppressWarnings("deprecation")
	public void login(String name) { // logging in with a name
		if (passwordField.getText().charAt(0) == 'T') {
			String query = "select * from teacher";
			try {
				rs = st.executeQuery(query);
				while (rs.next()) {
					String id = rs.getString("teacher_id");
					String firstname = rs.getString("first_name");
					String lastname = rs.getString("last_name");
					String email = rs.getString("email");
					String phone = rs.getString("phone");
					String personalID = rs.getString("personal_id");
					teacher = new Teacher(id, firstname, lastname, email, phone, personalID);

					if (txtUserName.getText().equals(teacher.getName())
							&& passwordField.getText().equalsIgnoreCase(teacher.getTeacher_id())) {
						System.out.println("fdjvhvffdhvbdfhv");

						foundT = true;
						break;

					}

				}
			} catch (SQLException e) {

				e.printStackTrace();
			}
		} else {
			String query = "select * from student";
			try {
				rs = st.executeQuery(query);
				while (rs.next()) {
					String id = rs.getString("student_number");
					String firstname = rs.getString("first_name");
					String lastname = rs.getString("last_name");
					String email = rs.getString("email");
					String phone = rs.getString("phone");
					String personalID = rs.getString("personal_id");
					student = new Student(id, firstname, lastname, email, phone, personalID);

					if (txtUserName.getText().equals(student.getName())
							&& passwordField.getText().equalsIgnoreCase(student.getStudent_number())) {
						System.out.println("fdjvhvffdhvbdfhv");

						foundS = true;
						break;

					}

				}
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}

		if (foundT) {
			MenuPage mp = new MenuPage(con2, teacher);
			mp.getFrame().setVisible(true);
			frmLogIn.setVisible(false);

		} else if (foundS) {
			MenuPage2 mp2 = new MenuPage2(con2, student);
			mp2.getFrame().setVisible(true);
			frmLogIn.setVisible(false);

		} else {
			txtUserName.setText("");
			passwordField.setText("");

			JOptionPane.showMessageDialog(frmLogIn, "Wrong password or user name");

		}
	}

}
