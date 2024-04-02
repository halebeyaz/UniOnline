import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
/* Displaying the announcements that shared from teacher */
public class DisplayAnnouncements extends JFrame {
	
	private JFrame frame;
	private JScrollPane pane;
	private JTable jTable1;
	private JTableHeader header;

	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnUpdate;

	private JTextField txtId;
	private JTextField txtName;
	private JTextField txtInfo;
	private JTextField txtCourseId;
	private JTextField txtTaId;

	private DefaultTableModel model;

	private static Statement st;
	private static ResultSet rs;
	private JPanel panel;

	private ArrayList<Announcement> ann;
	private ArrayList<Announcement> lst;
	private JLabel label;
	
	public DisplayAnnouncements( Teacher teacher) {
		UniOnline uni = Main.db.uni;
		panel = new JPanel();
		panel.setBounds(0, 0, 500, 750);
		lst = new ArrayList<Announcement>();
	
		ann = uni.getAnnouncements();
		
		
		for (int i = 0; i < ann.size(); i++) { // found teachers announcement from all announcements
			if(ann.get(i).getTaID().equals(teacher.getTeacher_id()))
			{
				System.out.println(teacher.getTeacher_id());
				lst.add(ann.get(i));
			}
		}
		frame = new JFrame("All Announcements");
		frame.getContentPane().setLayout(null);

		btnAdd = new JButton("Add"); //adding ner announcement

		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (!txtId.getText().matches("([0-9]*){1,20}")) {
					JOptionPane.showMessageDialog(frame, "Id value must be numeric!");
				} else {
					if (!txtInfo.getText().equals("") && !txtName.getText().equals("") &&!txtCourseId.getText().equals("") &&!txtTaId.getText().equals("") ) {
						model.addRow(
								new Object[] {txtId.getText(),txtName.getText(), txtInfo.getText(), txtCourseId.getText(),txtTaId.getText()});
						
						Announcement a  =  new Announcement(Integer.parseInt(txtId.getText()),txtName.getText(), txtInfo.getText(), txtCourseId.getText(),txtTaId.getText());
						
						Main.db.uni.add("announcement",a);
						ArrayList<StudentCourse> stu=Main.db.uni.getStudent_course();
						for(int i=0;i<stu.size();i++)
						{
							if(stu.get(i).getCourse().equals(a.getCourseID()))
							{
								Main.db.uni.add("announcement_student",new AnnouncementStudent(a.getID(), stu.get(i).getStudent()));
							}
						}
						
						
		
						JOptionPane.showMessageDialog(frame, "Successfully added!");

						txtId.setText("");
						txtName.setText("");
						txtInfo.setText("");
						txtCourseId.setText("");
						txtTaId.setText("");

					} else {
						JOptionPane.showMessageDialog(frame, "All texts must be filled!");
					}
				}
			}
		});
		btnAdd.setBackground(Color.GREEN);
		btnAdd.setBounds(327, 33, 130, 31);
		frame.getContentPane().add(btnAdd);
		
		
		btnDelete = new JButton("Delete selected");  ///Deleting selected announcement
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int selectedRow = jTable1.getSelectedRow();

				int answer = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this instance?",
						"An Inane Question", JOptionPane.YES_NO_OPTION);

				if (selectedRow >= 0 && answer == 0) {
					
					Announcement a  =  new Announcement(Integer.parseInt(model.getValueAt(selectedRow, 0).toString()),model.getValueAt(selectedRow, 1).toString(),model.getValueAt(selectedRow, 2).toString(),model.getValueAt(selectedRow, 3).toString(),model.getValueAt(selectedRow, 4).toString());
					
					Main.db.delete("announcement",a);
					
					model.removeRow(selectedRow);

					txtId.setText("");
					txtName.setText("");
					txtInfo.setText("");
					txtCourseId.setText("");
					txtTaId.setText("");

				}
			}
		});
		btnDelete.setBackground(Color.ORANGE);
		btnDelete.setBounds(327, 74, 130, 31);
		frame.getContentPane().add(btnDelete);
		
		btnUpdate = new JButton("Update");  //updated selected announcement
		
		
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int update_row = jTable1.getSelectedRow();
				if (update_row != -1) {
					model.setValueAt(txtId.getText(), update_row, 0);
					model.setValueAt(txtName.getText(), update_row, 1);
					model.setValueAt(txtInfo.getText(), update_row, 2);
					model.setValueAt(txtCourseId.getText(), update_row, 3);
					model.setValueAt(txtTaId.getText(), update_row, 4);

					

			
					Announcement a  =  new Announcement(Integer.parseInt(txtId.getText()),txtName.getText(), txtInfo.getText(), txtCourseId.getText(),txtTaId.getText());
					
					
					Main.db.update("announcement", a);

					update_row = -1;
				}
			}
		});


		
		btnUpdate.setBounds(327, 115, 130, 31);
		frame.getContentPane().add(btnUpdate);

		String col[] = { "Announcement ID", "	Name", "Info", "CourseID","TA ID" };

		model = new DefaultTableModel(col, 0);

		for (int i = 0; i < lst.size(); i++) {

			int id = lst.get(i).getID();
			String name = lst.get(i).getName();
			String info = lst.get(i).getInfo();
			String course_id = lst.get(i).getCourseID();
			String ta_id = lst.get(i).getTaID();

			Object[] row = { id, name, info, course_id,ta_id };
			model.addRow(row);
		}

		jTable1 = new JTable();
		jTable1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				int update_row = jTable1.rowAtPoint(evt.getPoint());

				txtId.setText(model.getValueAt(update_row, 0).toString());
				txtName.setText(model.getValueAt(update_row, 1).toString());
				txtInfo.setText(model.getValueAt(update_row, 2).toString());
				txtCourseId.setText(model.getValueAt(update_row, 3).toString());
				txtTaId.setText(model.getValueAt(update_row, 4).toString());
			}
		});
		jTable1.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {

				int keyboard_row = jTable1.getSelectedRow();

				if (e.getKeyCode() == 38) {
					if (keyboard_row > 0) {
						keyboard_row--;

						txtId.setText(model.getValueAt(keyboard_row, 0).toString());
						txtName.setText(model.getValueAt(keyboard_row, 1).toString());
						txtInfo.setText(model.getValueAt(keyboard_row, 2).toString());
						txtCourseId.setText(model.getValueAt(keyboard_row, 3).toString());
						txtTaId.setText(model.getValueAt(keyboard_row, 4).toString());
					}
				} else if (e.getKeyCode() == 40) {
					if (keyboard_row < model.getRowCount()) {
						keyboard_row++;

						txtId.setText(model.getValueAt(keyboard_row, 0).toString());
						txtName.setText(model.getValueAt(keyboard_row, 1).toString());
						txtInfo.setText(model.getValueAt(keyboard_row, 2).toString());
						txtCourseId.setText(model.getValueAt(keyboard_row, 3).toString());
						txtTaId.setText(model.getValueAt(keyboard_row, 4).toString());

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
		
		jTable1.setModel(model);

		header = jTable1.getTableHeader();
		header.setBackground(Color.yellow);
		panel.setLayout(null);

		pane = new JScrollPane(jTable1);
		pane.setBounds(12, 231, 476, 458);
		jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		panel.add(pane);

		label = new JLabel("New label");
		pane.setColumnHeaderView(label);
		frame.getContentPane().add(panel);

		JLabel lblid = new JLabel("ID:");
		lblid.setBounds(48, 20, 80, 30);
		panel.add(lblid);

		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(48, 63, 72, 14);
		panel.add(lblName);

		JLabel lblInfo = new JLabel("Info:");
		lblInfo.setBounds(48, 151, 88, 14);
		panel.add(lblInfo);
		
		JLabel lblCID = new JLabel("Course id:");
		lblCID.setBounds(48, 107, 72, 14);
		panel.add(lblCID);

		JLabel lblTAD = new JLabel("TA id:");
		lblTAD.setBounds(48, 187, 88, 14);
		panel.add(lblTAD);

		txtId = new JTextField();
		txtId.setBounds(132, 13, 182, 31);
		panel.add(txtId);
		txtId.setColumns(10);

		txtName = new JTextField();
		txtName.setBounds(132, 55, 182, 31);
		panel.add(txtName);
		txtName.setColumns(10);

		txtInfo = new JTextField();
		txtInfo.setBounds(132, 140, 182, 31);
		panel.add(txtInfo);
		txtInfo.setColumns(10);
		
		txtCourseId = new JTextField();
		txtCourseId.setBounds(132, 99, 182, 31);
		panel.add(txtCourseId);
		txtCourseId.setColumns(10);

		txtTaId = new JTextField();
		txtTaId.setBounds(132, 178, 182, 31);
		panel.add(txtTaId);
		txtTaId.setColumns(10);

		frame.setSize(500, 750);
		frame.setUndecorated(true);
		frame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		frame.setVisible(true);
	}
	
	
}
