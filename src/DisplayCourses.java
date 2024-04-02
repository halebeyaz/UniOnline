import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.Color;
/* Display the courses that teacher add*/
public class DisplayCourses extends JPanel {

	private JFrame frame;
	private JScrollPane pane;
	private JTable jTable1;
	private JTableHeader header;

	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnUpdate;

	private JTextField txtCourseCode;
	private JTextField txtName;
	private JTextField txtCapacity;
	private DefaultTableModel model;

	private static Statement st;
	private static ResultSet rs;
	private JPanel panel;

	private ArrayList<Course> lst;
	private JLabel label;


	public DisplayCourses(Teacher teacher) {
		UniOnline uni = Main.db.uni;
		panel = new JPanel();
		panel.setBounds(0, 0, 500, 750);

		ArrayList<TeacherCourse> c = uni.getTeacher_course();
		ArrayList<Course> course = uni.getCourses();
		lst = new ArrayList<Course>();

		
		for (int i = 0; i < c.size(); i++) { //Find teacher's course
			if(c.get(i).getTeacher().equals(teacher.getTeacher_id()))
			{
				System.out.println(c.get(i).getTeacher() + " 1");
				for (int j = 0; j < course.size(); j++) {
					if(course.get(j).getCode().equals(c.get(i).getCourse()))
					{
						System.out.println(c.get(i).getTeacher() + " 2");

						lst.add(course.get(j));
					}
				}
			}
		}
		frame = new JFrame("All Courses");
		frame.getContentPane().setLayout(null);

		btnAdd = new JButton("Add"); //adding new course

		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (!txtCapacity.getText().matches("([0-9]*){1,20}")) {
					JOptionPane.showMessageDialog(frame, "Capacity value must be numeric!");
				} else {
					if (!txtCourseCode.getText().equals("") && !txtName.getText().equals("")) {
						model.addRow(
								new Object[] { txtCourseCode.getText(), txtName.getText(), txtCapacity.getText() });
						
						Course c  =  new Course(txtCourseCode.getText(),txtName.getText(),Integer.parseInt(txtCapacity.getText()));
						TeacherCourse t = new TeacherCourse(teacher.getTeacher_id(), txtCourseCode.getText());
						
						uni.add("course", c);
						uni.add("teacher_course", t);
						
						
						JOptionPane.showMessageDialog(frame, "Successfully added!");

						txtCourseCode.setText("");
						txtName.setText("");
						txtCapacity.setText("");

					} else {
						JOptionPane.showMessageDialog(frame, "All texts must be filled!");
					}
				}
			}
		});

		btnAdd.setBackground(Color.GREEN);
		btnAdd.setBounds(327, 33, 130, 31);
		frame.getContentPane().add(btnAdd);

		btnDelete = new JButton("Delete selected");			//delete selectted course
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int selectedRow = jTable1.getSelectedRow();

				int answer = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this instance?",
						"An Inane Question", JOptionPane.YES_NO_OPTION);

				if (selectedRow >= 0 && answer == 0) {
										Course c  =  new Course(model.getValueAt(selectedRow, 0).toString(),model.getValueAt(selectedRow, 1).toString(),Integer.parseInt(model.getValueAt(selectedRow, 2).toString()));

					Main.db.delete("course",c);
					
					model.removeRow(selectedRow);

					txtCourseCode.setText("");
					txtName.setText("");
					txtCapacity.setText("");

				}
			}
		});

		btnDelete.setBackground(Color.ORANGE);
		btnDelete.setBounds(327, 74, 130, 31);
		frame.getContentPane().add(btnDelete);

		btnUpdate = new JButton("Update");			//update selected course
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int update_row = jTable1.getSelectedRow();
				if (update_row != -1) {
					model.setValueAt(txtCourseCode.getText(), update_row, 0);
					model.setValueAt(txtName.getText(), update_row, 1);
					model.setValueAt(txtCapacity.getText(), update_row, 2);
					
					Course c  =  new Course(txtCourseCode.getText(),txtName.getText(),Integer.parseInt(txtCapacity.getText()));
					TeacherCourse t = new TeacherCourse(teacher.getTeacher_id(), txtCourseCode.getText());
					
					
					Main.db.update("course", c);

					update_row = -1;
				}
			}
		});

		btnUpdate.setBounds(327, 115, 130, 31);
		frame.getContentPane().add(btnUpdate);

		String col[] = { "CourseCode", "	Name", "	Capacity" };

		model = new DefaultTableModel(col, 0);

		for (int i = 0; i < lst.size(); i++) {

			
			String course_code = lst.get(i).getCode();
			String name = lst.get(i).getName();
			int capacity = lst.get(i).getCapacity();

			Object[] row = { course_code, name, capacity };
			model.addRow(row);
		}

		jTable1 = new JTable();
		jTable1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				int update_row = jTable1.rowAtPoint(evt.getPoint());

				txtCourseCode.setText(model.getValueAt(update_row, 0).toString());
				txtName.setText(model.getValueAt(update_row, 1).toString());
				txtCapacity.setText(model.getValueAt(update_row, 2).toString());

			}
		});

		jTable1.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {

				int keyboard_row = jTable1.getSelectedRow();

				if (e.getKeyCode() == 38) {
					if (keyboard_row > 0) {
						keyboard_row--;

						txtCourseCode.setText(model.getValueAt(keyboard_row, 0).toString());
						txtName.setText(model.getValueAt(keyboard_row, 1).toString());
						txtCapacity.setText(model.getValueAt(keyboard_row, 2).toString());

					}
				} else if (e.getKeyCode() == 40) {
					if (keyboard_row < model.getRowCount()) {
						keyboard_row++;

						txtCourseCode.setText(model.getValueAt(keyboard_row, 0).toString());
						txtName.setText(model.getValueAt(keyboard_row, 1).toString());
						txtCapacity.setText(model.getValueAt(keyboard_row, 2).toString());

					}
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

		});
		
		JButton btnGoToThe = new JButton("Go to the stream");		//Open the stream page of selected class
		btnGoToThe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int selectedRow = jTable1.getSelectedRow();

				

				if (selectedRow >= 0) {
					Course c  =  new Course(txtCourseCode.getText().toString(),txtName.getText().toString(),Integer.parseInt(txtCapacity.getText().toString()));
					TeacherStream ts = new TeacherStream(teacher, c);

				}
			
				
				
			}
		});
		btnGoToThe.setBackground(Color.CYAN);
		btnGoToThe.setBounds(328, 159, 130, 31);
		panel.add(btnGoToThe);
		jTable1.setModel(model);

		header = jTable1.getTableHeader();
		header.setBackground(Color.yellow);
		panel.setLayout(null);

		pane = new JScrollPane(jTable1);
		pane.setBounds(96, 274, 302, 309);
		jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		panel.add(pane);

		label = new JLabel("New label");
		pane.setColumnHeaderView(label);
		frame.getContentPane().add(panel);

		JLabel lblcap = new JLabel("Capacity:");
		lblcap.setBounds(48, 114, 80, 30);
		panel.add(lblcap);

		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(48, 79, 72, 14);
		panel.add(lblName);

		JLabel lblId = new JLabel("Course Code:");
		lblId.setBounds(48, 34, 88, 14);
		panel.add(lblId);

		txtCapacity = new JTextField();
		txtCapacity.setBounds(132, 114, 182, 31);
		panel.add(txtCapacity);
		txtCapacity.setColumns(10);

		txtName = new JTextField();
		txtName.setBounds(132, 71, 182, 31);
		panel.add(txtName);
		txtName.setColumns(10);

		txtCourseCode = new JTextField();
		txtCourseCode.setBounds(132, 26, 182, 31);
		panel.add(txtCourseCode);
		txtCourseCode.setColumns(10);
		
		

		frame.setSize(500, 750);
		frame.setUndecorated(true);
		frame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		frame.setVisible(true);
	}
}
