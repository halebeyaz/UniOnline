import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;

import java.util.ArrayList;

import javax.swing.*;
import java.awt.Color;
import javax.swing.GroupLayout.Alignment;
/*Display the courses that student is enrolled.*/
public class StudentDisplayCourses extends JPanel {

	private JFrame frame;
	JScrollPane scrollPane;
	private ArrayList<Course> lst;
	JPanel panel;
	JButton btnNewButton;
	private JTextField textEnroll;

	public StudentDisplayCourses(Student student) {
		lst = new ArrayList<Course>();
		ArrayList<StudentCourse> list = Main.db.uni.getStudent_course();
		ArrayList<String> course_code = new ArrayList<String>();
		ArrayList<Course> listCourse = Main.db.uni.getCourses();
		for (StudentCourse s_c : list) {
			if (s_c.getStudent().equals(student.getStudent_number())) {	//find student enrolled course codes.
				for (Course c : listCourse) {
					if (c.getCode().equals(s_c.getCourse()))			//get coursec from course code.
						lst.add(c);
				}
			}
		}

		frame = new JFrame();
		frame.setBounds(100, 100, 775, 788);
		panel = new JPanel();

		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 0, 733, 466);
		ArrayList<JRadioButton> buttons = new ArrayList<JRadioButton>();
		for (int i = 0; i < lst.size(); i++) {
			buttons.add(i, new JRadioButton(lst.get(i).getCode() + " " + lst.get(i).getName()));
			buttons.get(i).setBackground(Color.cyan);
			buttons.get(i).setMaximumSize(new Dimension(500, 70));
			buttons.get(i).setAlignmentX(CENTER_ALIGNMENT);
			panel.add(buttons.get(i));
			panel.add(Box.createRigidArea(new Dimension(0, 20)));
		}

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		frame.getContentPane().add(scrollPane);

		scrollPane.setColumnHeaderView(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setSize(700, 500);
		btnNewButton = new JButton("OPEN");					//opens stream of selected course
		btnNewButton.setBounds(558, 479, 172, 46);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < lst.size(); i++) {
					if (buttons.get(i).isSelected()) {
						StudentStream stream = new StudentStream(lst.get(i), student);
					}
				}
			}

		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		frame.getContentPane().add(btnNewButton);

		textEnroll = new JTextField();
		textEnroll.setBounds(12, 527, 172, 37);
		frame.getContentPane().add(textEnroll);
		textEnroll.setColumns(10);

		JLabel lblEnroll = new JLabel("Course ID:");
		lblEnroll.setBounds(12, 499, 114, 16);
		frame.getContentPane().add(lblEnroll);

		JButton btnEnroll = new JButton("Enroll Course");			//enrolling new course
		btnEnroll.setBackground(Color.GREEN);
		btnEnroll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				boolean courseExists = false;
				boolean alreadyEnrolled = false;
				
				Course c = new Course("", "", 0);
				for (int i = 0; i < listCourse.size(); i++) {
					System.out.println(listCourse.get(i).getCode() + " " +textEnroll.getText());
					if (listCourse.get(i).getCode().equals(textEnroll.getText())) {

						courseExists = true;
						System.out.println("course exists: " + listCourse.get(i).getName());
						c = new Course(listCourse.get(i).getCode(),listCourse.get(i).getName(),listCourse.get(i).getCapacity());
					}
				}

				if (courseExists) {
					for (int i = 0; i < list.size(); i++) {
						
						
						if (list.get(i).getCourse().equals(textEnroll.getText())
								&& list.get(i).getStudent().equals(student.getStudent_number())) {
							alreadyEnrolled = true;
						}

					}
					if (alreadyEnrolled) {
						JOptionPane.showMessageDialog(frame, "You are already enrolled in this class!");
					} else if (!alreadyEnrolled) {
						
						try {
							StudentCourse sc = new StudentCourse(student.getStudent_number(), c.getCode(), "elective");
							Main.db.uni.add("student_course", sc);
							JOptionPane.showMessageDialog(frame, "Successfully Enrolled!");
							
							
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					}

				} else if (!courseExists) {
					JOptionPane.showMessageDialog(frame, "Wrong course ID. Course does not exist.");
				}
			}
		});
		btnEnroll.setBounds(196, 527, 147, 37);
		frame.getContentPane().add(btnEnroll);
		frame.setVisible(true);

	}
}
