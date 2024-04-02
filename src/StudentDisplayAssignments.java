import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JTable;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/*Display the Assignments that given in course to student*/
public class StudentDisplayAssignments {

	private JFrame frame;
	private JScrollPane pane;
	private JPanel panel;
	private JTableHeader header;
	private JTable table;
	private DefaultTableModel model;
	private JTextField textName;
	private JTextField textCoruseCode;
	private JTextField textAssnName;
	private JTextField textField;
	private JLabel lblText;
	
	
	private ArrayList<AssignmentStudent> assStu;
	private ArrayList<Assignment> ass;
	private ArrayList<AssignmentStudent> lst;
	
	public StudentDisplayAssignments(Student s) {

		assStu=Main.db.uni.getAssignments_student();
		ass=Main.db.uni.getAssignments();
		panel = new JPanel();
		panel.setBounds(0, 0, 500, 750);
		lst = new ArrayList<AssignmentStudent>();
		frame = new JFrame("Student Assignmetns");
		frame.getContentPane().setLayout(null);
		frame.setBounds(100, 100, 450, 572);

		frame.setUndecorated(true);
		frame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		panel.setLayout(null);
		
		table = new JTable();
		table.setBounds(12, 526, 408, -287);
		
		
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(272, 81, 116, 25);
		frame.getContentPane().add(btnUpdate);
		btnUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int update_row = table.getSelectedRow();
				if (update_row != -1) {
					model.setValueAt(textName.getText(), update_row, 0);
					model.setValueAt(textCoruseCode.getText(), update_row, 1);
					model.setValueAt(textAssnName.getText(), update_row, 2);
					model.setValueAt(textField.getText(), update_row, 3);
					
					if(textField.getText()!=null)
					{
						assStu.get(update_row).setStatus("completed");
						assStu.get(update_row).setText(textField.getText());
						Main.db.update("assignment_student", assStu.get(update_row));
					}
					
				}
				
			}
		});
		
		JLabel lblStudentName = new JLabel("Name : ");
		lblStudentName.setBounds(12, 27, 80, 22);
		panel.add(lblStudentName);
		
		textName = new JTextField();
		textName.setBounds(112, 27, 116, 22);
		panel.add(textName);
		textName.setColumns(10);
		
		JLabel lblCourseCode = new JLabel("Course Code:");
		lblCourseCode.setBounds(12, 63, 80, 22);
		panel.add(lblCourseCode);
		
		textCoruseCode = new JTextField();
		textCoruseCode.setBounds(112, 63, 116, 22);
		panel.add(textCoruseCode);
		textCoruseCode.setColumns(10);
		
		textAssnName = new JTextField();
		textAssnName.setBounds(112, 96, 116, 22);
		panel.add(textAssnName);
		textAssnName.setColumns(10);
		
		JLabel lblAssþgnmentName = new JLabel("Assn Name :");
		lblAssþgnmentName.setBounds(12, 96, 80, 22);
		panel.add(lblAssþgnmentName);
		
		textField = new JTextField();
		textField.setBounds(112, 129, 116, 22);
		panel.add(textField);
		textField.setColumns(10);
		
		lblText = new JLabel("Text : ");
		lblText.setBounds(12, 129, 80, 22);
		panel.add(lblText);
		frame.setVisible(true);
		
		
		
		for(int i=0;i<assStu.size();i++)
		{
			System.out.println(assStu.get(i).toString());
			if(assStu.get(i).getStudent_id().equals(s.getStudent_number())) //find the assignment for student
			{
				lst.add(assStu.get(i));
			}
		}
		

		String col[] = {"Name", "Cource Code", "Assn Name","Text" };
		model = new DefaultTableModel(col, 0);
		
		for (int i = 0; i < lst.size(); i++) {

			String name = s.getName();
			String courseCode = ass.get(lst.get(i).getAssign_id()-1).getCourseID();
			String assnName = ass.get(lst.get(i).getAssign_id()-1).getName();
			String text = lst.get(i).getText();

			Object[] row = {name,courseCode,assnName,text };
			model.addRow(row);
		}
		
		

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				int update_row = table.rowAtPoint(evt.getPoint());

				textName.setText(model.getValueAt(update_row, 0).toString());
				textCoruseCode.setText(model.getValueAt(update_row, 1).toString());
				textAssnName.setText(model.getValueAt(update_row, 2).toString());
				if(!textField.getText().equals(""))
				{	
					textField.setText(model.getValueAt(update_row, 3).toString());
				}
			}
		});
		
		
		table.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {

				int keyboard_row = table.getSelectedRow();

				if (e.getKeyCode() == 38) {
					if (keyboard_row > 0) {
						keyboard_row--;

						textName.setText(model.getValueAt(keyboard_row, 0).toString());
						textCoruseCode.setText(model.getValueAt(keyboard_row, 1).toString());
						textName.setText(model.getValueAt(keyboard_row, 2).toString());
						textField.setText(model.getValueAt(keyboard_row, 3).toString());
					}
				} else if (e.getKeyCode() == 40) {
					if (keyboard_row < model.getRowCount()) {
						keyboard_row++;

						textName.setText(model.getValueAt(keyboard_row, 0).toString());
						textCoruseCode.setText(model.getValueAt(keyboard_row, 1).toString());
						textName.setText(model.getValueAt(keyboard_row, 2).toString());
						textField.setText(model.getValueAt(keyboard_row, 3).toString());

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
		
		table.setModel(model);
		header = table.getTableHeader();
		header.setBackground(Color.yellow);
		


		pane = new JScrollPane(table);
		pane.setBounds(12, 231, 476, 458);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		panel.add(pane);

		frame.getContentPane().add(panel);
		
	}
}
